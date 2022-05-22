package com.vad.modulchit.models.sort.shellimpl;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.models.sort.SortArray;

public class ShellSort implements SortArray {

    private final StepRecorder stepRecorder;

    public ShellSort() {
        stepRecorder = new StepRecorder();
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        int gap = arr.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < arr.length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (arr[c] > arr[c + gap]) {
                        int tmp = arr[c];
                        arr[c] = arr[c+gap];
                        arr[c+gap] = tmp;
                        stepRecorder.record(arr);
                    }
                }
            }
            gap = gap / 2;
        }
        return stepRecorder;
    }
}
