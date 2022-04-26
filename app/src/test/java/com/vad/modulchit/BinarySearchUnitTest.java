package com.vad.modulchit;

import com.vad.modulchit.pojos.BinarySearchModel;
import com.vad.modulchit.utils.search.BinarySearchImpl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchUnitTest {
    BinarySearchImpl binarySearch = new BinarySearchImpl();
    int[] arr = {1, 2, 3, 4, 5, 6, 9, 10};
    int element = 4;
    List<BinarySearchModel> searchModelList = new ArrayList<>();
    List<int[]> tempArr = new ArrayList<>();

    @Before
    public void setUp() {
        searchModelList.add(new BinarySearchModel(new int[]{1, 2, 3, 4, 5, 6, 9, 10}, 5, "4 < 5"));
        searchModelList.add(new BinarySearchModel(new int[]{1, 2, 3, 4}, 2, "4 > 2"));
        searchModelList.add(new BinarySearchModel(new int[]{3, 4}, 3, "4 > 3"));
        searchModelList.add(new BinarySearchModel(new int[]{4}, 4, "4 = 4"));
        searchModelList.add(new BinarySearchModel(new int[]{4}, 4, "4 = 4"));

        searchModelList.forEach(it -> tempArr.add(it.getArrTemp()));
    }

    @Test
    public void binarySearch_isCorrect() {
        assertEquals(binarySearch.search(arr, element), searchModelList);
    }

    @Test
    public void binarySearchTempArray_isCorrect() {
        List<int[]> tempar = new ArrayList<>();
        binarySearch.search(arr, element).forEach(it -> tempar.add(it.getArrTemp()));

        for (int i = 0; i < tempar.size(); i++) {
            assertEquals(Arrays.toString(tempar.get(i)), Arrays.toString(tempArr.get(i)));
        }
    }
}
