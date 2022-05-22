package com.vad.modulchit.screens.sort.shell;

import com.vad.modulchit.R;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.SortFactory;
import com.vad.modulchit.models.sort.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;


public class FragmentShellSort extends FragmentBubbleSort {

    @Override
    protected Sort getSort() {
        return new SortFactory().createSort(SortType.SHELL_SORT);
    }

    @Override
    public int getTitle() {
        return R.string.shell_sort;
    }
}