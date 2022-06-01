package com.vad.modulchit.pojos;

public class TableNumberNOK {
    private int a;
    private int b;
    private int q;
    private int r;

    private int i;

    private boolean expanded;
    private String extra;

    public TableNumberNOK(int a, int b, int q, int r, int i, String extra) {
        this.a = a;
        this.b = b;
        this.q = q;
        this.r = r;
        this.i=i;

        this.extra = extra;
    }

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

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "TableNumberNOK{" +
                "a=" + a +
                ", b=" + b +
                ", q=" + q +
                ", r=" + r +
                ", i=" + i +
                '}';
    }
}
