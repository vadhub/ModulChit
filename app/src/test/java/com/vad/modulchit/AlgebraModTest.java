package com.vad.modulchit;

import static org.junit.Assert.assertEquals;

import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.models.AlgebraMod;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AlgebraModTest {
    int[] arr = {1, 2, 3, 0, -1, 4};
    List<TableNumberGCDe> gcdeList;

    @Before
    public void setUp() {
        AlgebraMod a = new AlgebraMod();
        gcdeList = a.gcdGraph(180, 150);
    }

    @Test
    public void gcd_isCorrect() {
        assertEquals(30, AlgebraMod.gcd(180, 150));
    }

    @Test
    public void gcdeCoefY2_isCorrect() {
        assertEquals(-1, gcdeList.get(gcdeList.size()-1).getY2());
    }

    @Test
    public void gcdeCoefY1_isCorrect() {
        assertEquals(1, gcdeList.get(gcdeList.size()-1).getY1());
    }
}
