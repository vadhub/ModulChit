package com.vad.modulchit;

import static org.junit.Assert.assertEquals;

import com.vad.modulchit.utils.AlgebraMod;

import org.junit.Test;

public class AlgebraModTest {
    int[] arr = {1, 2, 3, 0, -1, 4};

    @Test
    public void searchMaxElement_isCorrect() {
        assertEquals(4, AlgebraMod.max(arr));
    }
}
