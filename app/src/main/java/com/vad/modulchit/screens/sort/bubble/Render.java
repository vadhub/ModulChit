package com.vad.modulchit.screens.sort.bubble;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.vad.modulchit.utils.AlgebraMod;
import java.util.Arrays;

public class Render extends Thread {

    private int maxSize = 10;
    private final static int STROKE_WITH = 20;
    private final static int FONT_SIZE = 20;

    private final SurfaceHolder mSurfaceHolder;
    private Paint paint;
    private Paint paintFont;
    private int[] arr;

    public int getMaxSize() {
        return maxSize = mSurfaceHolder.getSurfaceFrame().height();
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    private boolean mRun = false;

    public void setRun(boolean mRun) {
        this.mRun = mRun;
    }

    public Render(SurfaceHolder mSurfaceHolder) {
        this.mSurfaceHolder = mSurfaceHolder;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(STROKE_WITH);

        paintFont = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFont.setColor(Color.WHITE);
        paintFont.setStyle(Paint.Style.FILL);
        paintFont.setTextSize(FONT_SIZE);

    }

    public float[] scaling(int[] arr) {
        int max = AlgebraMod.max(arr);
        float[] scale = new float[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                scale[i] = (float) -1*arr[i]/max;
            }
            scale[i] = (float) arr[i]/max;
        }
        return scale;
    }

    public void drawArray(Canvas canvas, int[] arr, float[] scales) {
        canvas.drawColor(Color.BLACK);
        int startDrawX = 100;
        int startDrawY = getMaxSize();
        int shift = 10;

        for (int i = 0; i < arr.length; i++) {
            canvas.drawText(arr[i]+"", (float) (startDrawX - STROKE_WITH*0.25), startDrawY - scales[i] * getMaxSize() - 10 - FONT_SIZE, paintFont);
            canvas.drawLine(startDrawX, startDrawY, startDrawX, startDrawY - scales[i] * getMaxSize() - 30, paint);
            startDrawX = startDrawX + shift+STROKE_WITH;
        }

    }

    @Override
    public void run() {
        Canvas canvas;
        int temp = 0;

        if (mRun && arr != null) {
            float[] scales = scaling(arr);
            for (int i = arr.length - 1; i >= 1; i--) {
                for (int j = 0; j < i; j++) {
                    canvas = mSurfaceHolder.lockCanvas();
                    drawArray(canvas, arr, scales);
                    System.out.println(Arrays.toString(arr));
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
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
        }
    }

}
