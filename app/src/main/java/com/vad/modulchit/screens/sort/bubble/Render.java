package com.vad.modulchit.screens.sort.bubble;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceHolder;

import com.vad.modulchit.utils.sort.BubbleSort;

import java.util.List;

public class Render extends Thread{

    private final int REDRAW_TIME = 1000;
    private final static int SIZE_ELEMENT = 10;
    private final static int STROKE_WITH = 20;

    private final SurfaceHolder mSurfaceHolder;
    private BubbleSort bubbleSort;
    private long mStartTime;
    private long mPrevTime;
    private Paint paint;
    private Paint paintFont;
    private boolean mRun;
    private int[] arr;

    public Render(SurfaceHolder mSurfaceHolder, BubbleSort bubbleSort, int[] arr) {
        this.mSurfaceHolder = mSurfaceHolder;
        this.bubbleSort = bubbleSort;
        this.arr = arr;
        mRun = false;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(STROKE_WITH);

        paintFont = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFont.setColor(Color.WHITE);
        paintFont.setStyle(Paint.Style.FILL);
        paintFont.setTextSize(20);

    }

    public void drawArray(Canvas canvas, int[] arr) {
        canvas.drawColor(Color.BLACK);
        int startDrawX = 100;
        int startDrawY = 200;
        int shift = 10;

        for (int j : arr) {
            canvas.drawText(j+"", (float) (startDrawX - STROKE_WITH*0.25), startDrawY - j * SIZE_ELEMENT - 10, paintFont);
            canvas.drawLine(startDrawX, startDrawY, startDrawX, startDrawY - j * SIZE_ELEMENT, paint);
            startDrawX = startDrawX + shift+STROKE_WITH;
        }

    }

    public void setRunning(boolean running) {
        mRun = running;
        mPrevTime = getTime();
    }

    public long getTime() {
        return System.nanoTime() / 1_000_000;
    }

    @Override
    public void run() {
        Canvas canvas;
        mStartTime = getTime();
        List<int[]> arrays = bubbleSort.sort(arr);
        int i = 0;
        while (mRun) {
           long currentTime = getTime();
           long elapsedTime = currentTime - mPrevTime;
           if (elapsedTime < REDRAW_TIME) continue;
           canvas = null;
           canvas = mSurfaceHolder.lockCanvas();
           drawArray(canvas, arrays.get(i));
           mSurfaceHolder.unlockCanvasAndPost(canvas);
           mPrevTime = currentTime;
           i++;
        }
    }

    public void cleanup() {
        mRun = false;
    }

}
