package com.vad.modulchit.models.pojos;

public class TableNumberGCDe {

    private int a;
    private int b;
    private int q;
    private int r;

    private int x1;
    private int x2;

    private int y1;
    private int y2;

    private String extra;

    //state of the element
    private boolean expanded;

    public TableNumberGCDe(int a, int b, int q, int r, int x1, int x2, int y1, int y2, String extra) {
        this.a = a;
        this.b = b;
        this.q = q;
        this.r = r;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        this.extra = extra;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isExpanded(){
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "TableNumberGCDe{" +
                "a=" + a +
                ", b=" + b +
                ", q=" + q +
                ", r=" + r +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                '}';
    }
}