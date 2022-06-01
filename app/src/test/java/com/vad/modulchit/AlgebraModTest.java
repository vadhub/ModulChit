package com.vad.modulchit;

import static org.junit.Assert.assertEquals;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.models.AlgebraMod;
import com.vad.modulchit.pojos.TableNumberNOK;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AlgebraModTest {
    List<TableNumberGCDe> gcdeList;
    List<TableNumberFE> feList;
    List<TableNumberNOK> noks;

    @Before
    public void setUp() {
        AlgebraMod a = new AlgebraMod();
        gcdeList = a.gcdGraph(180, 150);
        feList = a.feGraph(122, 342, 212);
        noks = a.nokGraph(42);
    }

    @Test
    public void gcd_isCorrect() {
        int expected = 30;
        int input = AlgebraMod.gcd(180, 150);
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefAEnd_isCorrect() {
        int expected = 150;
        int input = gcdeList.get(gcdeList.size()-1).getA();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefBEnd_isCorrect() {
        int expected = 30;
        int input = gcdeList.get(gcdeList.size()-1).getB();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefQEnd_isCorrect() {
        int expected = 5;
        int input = gcdeList.get(gcdeList.size()-1).getQ();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefREnd_isCorrect() {
        int expected = 0;
        int input = gcdeList.get(gcdeList.size()-1).getR();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefY2End_isCorrect() {
        int expected = -1;
        int input = gcdeList.get(gcdeList.size()-1).getY2();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefY1End_isCorrect() {
        int expected = 1;
        int input = gcdeList.get(gcdeList.size()-1).getY1();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefX1End_isCorrect() {
        int expected = 0;
        int input = gcdeList.get(gcdeList.size()-1).getX1();
        assertEquals(expected, input);
    }

    @Test
    public void gcdeCoefX2End_isCorrect() {
        int expected = 1;
        int input = gcdeList.get(gcdeList.size()-1).getX2();
        assertEquals(expected, input);
    }

    @Test
    public void feResult_isCorrect() {
        int expected = 28;
        int input = feList.get(feList.size()-2).getP();
        assertEquals(expected, input);
    }

    @Test
    public void nokResult_isCorrect() {
        int expected = 1;
        int input = noks.get(noks.size() - 5).getAn();
        assertEquals(expected, input);
    }
}
