package com.vad.modulchit.screens.sort.insert;

import com.vad.modulchit.R;
import com.vad.modulchit.animation.common.RenderState;
import com.vad.modulchit.animation.common.SortFactory;
import com.vad.modulchit.animation.common.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;

public class FragmentInsertSort extends FragmentBubbleSort {

    @Override
    protected RenderState getRender() {
        SortFactory sortFactory = new SortFactory();
        customView.setRenderSort(sortFactory.createSort(SortType.INSERT_SORT, customView.getHolder()));
        customView.getRender().start();
        customView.getRender().setButtonIcon(this);
        return customView.getRender();
    }

    @Override
    public int getTitle() {
        return R.string.insert_sort;
    }
}
