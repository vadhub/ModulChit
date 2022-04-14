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
        int length = drawArray(canvas, paint, arr, 200, 100);
        drawArrow(canvas, paint, length/2, 100, length/2, 150);

    }

    private void drawArrow(Canvas canvas, Paint paint, int xStart, int yStart, int xEnd, int yEnd) {
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        canvas.drawLine(xStart,yStart, xEnd, yEnd, paint);

        paint.setColor(Color.BLACK);
        drawTriangle(canvas, paint, xStart, yEnd+15, 30);
    }

    private int drawArray(Canvas canvas, Paint paint, int[] arr, int x, int width) {
        int k = 0;

        //i = 200, 740 100
        int length = width*arr.length+x;
        for (int i = x; i < length; i+=width) {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawRect(i, width, i-width, 230, paint);

            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(5);
            canvas.drawRect(i-10, 110, i-width+10,220, paint);

            canvas.drawText(String.valueOf(arr[k]), i-75, 195, fontPaint);
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
