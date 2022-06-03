package com.vad.modulchit.screens.sort.insert;

import com.vad.modulchit.R;
import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.SortFactory;
import com.vad.modulchit.models.sort.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;

public class FragmentInsertSort extends FragmentBubbleSort {

    @Override
    protected Sort getSort() {
        return new SortFactory().createSort(SortType.INSERT_SORT);
    }

    @Override
    public int getTitle() {
        return R.string.insert_sort;
    }
}
