package com.vad.modulchit.screens.sort.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class CustomViewBubbleSort extends SurfaceView implements SurfaceHolder.Callback {

    private final static int SIZE_ELEMENT = 10;
    private final static int STROKE_WITH = 20;

    private Paint paint;
    private Paint paintFont;

    public CustomViewBubbleSort(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        init();
    }

    public void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(STROKE_WITH);

        paintFont = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFont.setColor(Color.WHITE);
        paintFont.setStyle(Paint.Style.FILL);
        paintFont.setTextSize(20);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        int[] arr = {3, 5, 2, 8, 7, 9, 1};
        Canvas canvas = holder.lockCanvas();
        drawArray(canvas, arr);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void drawArray(Canvas canvas, int[] arr) {
        int startDrawX = 100;
        int startDrawY = 200;
        int shift = 10;
        for (int j : arr) {
            canvas.drawText(j+"", startDrawX-STROKE_WITH/2, startDrawY - j * SIZE_ELEMENT - 10, paintFont);
            canvas.drawLine(startDrawX, startDrawY, startDrawX, startDrawY - j * SIZE_ELEMENT, paint);
            startDrawX = startDrawX + shift+STROKE_WITH;
        }

    }
}
