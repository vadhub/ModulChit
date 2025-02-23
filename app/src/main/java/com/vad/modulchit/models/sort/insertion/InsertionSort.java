package com.vad.modulchit.models.sort.insertion;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;


public class InsertionSort implements Sort {

    private StepRecorder stepRecorder;

    @Override
    public void setStepRecorder(StepRecorder stepRecorder) {
        this.stepRecorder = stepRecorder;
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        stepRecorder.record(arr);
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > current) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = current;
            stepRecorder.record(arr);
        }
        return stepRecorder;
    }
}
