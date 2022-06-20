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

import com.vad.modulchit.models.pojos.BinarySearchModel;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomViewBinarySearch extends View {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint paintForCondition = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<BinarySearchModel> binarySearchModels;
    private final int STROKE_WITH = 3;
    private int maxWidth;
    private int maxHeight;
    private int widthContent;

    public int getMaxHeight() {
        return maxWidth;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void searchElement(List<BinarySearchModel> binarySearchModels) {
        this.binarySearchModels = binarySearchModels;
        this.maxWidth = getWidth() / binarySearchModels.get(0).getArrTemp().length;
        this.maxHeight = 100;
        this.widthContent = maxWidth * binarySearchModels.get(0).getArrTemp().length;
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
        fontPaint.setTextSize(70);
        fontPaint.setStyle(Paint.Style.FILL);
        paintForCondition.setTextSize(30);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WITH);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

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
        int shiftDown = 100;
        float shiftX = 0;
        float len = 0;

        int length = binarySearchModels.get(0).getArrTemp().length;
        float xStart = (float) (length * getMaxWidth() / 2) + STROKE_WITH;
        AtomicBoolean flag = new AtomicBoolean(true);
        int finalY = y;
        binarySearchModels.forEach(s -> {
            if (s.getCompareElementAndMid().equals("zero")) {
                canvas.drawText("Element is absent " + s.getMidElement(), x+shiftDown, finalY+shiftDown, paintForCondition);
                flag.set(false);
            }
        });

        if (flag.get()) {
            for (int i = 0; i < binarySearchModels.size() - 1; i++) {
                drawArray(canvas, paint, binarySearchModels.get(i).getArrTemp(), x + shiftX, y, getMaxWidth(), getMaxHeight(), binarySearchModels.get(i).getMidElement());
                drawArrow(canvas, paint, xStart, y + getMaxHeight(), xStart, y + getMaxHeight() + shiftDown);
                canvas.drawText(binarySearchModels.get(i).getCompareElementAndMid(), xStart + 10, y + getMaxHeight() + shiftDown / 2, paintForCondition);
                y = y + getMaxHeight() + shiftDown;
                if (i + 1 < binarySearchModels.size()) {
                    len = binarySearchModels.get(i + 1).getArrTemp().length * getMaxWidth();
                }
                shiftX = (widthContent - len) / 2;
            }

            drawArray(canvas, paint, binarySearchModels.get(binarySearchModels.size() - 1).getArrTemp(), x + shiftX, y, getMaxWidth(), getMaxWidth());
        }
    }

    public void drawArrow(Canvas canvas, Paint paint, float xStart, float yStart, float xEnd, float yEnd) {
        paint.setColor(Color.BLACK);
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
        drawTriangle(canvas, paint, xStart, (float) (yEnd - STROKE_WITH * 2.5), 10);
    }

    public void drawArray(Canvas canvas, Paint paint, int[] arr, float x, int y, int width, int height, int midElement) {
        int k = 0;
        float length = width * arr.length + x;
        for (float i = x; i < length; i += width) {
            canvas.drawRect(i, y, i + width, y + height, paint);
            if (arr[k] == midElement) {
                int shift = 5;
                paint.setColor(Color.RED);
                canvas.drawRect(i + shift, y + shift, i + width - shift, y + height - shift, paint);
                paint.setColor(Color.BLACK);
            }
            canvas.drawText(String.valueOf(arr[k]), (float) (i + width * 0.15), (float) (y + height * 0.85), fontPaint);
            k++;
        }
    }

    public void drawArray(Canvas canvas, Paint paint, int[] arr, float x, int y, int width, int height) {
        int k = 0;
        paint.setColor(Color.BLACK);
        float length = width * arr.length + x;
        for (float i = x; i < length; i += width) {
            int shift = 5;
            canvas.drawRect(i, y, i + width, y + height, paint);
            paint.setColor(Color.GREEN);
            canvas.drawRect(i + shift, y + shift, i + width - shift, y + height - shift, paint);
            canvas.drawText(String.valueOf(arr[k]), (float) (i + width * 0.15), (float) (y + height * 0.85), fontPaint);
            k++;
            paint.setColor(Color.BLACK);
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
