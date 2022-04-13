package com.vad.modulchit;

import org.junit.Test;

import static org.junit.Assert.*;

import com.vad.modulchit.utils.search.BinarySearch;
import com.vad.modulchit.utils.search.BinarySearchImpl;
import com.vad.modulchit.utils.sort.BubbleSort;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void bubblesort_isCorrect() {
        BubbleSort b = new BubbleSort();
        int[] arr = {3, 5, 4, 1, 2};
        List<int[]> arrSort = b.sort(arr);
        List<int[]> arrList = new ArrayList<>();
        arrList.add(new int[]{1, 2, 3, 4, 5});
        assertArrayEquals(arrList.get(0), arrSort.get(0));
    }

    @Test
    public void binarySearch_isCorrect() {
        BinarySearch binarySearch = new BinarySearchImpl();
        int[] arr = {1, 2, 4, 5, 7, 9};
        int elem = binarySearch.search(arr, 5);
        System.out.println(elem);
        assertEquals(3, binarySearch.search(arr, 5));
    }
}