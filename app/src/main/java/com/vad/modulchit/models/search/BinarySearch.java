package com.vad.modulchit.models.search;

import com.vad.modulchit.models.pojos.BinarySearchModel;

import java.util.List;

public interface BinarySearch {
    List<BinarySearchModel> search(int[] arr, int element);
}
