package com.vad.modulchit.screens.sort.shell;

import android.view.SurfaceHolder;

import com.vad.modulchit.animation.RenderSort;

public class RenderShellSort extends RenderSort {

    public RenderShellSort(SurfaceHolder mSurfaceHolder) {
        super(mSurfaceHolder);
    }

    @Override
    public void sort() {
        int gap = getArr().length / 2;
        while (gap >= 1) {
            for (int right = 0; right < getArr().length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (getArr()[c] > getArr()[c + gap]) {
                        draw(getArr(), getSurfaceHolder(), c);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        swap(getArr(), c, c + gap);
                    }
                }
            }
            gap = gap / 2;
        }
    }
}
