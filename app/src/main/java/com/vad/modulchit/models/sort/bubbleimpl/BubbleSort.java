package com.vad.modulchit.models.sort.bubbleimpl;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;

public class BubbleSort implements Sort {

    private final StepRecorder stepRecorder;

    public BubbleSort() {
        stepRecorder = new StepRecorder();
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        stepRecorder.record(arr);
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
}
