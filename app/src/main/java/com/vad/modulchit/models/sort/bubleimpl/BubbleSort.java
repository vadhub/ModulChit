package com.vad.modulchit.models.sort.bubleimpl;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.models.sort.SortArray;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortArray {

    private StepRecorder stepRecorder;

    public BubbleSort(StepRecorder stepRecorder) {
        this.stepRecorder = stepRecorder;
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    stepRecorder.record(arr);
                }
            }
        }
        return stepRecorder;
    }

    protected void swap(Integer[] array, int ind1, int ind2) {

    }


}
