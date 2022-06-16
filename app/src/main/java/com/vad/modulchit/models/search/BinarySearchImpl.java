package com.vad.modulchit.models.search;

import com.vad.modulchit.models.pojos.BinarySearchModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchImpl implements BinarySearch{
    @Override
    public List<BinarySearchModel> search(int[] arr, int element) {

        List<BinarySearchModel> binarySearchModel = new ArrayList<>();

        int[] tempArr = arr;
        int low = 0;
        int high = arr.length;
        int tempHigh = arr.length;
        int elementPos = -1;
        String mark = "";

        while (low <= high) {

            int midIndex = low + (high - low) / 2;

            if (midIndex >= arr.length) {
                binarySearchModel.add(new BinarySearchModel(new int[]{0}, element, "zero"));
                return binarySearchModel;
            }

            if (element < arr[midIndex]) {
                high = midIndex - 1;
                tempHigh = midIndex;
                mark = " < ";
            }

            if (element > arr[midIndex]) {
                low = midIndex + 1;
                mark = " > ";
            }

            binarySearchModel.add(new BinarySearchModel(tempArr, arr[midIndex], element + mark + arr[midIndex]));
            mark = " = ";

            if (element == arr[midIndex]) {
                elementPos = midIndex;
                break;
            }

            tempArr = Arrays.copyOfRange(arr, low, tempHigh);
        }

        if(elementPos == -1) {
            binarySearchModel.add(new BinarySearchModel(new int[]{0}, element, "zero"));
            return binarySearchModel;
        }
        binarySearchModel.add(new BinarySearchModel(new int[]{arr[elementPos]}, arr[elementPos], element + mark + arr[elementPos]));

        return binarySearchModel;

    }
}
