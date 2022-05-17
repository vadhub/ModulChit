package com.vad.modulchit.screens.sort;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.vad.modulchit.animation.RenderSort;


public class CustomViewSorted extends SurfaceView implements SurfaceHolder.Callback {

    private RenderSort renderSort;

    public void setRenderSort(RenderSort renderSort) {
        this.renderSort = renderSort;
    }

    public RenderSort getRender() {
        return renderSort;
    }

    public CustomViewSorted(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        renderSort.stopAnimation();
        renderSort.setRun(false);
        try {
            renderSort.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        renderSort = null;
    }



}
