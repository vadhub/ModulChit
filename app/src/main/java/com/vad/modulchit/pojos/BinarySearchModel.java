package com.vad.modulchit.pojos;

import java.util.Arrays;
import java.util.Objects;

public class BinarySearchModel {
    private int[] arrTemp;
    private int midElement;
    private String compareElementAndMid;

    public BinarySearchModel(int[] arrTemp, int midElement, String compareElementAndMid) {
        this.arrTemp = arrTemp;
        this.midElement = midElement;
        this.compareElementAndMid = compareElementAndMid;
    }

    public int[] getArrTemp() {
        return arrTemp;
    }

    public void setArrTemp(int[] arrTemp) {
        this.arrTemp = arrTemp;
    }

    public int getMidElement() {
        return midElement;
    }

    public void setMidElement(int midElement) {
        this.midElement = midElement;
    }

    public String getCompareElementAndMid() {
        return compareElementAndMid;
    }

    public void setCompareElementAndMid(String compareElementAndMid) {
        this.compareElementAndMid = compareElementAndMid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinarySearchModel that = (BinarySearchModel) o;
        return midElement == that.midElement && Arrays.equals(arrTemp, that.arrTemp) && Objects.equals(compareElementAndMid, that.compareElementAndMid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(midElement, compareElementAndMid);
        result = 31 * result + Arrays.hashCode(arrTemp);
        return result;
    }
}
