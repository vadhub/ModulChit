package com.vad.modulchit;

import org.junit.Test;
import static org.junit.Assert.*;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.quickimpl.QuickSort;

import java.util.List;

public class QuickSortUnitTest {
    int[] arr = {10, 4, 1, 8, 5, 4, 11, 9, 2, 1, 9, 3};
    int[] arrSorted = {1, 1, 2, 3, 4, 4, 5, 8, 9, 9, 10, 11};

    @Test
    public void sort_isCorrect() {
        StepRecorder stepRecorder = new StepRecorder();
        Sort sort = new QuickSort();
        sort.setStepRecorder(stepRecorder);
        List<int[]> steps = sort.sorting(arr).getSteps();
        assertArrayEquals(steps.get(steps.size()-1), arrSorted);
    }
}
