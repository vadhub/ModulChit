package com.vad.modulchit.screens.sort.bubble;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceHolder;
import com.vad.modulchit.utils.sort.BubbleSort;

import java.util.Arrays;
import java.util.List;

public class Render extends Thread{

    private final static int SIZE_ELEMENT = 10;
    private final static int STROKE_WITH = 20;

    private final SurfaceHolder mSurfaceHolder;
    private Paint paint;
    private Paint paintFont;
    private int[] arr;
    private List<int[]> arrays;

    private boolean mRun = false;

    public void setRun(boolean mRun) {
        this.mRun = mRun;
    }

    public Render(SurfaceHolder mSurfaceHolder, int[] arr) {
        this.mSurfaceHolder = mSurfaceHolder;
        this.arr = arr;

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

    @Override
    public void run() {
        Canvas canvas;
        int temp = 0;

        if (mRun) {

            for (int i = arr.length - 1; i >= 1; i--) {
                for (int j = 0; j < i; j++) {
                    canvas = mSurfaceHolder.lockCanvas();
                    drawArray(canvas, arr);
                    System.out.println(Arrays.toString(arr));
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                    try {
                        sleep(1000);
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
        }



    }

}
