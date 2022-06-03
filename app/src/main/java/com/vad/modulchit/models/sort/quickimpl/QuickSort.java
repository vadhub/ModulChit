package com.vad.modulchit.models.sort.quickimpl;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.sort.Sort;

public class QuickSort implements Sort {

    private StepRecorder recorder;

    @Override
    public void setStepRecorder(StepRecorder stepRecorder) {
        this.recorder = stepRecorder;
    }

    @Override
    public StepRecorder sorting(int[] arr) {
        recorder.record(arr);
        quickSort(arr, 0, arr.length-1);
        return recorder;
    }

    public void quickSort(int[] arr, int from, int to) {

        if (from < to) {

            int divideIndex = partition(arr, from, to);

            quickSort(arr, from, divideIndex - 1);

            quickSort(arr, divideIndex, to);
        }
    }

    private  int partition(int[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from + (to - from) / 2];
        while (leftIndex <= rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }

            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private void swap(int[] array, int index1, int index2) {
        int tmp  = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
        recorder.record(array);
    }
}
