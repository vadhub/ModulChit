package com.vad.modulchit.animation;

import android.view.SurfaceHolder;

import com.vad.modulchit.screens.sort.bubble.RenderBubbleSort;
import com.vad.modulchit.screens.sort.shell.RenderShellSort;

public class SortFactory {
    public RenderSort createSort(SortType type, SurfaceHolder surfaceHolder) {
        RenderSort renderSort = null;

        switch (type) {
            case BUBBLE_SORT:
                renderSort = new RenderBubbleSort(surfaceHolder);
                break;
            case SHELL_SORT:
                renderSort = new RenderShellSort(surfaceHolder);
                break;
        }

        return renderSort;
    }
}
