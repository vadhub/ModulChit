package com.vad.modulchit.pojos;

public class TableNumberFE {
    private int a; //number base degree
    private int m; //degree
    private int n; //z mod
    private int p; //default p = 1 if r = 1 to p=prev p *a else p = prev p
    private int r; // 0 if m%2 = 0 else 1

    private String extra;

    //state of the element
    private boolean expanded;

    public TableNumberFE(int a, int m, int n, int p, int r, String extra) {
        this.a = a;
        this.m = m;
        this.n = n;
        this.p = p;
        this.r = r;

        this.extra = extra;
    }

    public int getAfe() {
        return a;
    }

    public void setAfe(int a) {
        this.a = a;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getRfe() {
        return r;
    }

    public void setRfe(int r) {
        this.r = r;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
