package com.vad.modulchit.screens.binarysearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class ViewBinarySearch extends View {

    private final Paint paint;
    private final Paint fontPaint;

    public ViewBinarySearch(Context context) {
        super(context);
        paint = new Paint();
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(90);
        fontPaint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {4, 5, 6};
        int x = 200;
        int y = 100;
        int width = 100;
        int height = 120;

        int length = drawArray(canvas, paint, arr, x, y, width, height);
        drawArrow(canvas, paint, length/2, y+height, length/2, y+height+100);
        int length2 = drawArray(canvas, paint, arr2, length-arr2.length*width, y+height+125, width, height);

    }

    private void drawArrow(Canvas canvas, Paint paint, int xStart, int yStart, int xEnd, int yEnd) {
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        canvas.drawLine(xStart,yStart, xEnd, yEnd, paint);

        paint.setColor(Color.BLACK);
        drawTriangle(canvas, paint, xStart, yEnd+15, 30);
    }

    private int drawArray(Canvas canvas, Paint paint, int[] arr, int x, int y, int width, int height) {
        int k = 0;

        //i = 200, 740 100
        int length = width*arr.length+x;
        for (int i = x; i < length; i+=width) {

            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawRect(i, y, i-width, y+height, paint);

            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(5);
            canvas.drawRect(i-10, y+10, i-width+10,y+height-10, paint);

            canvas.drawText(String.valueOf(arr[k]), i-75, y+height-30, fontPaint);
            k++;
        }

        return length;
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
