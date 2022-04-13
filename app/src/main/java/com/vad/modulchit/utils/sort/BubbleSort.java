package com.vad.modulchit.utils.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleSort implements Sortable{
    // 9 3 5 7 4 6 8 -> 3 4 5 6 7 8 9
    @Override
    public List<int[]> sort(int[] arr) {
        int temp = 0;
        List<int[]> sortList = new ArrayList<>();
        for (int i = arr.length - 1; i >= 1; i--) {
            sortList.add(arr);
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return sortList;
    }
}
