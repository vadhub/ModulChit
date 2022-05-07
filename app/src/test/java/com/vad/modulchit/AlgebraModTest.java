package com.vad.modulchit;

import static org.junit.Assert.assertEquals;

import com.vad.modulchit.utils.AlgebraMod;

import org.junit.Test;

public class AlgebraModTest {
    int[] arr = {1, 2, 3, 0, -1, 4};

    @Test
    public void searchMinElement_isCorrect() {
        assertEquals(-1, AlgebraMod.min(arr));
    }
}
