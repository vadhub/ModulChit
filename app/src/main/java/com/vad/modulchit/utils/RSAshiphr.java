package com.vad.modulchit.utils;

import java.util.ArrayList;
import java.util.List;

public class RSAshiphr {

    public List<Character> getAlphabyte(){
        List<Character> alphabyte = new ArrayList<>();
        for(char i = 'a'; i<='z'; i++){
            alphabyte.add(i);
        }
        alphabyte.add('_');

        return alphabyte;
    }

    public List<Integer> getNumberShiphr(int startNumber,int modify){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabyte().size(); i++){
            numbers.add(i+modify+startNumber);
        }
        System.out.println(getAlphabyte().size());

        return numbers;
    }

    public List<Integer> getNumberShiphr(int startNumber){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabyte().size(); i++){
            numbers.add(i+startNumber);
        }
        System.out.println(getAlphabyte().size());

        return numbers;
    }

    public List<Integer> getNumberShiphr(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabyte().size(); i++){
            numbers.add((int) (Math.random() * 100));
        }
        System.out.println(getAlphabyte().size());
        return numbers;
    }

}
