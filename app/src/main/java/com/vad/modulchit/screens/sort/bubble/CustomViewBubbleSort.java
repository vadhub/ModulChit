package com.vad.modulchit.screens.sort.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.vad.modulchit.utils.sort.BubbleSort;

public class CustomViewBubbleSort extends SurfaceView implements SurfaceHolder.Callback {

    private int[] arr;
    private Render render;
    private BubbleSort bubbleSort = new BubbleSort();

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
        invalidate();
    }

    public CustomViewBubbleSort(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (arr != null) {
            render = new Render(getHolder(), bubbleSort, arr);
            render.setRunning(true);
            render.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        render.cleanup();
        render = null;

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }


}
