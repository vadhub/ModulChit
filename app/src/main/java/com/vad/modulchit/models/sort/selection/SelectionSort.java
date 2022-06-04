package com.vad.modulchit.models.sort.selection;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;

public class SelectionSort implements Sort {

    private StepRecorder stepRecorder;

    @Override
    public void setStepRecorder(StepRecorder stepRecorder) {
        this.stepRecorder = stepRecorder;
    }

    @Override
    public StepRecorder sorting(int[] array) {
        stepRecorder.record(array);
        for (int i = 0; i < array.length; i++) {
            int pos = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    pos = j;
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;
            stepRecorder.record(array);
        }
        return stepRecorder;
    }
}
