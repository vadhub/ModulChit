package com.vad.modulchit.models;

import java.util.ArrayList;
import java.util.List;

public class RSAshiphr {

    public List<Character> getAlphabyteEN(){
        List<Character> alphabyte = new ArrayList<>();
        for(char i = 'a'; i<='z'; i++){
            alphabyte.add(i);
        }
        alphabyte.add(' ');

        return alphabyte;
    }

    public List<Integer> getNumberShiphr(int startNumber){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabyteEN().size(); i++){
            numbers.add(i+startNumber);
        }

        return numbers;
    }

    public List<Integer> getNumberShiphr(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabyteEN().size(); i++){
            numbers.add((int) (Math.random() * 100));
        }
        return numbers;
    }

}
