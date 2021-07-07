package com.vad.modulchit.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RSAshiphr {

    public List<Character> getAlphabyte(){
        List<Character> alphabyte = new ArrayList<>();
        for(char i = 'a'; i<'z'; i++){
            alphabyte.add(i);
        }

        return alphabyte;
    }

    public List<Integer> getNumberShiphr(int startNumber,int modify){
        List<Integer> numbers = new ArrayList<>();
        for(int i = startNumber; i < getAlphabyte().size(); i+=modify){
            numbers.add(i);
        }

        return numbers;
    }

    public List<Integer> getNumberShiphr(int startNumber){
        List<Integer> numbers = new ArrayList<>();
        for(int i = startNumber; i < getAlphabyte().size(); i++){
            numbers.add(i);
        }

        return numbers;
    }

}
