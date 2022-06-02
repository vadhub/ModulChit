package com.vad.modulchit.models.pojos;

public class StepSort {
    private int[] arr;
    private int current;
    private int swapped;

    public StepSort(int[] arr, int current, int swapped) {
        this.arr = arr;
        this.current = current;
        this.swapped = swapped;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSwapped() {
        return swapped;
    }

    public void setSwapped(int swapped) {
        this.swapped = swapped;
    }
}
