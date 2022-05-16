package com.vad.modulchit.screens.sort.insert;

import android.view.SurfaceHolder;

import com.vad.modulchit.animation.RenderSort;

public class RenderInsertSort extends RenderSort {

    public RenderInsertSort(SurfaceHolder mSurfaceHolder) {
        super(mSurfaceHolder);
    }

    @Override
    public void sort() {
        for (int left = 0; left < getArr().length; left++) {
            int value = getArr()[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < getArr()[i]) {
                    getArr()[i + 1] = getArr()[i];
                } else {
                    break;
                }
            }
            getArr()[i + 1] = value;
        }
    }
}
