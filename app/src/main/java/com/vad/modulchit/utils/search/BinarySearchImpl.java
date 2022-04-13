package com.vad.modulchit.utils.search;

public class BinarySearchImpl implements BinarySearch{
    @Override
    public int search(int[] arr, int element) {
        int lowIndex = 0;
        int highIndex = arr.length-1;

        int elementPos = -1;

        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            if (element == arr[midIndex]) {
                elementPos = midIndex;
                break;
            } else if (element < arr[midIndex]) {
                highIndex = midIndex-1;
            } else if (element > arr[midIndex]) {
                lowIndex = midIndex+1;
            }
        }
        return elementPos;
    }
}
