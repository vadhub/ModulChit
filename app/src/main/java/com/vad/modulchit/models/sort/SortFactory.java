package com.vad.modulchit.models.sort;

import com.vad.modulchit.models.sort.bubbleimpl.BubbleSort;
import com.vad.modulchit.models.sort.insertion.InsertionSort;
import com.vad.modulchit.models.sort.quickimpl.QuickSort;
import com.vad.modulchit.models.sort.selection.SelectionSort;
import com.vad.modulchit.models.sort.shellimpl.ShellSort;

public class SortFactory {

    public Sort createSort(SortType type) {
        Sort sort = null;

        switch (type) {
            case BUBBLE_SORT:
                sort = new BubbleSort();
                break;
            case SHELL_SORT:
                sort = new ShellSort();
                break;
            case INSERT_SORT:
                sort = new InsertionSort();
                break;
            case QUICK_SORT:
                sort = new QuickSort();
                break;
            case  SELECT_SORT:
                sort = new SelectionSort();
        }

        return sort;
    }
}
