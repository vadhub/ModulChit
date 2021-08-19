package com.vad.modulchit.utils;

public class StringExtraData {
    public String extraGCDE(int a, int b, int q, int r, int x1, int x2, int y1, int y2){
        String result =
                "r = "+a+" - "+b+"*"+q+" = "+ r+";\n" //r = 56 - 11*5 = 1;
                        +"x = ("+x1+","+x2+");\n" //x = (1,0);
                        +"y = ("+x1+","+x2+") - "+ q +"*("+y1+","+y2+") = ("+(x1-(q*y1))+","+(x2-(q*y2))+");\n"; // y = (1,0) - 1*(0,1)

        return result;
    }

    public String extraFE(int a, int m, int n, int pe){
        int p = pe;
        int r = (m/2)%2;
        int aTmp;
        String pExtra = "p ="+p+"(pre);\n";

        if(r==1){
            aTmp = (a*a)%n;
            p = (p*aTmp)%n;
            pExtra = "p = ("+pe+"*"+aTmp+")%"+n+"="+p+";\n";
        }

        String result = "a = ("+a +"*"+a+")%"+n+"="+(a*a)%n+";\n"
                +"m = "+m+"/2 = "+m/2+";\n"
                +"r = "+(m/2)+"%2 = "+r+";\n"
                +pExtra;
        return result;
    }
}
