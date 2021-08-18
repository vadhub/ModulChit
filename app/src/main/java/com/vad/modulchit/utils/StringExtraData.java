package com.vad.modulchit.utils;

public class StringExtraData {
    public String extraGCDE(int a, int b, int q, int r, int x1, int x2, int y1, int y2){
        String result =
                "r = "+a+" - "+b+"*"+q+" = "+ r+";\n" //r = 56 - 11*5 = 1;
                        +"x = ("+x1+","+x2+");\n" //x = (1,0);
                        +"y = ("+x1+","+x2+") - "+ q +"*("+y1+","+y2+");\n"; // y = (1,0) - 1*(0,1)

        return result;
    }

    public String extraFE(){
        String result = "";
        return result;
    }
}
