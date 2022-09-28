package com.vad.modulchit.models.pojos;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TableNumberGCDe {

    private final int a;
    private final int b;
    private final int q;
    private final int r;

    private final int x1;
    private final int x2;

    private final int y1;
    private final int y2;

    private final String extra;

    //state of the element
    private boolean isExpand;

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

    @SuppressLint("SetTextI18n")
    public void fillRowTable(TextView textViewA, TextView textViewB, TextView textViewQ, TextView textViewR, TextView textViewX, TextView textViewY, TextView textViewExtra) {
        textViewA.setText(a + "");
        textViewB.setText(b + "");
        textViewQ.setText(q + "");
        textViewR.setText(r + "");
        textViewX.setText("(" + x1 + "; " + x2 + ")");
        textViewY.setText("(" + y1 + "; " + y2 + ")");
        textViewExtra.setText(extra);
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void rollUp() {
        isExpand = !isExpand;
    }

    public boolean extraIsEmpty() {
        return extra.equals("");
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
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
