package com.vad.modulchit.models.pojos;

import java.util.Arrays;
import java.util.Objects;

public class BinarySearchModel {
    private int[] arr;
    private int low;
    private int mid;
    private int high;
    private String compareElementAndMid;

    public BinarySearchModel(int[] arrTemp, int midElement, String compareElementAndMid) {
        this.arr = arrTemp;
        this.mid = midElement;
        this.compareElementAndMid = compareElementAndMid;
    }

    public BinarySearchModel(int[] arrTemp, int low, int midElement, int high, String compareElementAndMid) {
        this.arr = arrTemp;
        this.low = low;
        this.mid = midElement;
        this.high = high;
        this.compareElementAndMid = compareElementAndMid;
    }


    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
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
        return mid == that.mid && Arrays.equals(arr, that.arr) && Objects.equals(compareElementAndMid, that.compareElementAndMid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mid, compareElementAndMid);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    @Override
    public String toString() {
        return "BinarySearchModel{" +
                "arr=" + Arrays.toString(arr) +
                ", low=" + low +
                ", mid=" + mid +
                ", high=" + high +
                ", compareElementAndMid='" + compareElementAndMid + '\'' +
                '}';
    }
}
