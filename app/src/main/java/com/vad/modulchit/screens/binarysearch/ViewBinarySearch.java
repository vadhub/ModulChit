package com.vad.modulchit.screens.binarysearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.Arrays;

public class ViewBinarySearch extends View {

    private final Paint paint;
    private final Paint fontPaint;
    private final int STROKE_WITH = 5;

    public ViewBinarySearch(Context context) {
        super(context);
        paint = new Paint();
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(90);
        fontPaint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WITH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {4, 5, 6};
        int[] arr3 = {5};
        float x = STROKE_WITH;
        int y = 100;
        int width = 100;
        int height = 120;
        int shiftDown = 125;
        float length = arr.length*width;
        float shiftX = 0;

        for (int i = 1; i < 5; i++) {

            drawArray(canvas, paint, arr, x+shiftX, y, width, height);
            y = y + height+shiftDown;

            arr = Arrays.copyOfRange(arr, i, arr.length-1);
            float len = arr.length*width;
            shiftX = (length-len)/2;

        }

//        drawArrow(canvas, paint, length/2, y+height, length/2, y+height+shiftDown-25);
//
//        int length1 = arr2.length*width;
//        drawArray(canvas, paint, arr2, length-length1, y+height+shiftDown, width, height);
//        drawArrow(canvas, paint, length1, (y+height)*2, length1, y+(height+shiftDown)*2-25);
//
//        drawArray(canvas, paint, arr3, (length/2)+width/2, y+(height+shiftDown)*2, width, height);

    }

    private void drawArrow(Canvas canvas, Paint paint, int xStart, int yStart, int xEnd, int yEnd) {
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        canvas.drawLine(xStart,yStart, xEnd, yEnd, paint);

        paint.setColor(Color.BLACK);
        drawTriangle(canvas, paint, xStart, yEnd+15, 30);
    }

    private void drawArray(Canvas canvas, Paint paint, int[] arr, float x, int y, int width, int height) {
        int k = 0;

        //i = 200, 740 100
        float length = width*arr.length+x;
        for (float i = x; i < length; i+=width) {

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawRect(i, y, i+width, y+height, paint);

            paint.setColor(Color.GREEN);
//            int shift = 10;
//            canvas.drawRect(i+ shift, y+ shift, i+width+ shift,y+height+ shift, paint);

            canvas.drawText(String.valueOf(arr[k]), (float) (i+width*0.25), y+height-30, fontPaint);
            k++;
        }
    }

    private void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y + halfWidth); // Top
        path.lineTo(x + halfWidth, y - halfWidth); // Bottom left
        path.lineTo(x - halfWidth, y - halfWidth); // Bottom right
        //path.lineTo(x, y + halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }
}
