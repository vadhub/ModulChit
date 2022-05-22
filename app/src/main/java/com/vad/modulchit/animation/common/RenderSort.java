package com.vad.modulchit.animation.common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.models.AlgebraMod;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RenderSort implements RenderState {

    private final static int FONT_SIZE = 20;
    private final static int SHIFT = 10;

    private final SurfaceHolder mSurfaceHolder;
    private final Paint paint;
    private final Paint paintFont;
    private StepRecorder recorder;
    private ButtonIconChange buttonIconChange;
    private StatusAnimation statusAnimation = StatusAnimation.STOP;

    public RenderSort(SurfaceHolder mSurfaceHolder) {
        this.mSurfaceHolder = mSurfaceHolder;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        paintFont = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFont.setColor(Color.BLACK);
        paintFont.setStyle(Paint.Style.FILL);
        paintFont.setTextSize(FONT_SIZE);
    }

    public float[] scaling(int[] arr) {
        int max = AlgebraMod.max(arr);
        float[] scale = new float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            scale[i] = (float) arr[i] / max;
            if (scale[i] <= 0.10f) {
                scale[i] += 0.05f;
            }
        }
        return scale;
    }

    public void drawArray(Canvas canvas, int[] arr) {
        canvas.drawColor(Color.WHITE);
        int startDrawY = getMaxHeight();
        int startDrawX = SHIFT + getStrokeWidth() / 2;

        paint.setStrokeWidth(getStrokeWidth() - SHIFT);

        float[] scales = scaling(arr);

        for (int i = 0; i < arr.length; i++) {
            canvas.drawText(arr[i] + "", (float) (startDrawX - getStrokeWidth() * 0.5), startDrawY - scales[i] * getMaxHeight() + 10 + FONT_SIZE, paintFont);
            canvas.drawLine(startDrawX, startDrawY, startDrawX, startDrawY - scales[i] * getMaxHeight() + 20 + FONT_SIZE, paint);
            startDrawX = startDrawX + getStrokeWidth();
            paint.setColor(Color.BLUE);
        }
    }

    public void stopAnimation() {
        getButtonIconChange().setButtonStatus();
        recorder = null;
    }

    public void draw(List<int[]> arr) {
        Timer timer = new Timer();
        timer.schedule(new TimerTaskDraw(arr), 0, 500);
    }

    private class TimerTaskDraw extends TimerTask {
        int i = 0;
        List<int[]> arr;

        public TimerTaskDraw(List<int[]> arr) {
            this.arr = arr;
        }
        @Override
        public void run() {
            Canvas canvas = mSurfaceHolder.lockCanvas();
            if (canvas != null) {
                drawArray(canvas, arr.get(i));
                mSurfaceHolder.unlockCanvasAndPost(canvas);
                if (i < arr.size()) {
                    i++;
                } else {
                    cancel();
                }
            }
        }
    }

    @Override
    public void setStateRun(StepRecorder stepRecorder) {
        statusAnimation = StatusAnimation.START;
        draw(stepRecorder.getSteps());
        setRecorder(stepRecorder);
    }

    @Override
    public void setStateStop() {
        statusAnimation = StatusAnimation.STOP;
        stopAnimation();
    }

    @Override
    public void setStatePause() {
        statusAnimation = StatusAnimation.PAUSE;
    }

    @Override
    public void setStateRestart() {
        statusAnimation = StatusAnimation.RESTART;
    }

    @Override
    public StatusAnimation getStateRun() {
        return statusAnimation;
    }

    public StepRecorder getRecorder() {
        return recorder;
    }

    public void setRecorder(StepRecorder recorder) {
        this.recorder = recorder;
    }

    public ButtonIconChange getButtonIconChange() {
        return buttonIconChange;
    }

    public void setButtonIcon(ButtonIconChange buttonIconChange) {
        this.buttonIconChange = buttonIconChange;
    }

    public int getStrokeWidth() {
        return getMaxWith() / (recorder.getSteps().get(0).length + 1);
    }

    public int getMaxWith() {
        return mSurfaceHolder.getSurfaceFrame().width();
    }

    public int getMaxHeight() {
        return mSurfaceHolder.getSurfaceFrame().height();
    }

}
