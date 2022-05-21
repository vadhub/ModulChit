package com.vad.modulchit.utils.sort.bubleimpl;

import com.vad.modulchit.utils.sort.SortArray;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements SortArray {

    @Override
    public List<Integer[]> sorting(Integer[] arr) {
        List<Integer[]> steps = new ArrayList<>();
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {

                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    steps.add(arr);
                }
            }
        }
        return steps;
    }

    protected void swap(Integer[] array, int ind1, int ind2) {

    }


}
