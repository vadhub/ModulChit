package com.vad.modulchit.screens.binarysearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
            canvas.drawRect(i, 100, i-110, 230, paint);

            paint.setColor(Color.GREEN);
            paint.setStrokeWidth(5);
            canvas.drawRect(i-10, 110, i-100,220, paint);
        }
    }
}
