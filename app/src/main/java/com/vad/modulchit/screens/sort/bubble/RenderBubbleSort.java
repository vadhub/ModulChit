package com.vad.modulchit.screens.sort.bubble;

import com.vad.modulchit.animation.SortArray;
import com.vad.modulchit.pojos.StepSort;

import java.util.ArrayList;
import java.util.List;

public class RenderBubbleSort implements SortArray {

    @Override
    public List<StepSort> sort(int[] arr) {
        List<StepSort> steps = new ArrayList<>();
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    steps.add(new StepSort(arr, j, j+1));
                    swap(arr, j, j+1);
                }
            }
        }
        return steps;
    }

    protected void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }


}
