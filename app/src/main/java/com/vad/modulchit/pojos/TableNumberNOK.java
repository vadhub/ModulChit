package com.vad.modulchit.pojos;

public class TableNumberNOK {
    private int a;
    private int b;
    private int q;
    private int r;

    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getAn() {
        return a;
    }

    public void setAn(int a) {
        this.a = a;
    }

    public int getBn() {
        return b;
    }

    public void setBn(int b) {
        this.b = b;
    }

    public int getQn() {
        return q;
    }

    public void setQn(int q) {
        this.q = q;
    }

    public int getRn() {
        return r;
    }

    public void setRn(int r) {
        this.r = r;
    }

    public TableNumberNOK() {
    }

    public TableNumberNOK(int a, int b, int q, int r, int i) {
        this.a = a;
        this.b = b;
        this.q = q;
        this.r = r;
        this.i=i;
    }
}
