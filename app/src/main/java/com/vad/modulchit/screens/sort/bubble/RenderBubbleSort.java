package com.vad.modulchit.screens.sort.bubble;

import android.graphics.Color;
import android.view.SurfaceHolder;

import com.vad.modulchit.animation.Render;
import com.vad.modulchit.animation.StatusAnimation;


public class RenderBubbleSort extends Render {

    public RenderBubbleSort(SurfaceHolder mSurfaceHolder) {
        super(mSurfaceHolder);
    }

    @Override
    public void sorted() {
        int temp = 0;
        int[] arr = getArr();

        if (arr != null && getStatusAnimation() == StatusAnimation.START) {

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
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }

            getPaint().setColor(Color.BLUE);
            draw(arr, getSurfaceHolder(), -1);
            setStatusAnimation(StatusAnimation.STOP);
            getButtonIconChange().setButtonStatus();
            setArr(null);
        }

    }
}
