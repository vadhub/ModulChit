package com.vad.modulchit;

import org.junit.Test;

import static org.junit.Assert.*;

import com.vad.modulchit.utils.sort.BubbleSort;

import java.util.Arrays;

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

    @Test public void bubblesort_isCorrect() {
        BubbleSort b = new BubbleSort();
        int[] arr = {3, 5, 4, 1, 2};
        int[] arrSort = b.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arrSort);
    }
}