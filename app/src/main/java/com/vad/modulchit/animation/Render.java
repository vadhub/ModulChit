package com.vad.modulchit.animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.vad.modulchit.screens.sort.bubble.BubbleSortView;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.Arrays;

public class Render extends Thread implements RenderState {

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
    private BubbleSortView bubbleSortView;
    private StatusAnimation statusAnimation = StatusAnimation.STOP;

    public Render(SurfaceHolder mSurfaceHolder) {
        this.mSurfaceHolder = mSurfaceHolder;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        paintFont = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFont.setColor(Color.BLACK);
        paintFont.setStyle(Paint.Style.FILL);
        paintFont.setTextSize(FONT_SIZE);

    }

    public BubbleSortView getBubbleSortView() {
        return bubbleSortView;
    }

    public void setBubbleSortView(BubbleSortView bubbleSortView) {
        this.bubbleSortView = bubbleSortView;
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
        int temp = 0;
        int[] tempArr = {10};

        while (mRun) {

            if (arr != null && statusAnimation == StatusAnimation.START) {

                if (statusAnimation == StatusAnimation.RESTART) {
                    arr = tempArr;
                    System.out.println(statusAnimation+" "+ Arrays.toString(arr));
                }

                for (int i = arr.length - 1; i >= 1; i--) {

                    if (statusAnimation == StatusAnimation.PAUSE) {
                        tempArr = arr;
                        System.out.println(statusAnimation+" "+ Arrays.toString(arr));
                        break;
                    }
                    for (int j = 0; j < i; j++) {
                        draw(arr, mSurfaceHolder, j);
                        if (statusAnimation == StatusAnimation.PAUSE) {
                            tempArr = arr;
                            break;
                        }

                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (arr[j] > arr[j + 1]) {
                            temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }

                paint.setColor(Color.BLUE);
                draw(arr, mSurfaceHolder, -1);
                statusAnimation = StatusAnimation.STOP;
                bubbleSortView.setButtonStatus();
                arr = null;
            }
        }
    }

    public void draw(int[] arr, SurfaceHolder mSurfaceHolder, int current) {

        Canvas canvas = mSurfaceHolder.lockCanvas();
        drawArray(canvas, arr, current);
        mSurfaceHolder.unlockCanvasAndPost(canvas);

    }

    @Override
    public void setStateRun(StatusAnimation run) {
        statusAnimation = run;
    }

    @Override
    public void setStatePause(StatusAnimation status) {
        statusAnimation = StatusAnimation.PAUSE;
    }

    @Override
    public StatusAnimation getStateRun() {
        return statusAnimation;
    }
}
