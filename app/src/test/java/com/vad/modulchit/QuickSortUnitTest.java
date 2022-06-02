package com.vad.modulchit;

import org.junit.Test;
import static org.junit.Assert.*;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.quickimpl.QuickSort;

public class QuickSortUnitTest {
    int[] arr = {10, 4, 1, 8, 5, 4, 11, 9, 2, 1, 9, 3};
    int[] arrSorted = {1, 1, 2, 3, 4, 4, 5, 8, 9, 9, 10, 11};

    @Test
    public void sort_isCorrect() {
        Sort sort = new QuickSort();
        StepRecorder stepRecorder = sort.sorting(arr);
        assertArrayEquals(stepRecorder.getSteps().get(stepRecorder.getSteps().size()-1), arrSorted);
    }
}
