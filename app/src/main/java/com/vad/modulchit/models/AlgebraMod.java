package com.vad.modulchit.models;

import android.util.Pair;

import com.vad.modulchit.models.pojos.TableNumberFE;
import com.vad.modulchit.models.pojos.TableNumberGCDe;
import com.vad.modulchit.models.pojos.TableNumberNOK;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class AlgebraMod {

    private final StringExtraData extraData = new StringExtraData();

    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public List<TableNumberGCDe> gcdGraph(int a, int b) {
        List<TableNumberGCDe> tempTableNumberGCDes = new ArrayList<>();
        TableNumberGCDe tableNumberGCDe;

        String extra = "";

        int aTemp = 0;
        int bTemp = 0;
        int tmpQ = 0;
        int tmpR = 0;

        int x1 = 1;
        int x2 = 0;

        int y1 = 0;
        int y2 = 1;

        if (a < b) {
            aTemp = b;
            bTemp = a;
            tmpR = aTemp;
            tableNumberGCDe = new TableNumberGCDe(a, b, tmpQ, tmpR, x1, x2, y1, y2, extra);
            tempTableNumberGCDes.add(tableNumberGCDe);
            extra = "a = " + aTemp + ";\n" + "b = " + bTemp + ";\n";
            x1 = 0;
            x2 = 1;
            y1 = 1;
            y2 = 0;

        } else {
            aTemp = a;
            bTemp = b;
        }
        do {

            int tmpX1 = x1;
            int tmpX2 = x2;

            tmpR = aTemp % bTemp;
            tmpQ = aTemp / bTemp;

            tableNumberGCDe = new TableNumberGCDe(aTemp, bTemp, tmpQ, tmpR, x1, x2, y1, y2, extra);
            tempTableNumberGCDes.add(tableNumberGCDe);

            aTemp = tableNumberGCDe.getB();
            bTemp = tableNumberGCDe.getR();

            extra = extraData.extraGCDE(aTemp, bTemp, tmpQ, tmpR, x1, x2, y1, y2);

            x1 = tableNumberGCDe.getY1();
            x2 = tableNumberGCDe.getY2();

            y1 = tmpX1 - tmpQ * y1;
            y2 = tmpX2 - tmpQ * y2;

        } while (bTemp != 0);

        return tempTableNumberGCDes;
    }

    public List<TableNumberNOK> nokGraph(int mod) {
        List<TableNumberNOK> tempTableNumberNOKs = new ArrayList<>();
        TableNumberNOK tableNumberNOK;

        String extra = "";
        int a = 0;
        int b = 0;
        int q = 0;
        int r = 0;

        for (int i = 2; i <= mod; i++) {
            a = mod;
            b = i;
            q = a / b;
            r = a % b;

            extra = extraData.extraGCD(a, b, q, r);
            tableNumberNOK = new TableNumberNOK(a, b, q, r, i, extra);
            tempTableNumberNOKs.add(tableNumberNOK);
            while (true) {
                if (r == 0) {
                    tableNumberNOK = new TableNumberNOK(gcd(mod, i), 0, 0, 0, i, "exit from loop");
                    tempTableNumberNOKs.add(tableNumberNOK);
                    break;
                }

                a = tableNumberNOK.getBn();
                b = tableNumberNOK.getRn();

                q = a / b;
                r = a % b;

                extra = extraData.extraGCD(a, b, q, r);
                tableNumberNOK = new TableNumberNOK(a, b, q, r, i, extra);
                tempTableNumberNOKs.add(tableNumberNOK);
            }

            tableNumberNOK = new TableNumberNOK();
            tempTableNumberNOKs.add(tableNumberNOK);
        }
        return tempTableNumberNOKs;
    }

    public Pair<List<TableNumberNOK>, String> getResult(List<TableNumberNOK> noks) {
        StringBuilder txtRes = new StringBuilder("1");

        int tmp = 0;
        for (TableNumberNOK nok : noks) {
            if (nok.getBn() == 0 && nok.getAn() == 1) {
                if (tmp != nok.getI()) {
                    txtRes.append("; ").append(nok.getI());
                }
            }
        }

        return new Pair<>(noks, txtRes + ".");
    }

    public List<TableNumberFE> feGraph(int a, int m, int n) {

        List<TableNumberFE> tableNumberFES = new ArrayList<>();
        TableNumberFE tableNumberFE;

        String extra = "";

        int tempA = a;
        int tempM = m;
        int p = 1;
        int tmpP = 1;
        int r = 0;

        extra = "r = " + m + "%2 = " + m % 2 + ";\n";

        while (tempM != 0) {
            r = tempM % 2;
            if (r == 1) {
                p = (tmpP * tempA) % n;
                tmpP = p;
            }

            tableNumberFE = new TableNumberFE(tempA, tempM, n, p, r, extra);
            tableNumberFES.add(tableNumberFE);
            extra = extraData.extraFE(tempA, tempM, n, p);
            tempA = (tempA * tempA) % n;
            tempM = tempM / 2;

        }

        tableNumberFE = new TableNumberFE();
        tableNumberFES.add(tableNumberFE);
        return tableNumberFES;
    }

}
