package com.vad.modulchit.models.sort.shellimpl;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;

public class ShellSort implements Sort {

    private StepRecorder stepRecorder;

    @Override
    public void setStepRecorder(StepRecorder stepRecorder) {
        this.stepRecorder = stepRecorder;
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        stepRecorder.record(arr);
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
