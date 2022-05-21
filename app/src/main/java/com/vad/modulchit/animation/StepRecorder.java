package com.vad.modulchit.animation;

import java.util.List;

public class StepRecorder {

    private List<int[]> steps;
    private int[] array;

    public StepRecorder(List<int[]> steps) {
        this.steps = steps;
    }

    public int[] copyArray(int[] arr) {
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    public void record(int[] arr) {
        array = copyArray(arr);
        steps.add(array);
    }

    public List<int[]> getSteps() {
        return steps;
    }
}
