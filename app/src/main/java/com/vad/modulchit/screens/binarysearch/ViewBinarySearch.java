package com.vad.modulchit.screens.binarysearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class ViewBinarySearch extends View {

    private final Paint paint = new Paint();

    public ViewBinarySearch(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 200; i < 740; i+=100) {
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(5);
            canvas.drawRect(i, 100, i-100, 230, paint);

            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(5);
            canvas.drawRect(i-10, 110, i-90,220, paint);
        }

        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        canvas.drawLine(400,233, 400, 380, paint);

        paint.setStyle(Paint.Style.STROKE);

        paint.setColor(Color.BLACK);
        drawTriangle(canvas, paint, 400, 395, 30);

    }

    public void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width) {
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
