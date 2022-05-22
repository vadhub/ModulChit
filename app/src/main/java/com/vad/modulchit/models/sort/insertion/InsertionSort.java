package com.vad.modulchit.models.sort.insertion;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.models.sort.SortArray;


public class InsertionSort implements SortArray {

    private final StepRecorder stepRecorder;

    public InsertionSort() {
        stepRecorder = new StepRecorder();
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i;
            stepRecorder.record(arr);
            while (j > 0 && arr[j - 1] > current) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = current;
        }
        return stepRecorder;
    }
}
