package com.vad.modulchit.screens.sort.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class CustomViewBubbleSort extends SurfaceView implements SurfaceHolder.Callback {

    private Render render;

    public Render getRender() {
        return render;
    }

    public CustomViewBubbleSort(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        render = new Render(getHolder());
        render.setRun(true);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        render = null;
    }

    public void cleanup() {
        render = null;
    }


}
