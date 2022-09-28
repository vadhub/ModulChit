package com.vad.modulchit.models.pojos;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TableNumberFE {
    private final int a; //number base degree
    private final int m; //degree
    private final int n; //z mod
    private final int p; //default p = 1 if r = 1 to p=prev p *a else p = prev p
    private final int r; // 0 if m%2 = 0 else 1

    private final String extra;

    //state of the element
    private boolean isExpand;

    public TableNumberFE(int a, int m, int n, int p, int r, String extra) {
        this.a = a;
        this.m = m;
        this.n = n;
        this.p = p;
        this.r = r;

        this.extra = extra;
    }

    public TableNumberFE() {
        this.a = -1;
        this.m = -1;
        this.n = -1;
        this.p = -1;
        this.r = -1;
        this.extra = "";
    }

    @SuppressLint("SetTextI18n")
    public void fillRowTable(TextView textViewA, TextView textViewM, TextView textViewN, TextView textViewP, TextView textViewR, TextView textViewExtra) {
        textViewA.setText(a + "");
        textViewM.setText(m + "");
        textViewN.setText(n + "");
        textViewP.setText(p + "");
        textViewR.setText(r + "");

        textViewExtra.setText(extra);

        if (a == -1 && n == -1) {
            textViewA.setText("");
            textViewM.setText("");
            textViewN.setText("");
            textViewP.setText("");
            textViewR.setText("");
        }
    }

    public int getA() {
        return a;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getP() {
        return p;
    }

    public int getR() {
        return r;
    }

    public boolean extraIsEmpty() {
        return extra.equals("");
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void rollUp() {
        isExpand = !isExpand;
    }

    @Override
    public String toString() {
        return "TableNumberFE{" +
                "a=" + a +
                ", m=" + m +
                ", n=" + n +
                ", p=" + p +
                ", r=" + r +
                '}';
    }
}
