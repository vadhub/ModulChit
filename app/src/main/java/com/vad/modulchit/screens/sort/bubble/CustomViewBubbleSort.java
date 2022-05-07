package com.vad.modulchit.screens.sort.bubble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


public class CustomViewBubbleSort extends SurfaceView implements SurfaceHolder.Callback {

    private int[] arr;
    private Render render;

    public Render getRender() {
        return render;
    }

    public void setRender(Render render) {
        this.render = render;
    }

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
        System.out.println(" 44444 "+arr);
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


}
