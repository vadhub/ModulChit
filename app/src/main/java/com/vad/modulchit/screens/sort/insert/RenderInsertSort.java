package com.vad.modulchit.screens.sort.insert;

import android.view.SurfaceHolder;

import com.vad.modulchit.animation.RenderSort;


public class RenderInsertSort extends RenderSort {

    public RenderInsertSort(SurfaceHolder mSurfaceHolder) {
        super(mSurfaceHolder);
    }

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;
            draw(array, i);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (j > 0 && array[j - 1] > current) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = current;
        }
    }
}
