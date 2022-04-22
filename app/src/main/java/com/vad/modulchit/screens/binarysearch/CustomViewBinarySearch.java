package com.vad.modulchit.screens.binarysearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class CustomViewBinarySearch extends View {

    private Paint paint;
    private Paint fontPaint;
    private Paint paintForCondition;
    private final int STROKE_WITH = 3;

    public CustomViewBinarySearch(Context context) {
        super(context);
        init();
    }

    public CustomViewBinarySearch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        paint = new Paint();
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(50);
        fontPaint.setStyle(Paint.Style.STROKE);
        paintForCondition = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintForCondition.setTextSize(20);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WITH);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        int[] arr = {11, 12, 13, 44, 54, 66};
        int[] tempArr = arr;
        float x = STROKE_WITH;
        int y = 100;
        int width = 100;
        int height = 70;
        int shiftDown = 75;
        float shiftX = 0;
        int element = 13;
        int low = 0;
        int high = arr.length;
        int tempHigh = arr.length;
        int elementPos = -1;
        String mark = "";

        for (int i : arr) {
            if(Math.ceil(Math.log10(i)) == 2){
                width = 75;
                break;
            }
        }
        float length = arr.length*width;
        float xStart = (float) (arr.length*width/2)+STROKE_WITH;

        while (low <= high) {

            int midIndex = low + (high - low) / 2;

            if (element < arr[midIndex]) {
                high = midIndex - 1;
                tempHigh = midIndex;
                mark = " < ";
            }

            if (element > arr[midIndex]) {
                low = midIndex + 1;
                mark = " > ";
            }

            drawArray(canvas, paint, tempArr, x+shiftX, y, width, height, arr[midIndex]);
            drawArrow(canvas, paint, xStart, y+height, xStart, y+height+shiftDown);
            canvas.drawText(element + mark + arr[midIndex], xStart+10, y+height+shiftDown/2, paintForCondition);
            y = y + height+shiftDown;
            mark = " = ";

            if (element == arr[midIndex]) {
                elementPos = midIndex;
                break;
            }

            tempArr = Arrays.copyOfRange(arr, low, tempHigh);
            float len = tempArr.length*width;
            shiftX = (length-len)/2;
        }

        if (elementPos != -1) {
            arr = new int[]{arr[elementPos]};
            shiftX = (length-1)/2;
            drawArray(canvas, paint, arr, x+shiftX-width/2, y, width, height);
        } else {
            canvas.drawText("Element is absent " + element, x, y, paintForCondition);
        }
    }

    public void drawArrow(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        paint.setColor(Color.BLACK);
        canvas.drawLine(xStart,yStart, xEnd, yEnd, paint);
        drawTriangle(canvas, paint, xStart, (float) (yEnd-STROKE_WITH*2.5), 10);
    }

    public void drawArray(Canvas canvas, Paint paint, int[] arr, float x, int y, int width, int height, int midElement) {
        int k = 0;
        float length = width*arr.length+x;
        for (float i = x; i < length; i+=width) {
            canvas.drawRect(i, y, i+width, y+height, paint);
            if (arr[k] == midElement) {
                int shift = 5;
                paint.setColor(Color.RED);
                canvas.drawRect(i+shift, y+shift, i+width-shift, y+height-shift, paint);
                paint.setColor(Color.BLACK);
            }
            canvas.drawText(String.valueOf(arr[k]), (float) (i+width*0.15), (float) (y+height*0.75), fontPaint);
            k++;
        }
    }

    public void drawArray(Canvas canvas, Paint paint, int[] arr, float x, int y, int width, int height) {
        int k = 0;
        paint.setColor(Color.BLACK);
        float length = width*arr.length+x;
        for (float i = x; i < length; i+=width) {
            canvas.drawRect(i, y, i+width, y+height, paint);
            canvas.drawText(String.valueOf(arr[k]), (float) (i+width*0.15), (float) (y+height*0.75), fontPaint);
            k++;
        }
    }

    public void drawTriangle(Canvas canvas, Paint paint, float x, float y, int width) {
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
