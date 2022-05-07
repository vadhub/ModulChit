package com.vad.modulchit.utils;

public class Parser {
    public static int[] parseComma(String str) {
        String[] spitString = str.split(", ");
        int[] tempArr = new int[spitString.length];

        for (int i = 0; i < spitString.length; i++) {
            tempArr[i] = Integer.parseInt(spitString[i]);
        }

        return tempArr;
    }
}
