package com.vad.modulchit.screens.sort.quick;

import com.vad.modulchit.R;
import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.SortFactory;
import com.vad.modulchit.models.sort.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;

public class FragmentQuickSort extends FragmentBubbleSort {

    @Override
    protected Sort getSort() {
        return new SortFactory().createSort(SortType.QUICK_SORT);
    }

    @Override
    public int getTitle() {
        return R.string.quick_sort;
    }
}
