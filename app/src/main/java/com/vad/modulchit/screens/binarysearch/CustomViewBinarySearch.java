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

import com.vad.modulchit.pojos.BinarySearchModel;
import com.vad.modulchit.utils.search.BinarySearch;

import java.util.Arrays;
import java.util.List;

public class CustomViewBinarySearch extends View {

    private final Paint paint = new Paint();
    private final Paint fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint paintForCondition = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<BinarySearchModel> binarySearchModels;

    private final int STROKE_WITH = 3;


    public void searchElement(List<BinarySearchModel> binarySearchModels) {
        this.binarySearchModels = binarySearchModels;
        requestLayout();
    }

    public CustomViewBinarySearch(Context context) {
        super(context);
        init();
    }

    public CustomViewBinarySearch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        fontPaint.setTextSize(50);
        fontPaint.setStyle(Paint.Style.FILL);
        paintForCondition.setTextSize(20);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WITH);
    }

    //    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        if (binarySearchModels != null) {
            drawBinarySearch(canvas);
        }
    }

    public void drawBinarySearch(Canvas canvas) {

        float x = STROKE_WITH;
        int y = STROKE_WITH;
        int width = 75;
        int height = 70;
        int shiftDown = 75;
        float shiftX = 0;
        float len = 0;

        for (int i : binarySearchModels.get(0).getArrTemp()) {
            if(Math.ceil(Math.log10(i)) == 2){
                width = 100;
                break;
            }
        }
        int length = binarySearchModels.get(0).getArrTemp().length;
        float xStart = (float) (length*width/2)+STROKE_WITH;
        int lengthWith = length*width;

        for (int i = 0; i < binarySearchModels.size()-1; i++) {
            if (binarySearchModels.get(i).getCompareElementAndMid().equals("zero")) {
                canvas.drawText("Element is absent " + binarySearchModels.get(i).getMidElement(), x, y, paintForCondition);
                break;
            }
            drawArray(canvas, paint, binarySearchModels.get(i).getArrTemp(), x+shiftX, y, width, height, binarySearchModels.get(i).getMidElement());
            drawArrow(canvas, paint, xStart, y+height, xStart, y+height+shiftDown);
            canvas.drawText(binarySearchModels.get(i).getCompareElementAndMid(), xStart+10, y+height+shiftDown/2, paintForCondition);
            y = y + height+shiftDown;
            if (i+1 < binarySearchModels.size()) {
                len = binarySearchModels.get(i+1).getArrTemp().length*width;
            }
            shiftX = (lengthWith-len)/2;
        }

        drawArray(canvas, paint, binarySearchModels.get(binarySearchModels.size()-1).getArrTemp(), x+shiftX, y, width, height);
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
            int shift = 5;
            canvas.drawRect(i, y, i+width, y+height, paint);
            paint.setColor(Color.GREEN);
            canvas.drawRect(i+shift, y+shift, i+width-shift, y+height-shift, paint);
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
