package com.vad.modulchit.screens.sort.shell;

import com.vad.modulchit.R;
import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.SortFactory;
import com.vad.modulchit.models.sort.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;


public class FragmentShellSort extends FragmentBubbleSort {

    @Override
    protected Sort getSort() {
        StepRecorder stepRecorder = new StepRecorder();
        return new SortFactory().createSort(SortType.SHELL_SORT, stepRecorder);
    }

    @Override
    public int getTitle() {
        return R.string.shell_sort;
    }
}