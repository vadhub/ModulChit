package com.vad.modulchit.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.vad.modulchit.utils.AlgebraMod;

public abstract class RenderSort extends Thread implements RenderState {

    private int maxHeight;
    private int maxWith;
    private int strokeWidth;
    private final static int FONT_SIZE = 20;
    private final static int SHIFT = 10;

    private final SurfaceHolder mSurfaceHolder;
    private Paint paint;
    private Paint paintFont;
    private int[] arr;
    private boolean mRun = true;
    private ButtonIconChange buttonIconChange;
    private volatile StatusAnimation statusAnimation = StatusAnimation.STOP;

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

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public SurfaceHolder getSurfaceHolder() {
        return mSurfaceHolder;
    }

    public ButtonIconChange getButtonIconChange() {
        return buttonIconChange;
    }

    public void setButtonIconChange(ButtonIconChange buttonIconChange) {
        this.buttonIconChange = buttonIconChange;
    }

    public StatusAnimation getStatusAnimation() {
        return statusAnimation;
    }

    public void setStatusAnimation(StatusAnimation statusAnimation) {
        this.statusAnimation = statusAnimation;
    }

    public ButtonIconChange getButtonIconChanged() {
        return buttonIconChange;
    }

    public void setButtonIcon(ButtonIconChange buttonIconChange) {
        this.buttonIconChange = buttonIconChange;
    }

    public int getStrokeWidth() {
        return getMaxWith() / (arr.length + 1);
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getMaxWith() {
        return mSurfaceHolder.getSurfaceFrame().width();
    }

    public void setMaxWith(int maxWith) {
        this.maxWith = maxWith;
    }

    public int getMaxHeight() {
        return mSurfaceHolder.getSurfaceFrame().height();
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public boolean isRun() {
        return mRun;
    }

    public void setRun(boolean mRun) {
        this.mRun = mRun;
    }

    public float[] scaling(int[] arr) {
        int max = AlgebraMod.max(arr);
        float[] scale = new float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            scale[i] = (float) arr[i] / max;
        }
        return scale;
    }

    public void drawArray(Canvas canvas, int[] arr, int currentIndex) {
        canvas.drawColor(Color.WHITE);
        int startDrawY = getMaxHeight();
        int startDrawX = SHIFT + getStrokeWidth() / 2;

        paint.setStrokeWidth(getStrokeWidth() - SHIFT);

        float[] scales = scaling(arr);

        for (int i = 0; i < arr.length; i++) {
            canvas.drawText(arr[i] + "", startDrawX, startDrawY - scales[i] * getMaxHeight() + 10 + FONT_SIZE, paintFont);

            if (currentIndex == i) {
                paint.setColor(Color.RED);
            }

            canvas.drawLine(startDrawX, startDrawY, startDrawX, startDrawY - scales[i] * getMaxHeight() + 20 + FONT_SIZE, paint);
            startDrawX = startDrawX + getStrokeWidth();
            paint.setColor(Color.BLUE);
        }

    }

    @Override
    public void run() {
        while (mRun) {
            if (arr != null && getStatusAnimation() == StatusAnimation.START) {
                sort(arr);
                stopAnimation();
            }
        }
    }

    protected void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    public void stopAnimation() {
        paint.setColor(Color.BLUE);
        draw(arr, -1);
        setStatusAnimation(StatusAnimation.STOP);
        getButtonIconChange().setButtonStatus();
        setArr(null);
    }

    public abstract void sort(int[] arr);

    public void draw(int[] arr, int current) {

        Canvas canvas = mSurfaceHolder.lockCanvas();
        drawArray(canvas, arr, current);
        mSurfaceHolder.unlockCanvasAndPost(canvas);

    }

    @Override
    public void setStateRun() {
        statusAnimation = StatusAnimation.START;
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
}