package com.vad.modulchit.models.animation.common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.screens.sort.ScreenSort;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RenderSort implements RenderState {

    private final static int FONT_SIZE = 30;
    private final static int PADDING = 10;

    private final SurfaceHolder mSurfaceHolder;
    private final Paint paint;
    private final Paint paintFont;
    private StepRecorder recorder;
    private ScreenSort screenSort;
    private StatusAnimation statusAnimation = StatusAnimation.STOP;
    private Timer timer;
    private TimerTaskDraw timerTaskDraw;
    private int current;

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

    public float[] scale(int[] arr) {

        int max = Arrays.stream(arr).max().getAsInt();
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
        int startDrawX = PADDING + getStrokeWidth() / 2;

        paint.setStrokeWidth(getStrokeWidth() - PADDING);

        float[] scales = scale(arr);

        for (int i = 0; i < arr.length; i++) {
            canvas.drawText(arr[i] + "", startDrawX, startDrawY - scales[i] * getMaxHeight() + 10 + FONT_SIZE, paintFont);
            canvas.drawLine(startDrawX, startDrawY, startDrawX, startDrawY - scales[i] * getMaxHeight() + 20 + FONT_SIZE, paint);
            startDrawX = startDrawX + getStrokeWidth();
            paint.setColor(Color.BLUE);
        }
    }

    public void draw(List<int[]> arr, int current) {
        timer = new Timer();
        timerTaskDraw = new TimerTaskDraw(arr, current);
        timer.schedule(timerTaskDraw, 0, 500);
    }

    private class TimerTaskDraw extends TimerTask {
        private int current;
        private final List<int[]> arr;

        public TimerTaskDraw(List<int[]> arr, int current) {
            this.arr = arr;
            this.current = current;
        }
        @Override
        public void run() {
            Canvas canvas = mSurfaceHolder.lockCanvas();
            if (canvas != null) {
                drawArray(canvas, arr.get(current));
                screenSort.write(Arrays.toString(arr.get(current))+"\n");
                mSurfaceHolder.unlockCanvasAndPost(canvas);

                if (current < arr.size()-1) {
                    current++;
                } else {
                    setStateStop();
                    cancel();
                }
            }
        }

        public int getCurrent() {
            return current;
        }

    }

    @Override
    public void setStateStart(StepRecorder stepRecorder) {
        statusAnimation = StatusAnimation.START;
        current = 0;
        setRecorder(stepRecorder);
        draw(recorder.getSteps(), current);
    }

    @Override
    public void setStateStop() {
        statusAnimation = StatusAnimation.STOP;
        screenSort.setButtonState();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void setStatePause() {
        statusAnimation = StatusAnimation.PAUSE;
        if (timerTaskDraw != null) {
            current = timerTaskDraw.getCurrent();
        }
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void setStateRestart() {
        statusAnimation = StatusAnimation.RESTART;
        draw(recorder.getSteps(), current);
    }

    @Override
    public void setScreenSort(ScreenSort screenSort) {
        this.screenSort = screenSort;
    }

    @Override
    public StatusAnimation getStateRun() {
        return statusAnimation;
    }

    public void setRecorder(StepRecorder recorder) {
        this.recorder = recorder;
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
