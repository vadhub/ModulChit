package com.vad.modulchit.screens.sort.shell;



import com.vad.modulchit.R;
import com.vad.modulchit.animation.RenderState;
import com.vad.modulchit.animation.SortFactory;
import com.vad.modulchit.animation.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;


public class FragmentShellSort extends FragmentBubbleSort {

    @Override
    protected RenderState getRender() {
        SortFactory sortFactory = new SortFactory();
        customView.setRenderSort(sortFactory.createSort(SortType.SHELL_SORT, customView.getHolder()));
        customView.getRender().start();
        customView.getRender().setButtonIcon(this);
        return customView.getRender();
    }

    @Override
    public int getTitle() {
        return R.string.shell_sort;
    }
}