package com.vad.modulchit.screens.sort.bubble;

import android.view.SurfaceHolder;

import com.vad.modulchit.animation.RenderSort;
import com.vad.modulchit.animation.StatusAnimation;


public class RenderBubbleSort extends RenderSort {

    public RenderBubbleSort(SurfaceHolder mSurfaceHolder) {
        super(mSurfaceHolder);
    }

    @Override
    public void sort(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {

            if (getStatusAnimation() == StatusAnimation.PAUSE) {
                break;
            }
            for (int j = 0; j < i; j++) {
                if (getStatusAnimation() == StatusAnimation.PAUSE) {
                    break;
                }
                draw(arr, getSurfaceHolder(), j);

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }
}
