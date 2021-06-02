package com.vad.modulchit.utils;

import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.pojos.TableNumberNOK;

import java.util.ArrayList;
import java.util.List;

public class AlgebraMod {

    public static int gcd(int a, int b) {
        while (b !=0) {
            int tmp = a%b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static int nok(int a, int b){
        return (a*b)/gcd(a,b);
    }

    public static int mod(int a, int b){
        return a%b;
    }

    public List<TableNumberGCDe> gcdGraph(int a, int b){
        List<TableNumberGCDe> tempTableNumberGCDes = new ArrayList<>();
        TableNumberGCDe tableNumberGCDe;

        int aTemp = 0;
        int bTemp = 0;
        int tmpQ = 0;
        int tmpR = 0;

        int x1= 1;
        int x2= 0;

        int y1 = 0;
        int y2 = 1;

        if(a<b){
            aTemp = b;
            bTemp = a;
            tmpQ = 0;
            tmpR = aTemp;
            x1 = 0;
            x2 = 1;
            y1=1;
            y2=0;
            tableNumberGCDe = new TableNumberGCDe(a, b, tmpQ, tmpR, x1, x2, y1, y2);
            tempTableNumberGCDes.add(tableNumberGCDe);
        }else{
            aTemp=a;
            bTemp=b;
        }
        while (true){

            tmpR = aTemp%bTemp;
            tmpQ = aTemp/bTemp;

            int tmpX1 = x1;
            int tmpX2 = x2;

            tableNumberGCDe = new TableNumberGCDe(aTemp, bTemp, tmpQ, tmpR, x1, x2, y1, y2);
            tempTableNumberGCDes.add(tableNumberGCDe);

            aTemp = tableNumberGCDe.getB();
            bTemp = tableNumberGCDe.getR();

            x1 = tableNumberGCDe.getY1();
            x2 = tableNumberGCDe.getY2();

            y1 = tmpX1 - tmpQ*y1;
            y2 = tmpX2 - tmpQ*y2;

            if(bTemp==0){
                break;
            }

        }

        return tempTableNumberGCDes;
    }

    public List<TableNumberNOK> nokGraph(int mod){
        List<TableNumberNOK> tempTableNumberNOKs = new ArrayList<>();
        TableNumberNOK tableNumberNOK;
        int a = 0;
        int b = 0;
        int q = 0;
        int r = 0;

        for(int i = 2; i<=mod; i++){
            a = mod;
            b = i;
            q = a/b;
            r = a%b;
            tableNumberNOK = new TableNumberNOK(a, b, q, r, i);
            tempTableNumberNOKs.add(tableNumberNOK);
            while (true){
                if(r==0){
                    tableNumberNOK = new TableNumberNOK(gcd(mod, i), 0, 0, 0, i);
                    tempTableNumberNOKs.add(tableNumberNOK);
                    break;
                };
                a = tableNumberNOK.getBn();
                b = tableNumberNOK.getRn();

                q = a/b;
                r = a%b;

                tableNumberNOK = new TableNumberNOK(a, b, q, r, i);
                tempTableNumberNOKs.add(tableNumberNOK);
            }
        }

        return tempTableNumberNOKs;
    }

    public String toBinary(int a){
        int b;
        String temp = "";
        while(a !=0){
            b = a%2;
            temp = b + temp;
            a = a/2;
        }
        return temp;
    }

    public List<Integer> getClasterBinary(String binary){

    }
}
