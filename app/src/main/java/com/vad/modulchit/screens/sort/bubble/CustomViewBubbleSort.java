package com.vad.modulchit.screens.sort.bubble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.vad.modulchit.animation.Render;
import com.vad.modulchit.animation.StatusButton;


public class CustomViewBubbleSort extends SurfaceView implements SurfaceHolder.Callback {

    private Render render;

    public Render getRender() {
        return render;
    }

    public CustomViewBubbleSort(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        render = new Render(getHolder());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        render.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        render.setRun(false);
        render = null;
    }



}
