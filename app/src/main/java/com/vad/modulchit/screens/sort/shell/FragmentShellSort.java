package com.vad.modulchit.screens.sort.shell;



import com.vad.modulchit.R;
import com.vad.modulchit.animation.common.RenderSort;
import com.vad.modulchit.animation.common.SortFactory;
import com.vad.modulchit.animation.common.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;


public class FragmentShellSort extends FragmentBubbleSort {

    @Override
    protected RenderSort getRender() {
        SortFactory sortFactory = new SortFactory();
        customView.setRenderSort(sortFactory.createSort(SortType.SHELL_SORT, customView.getHolder()));
        customView.getRender().setButtonIcon(this);
        return customView.getRender();
    }

    @Override
    public int getTitle() {
        return R.string.shell_sort;
    }
}