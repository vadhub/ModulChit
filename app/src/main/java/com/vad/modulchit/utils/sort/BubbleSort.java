package com.vad.modulchit.utils.sort;

import java.util.Arrays;

public class BubbleSort implements Sortable{
    // 9 3 5 7 4 6 8 -> 3 4 5 6 7 8 9
    @Override
    public int[] sort(int[] arr) {
        int temp = 0;
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                System.out.println(Arrays.toString(arr));
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }
}
