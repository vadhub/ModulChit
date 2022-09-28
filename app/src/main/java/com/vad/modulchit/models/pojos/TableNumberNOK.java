package com.vad.modulchit.models.pojos;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class TableNumberNOK {
    private final int a;
    private final int b;
    private final int q;
    private final int r;

    private final int i;

    private boolean isExpand;
    private final String extra;

    public TableNumberNOK(int a, int b, int q, int r, int i, String extra) {
        this.a = a;
        this.b = b;
        this.q = q;
        this.r = r;
        this.i = i;

        this.extra = extra;
    }

    public TableNumberNOK() {
        this.a = -1;
        this.b = -1;
        this.q = -1;
        this.r = -1;
        this.i = -1;
        this.extra = "end";
    }

    public int getI() {
        return i;
    }

    public int getAn() {
        return a;
    }

    public int getBn() {
        return b;
    }

    public int getRn() {
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

    @SuppressLint("SetTextI18n")
    public void fillRowTable(TextView textViewAn, TextView textViewBn, TextView textViewQn, TextView textViewRn, TextView textViewExtra) {
        textViewAn.setText(a + "");
        textViewBn.setText(b + "");
        textViewQn.setText(q + "");
        textViewRn.setText(r + "");

        textViewExtra.setText(extra);

        if (b == -1) {
            textViewAn.setText(" ");
            textViewBn.setText(" ");
            textViewQn.setText(" ");
            textViewRn.setText(" ");
        }
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
