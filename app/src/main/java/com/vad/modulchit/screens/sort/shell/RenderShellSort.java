package com.vad.modulchit.screens.sort.shell;

import android.view.SurfaceHolder;

import com.vad.modulchit.animation.RenderSort;

public class RenderShellSort extends RenderSort {

    private int[] array;

    public RenderShellSort(SurfaceHolder mSurfaceHolder) {
        super(mSurfaceHolder);
        array = getArr();
    }

    @Override
    public void sort() {
        int gap = array.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        swap(array, c, c + gap);
                    }
                }
            }
            gap = gap / 2;
        }
    }
}
