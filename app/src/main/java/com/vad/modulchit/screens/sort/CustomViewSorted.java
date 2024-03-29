package com.vad.modulchit.screens.sort;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.vad.modulchit.models.animation.common.RenderSort;


public class CustomViewSorted extends SurfaceView implements SurfaceHolder.Callback {

    private RenderSort renderSort;

    public RenderSort getRender() {
        return renderSort;
    }

    public CustomViewSorted(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        renderSort = new RenderSort(getHolder());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        renderSort = new RenderSort(getHolder());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        renderSort = new RenderSort(getHolder());
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        renderSort.setStatePause();
        renderSort = null;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}
