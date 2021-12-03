package com.vad.modulchit.utils.sort;

public class BubbleSort implements Sortable{
    // 9 3 5 7 4 6 8 -> 3 4 5 6 7 8 9
    @Override
    public int[] sort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr;
    }
}
