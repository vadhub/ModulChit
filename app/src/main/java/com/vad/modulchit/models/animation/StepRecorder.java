package com.vad.modulchit.models.animation;

import java.util.ArrayList;
import java.util.List;

public class StepRecorder {

    private List<int[]> steps;
    private int[] array;

    public StepRecorder() {
        steps = new ArrayList<>();
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

    public void setSteps(List<int[]> steps) {
        this.steps = steps;
    }
}
