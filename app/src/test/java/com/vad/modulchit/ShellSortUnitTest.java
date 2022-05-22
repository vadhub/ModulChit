package com.vad.modulchit;

import android.view.SurfaceHolder;

import com.vad.modulchit.models.sort.shellimpl.ShellSort;

import org.junit.Test;

import java.util.Arrays;

public class ShellSortUnitTest {
    int[] arr = {10, 4, 1, 8, 5, 4, 11, 9, 2, 1, 9, 3};
    int[] arrSorted = {1, 1, 2, 3, 4, 4, 5, 8, 9, 9, 10, 11};

    @Test
    public void shellSort_isCorrect() {
        SurfaceHolder surfaceHolder = null;
        ShellSort sort = new ShellSort();

    }
}
