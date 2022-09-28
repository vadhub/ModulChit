package com.vad.modulchit.models;

import java.util.ArrayList;
import java.util.List;

public class RSAshiphr {

    public List<Character> getAlphabetEN(){
        List<Character> alphabet = new ArrayList<>();
        for(char i = 'a'; i<='z'; i++){
            alphabet.add(i);
        }
        alphabet.add(' ');

        return alphabet;
    }

    public List<Integer> getNumberShiphr(int startNumber){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabetEN().size(); i++){
            numbers.add(i+startNumber);
        }

        return numbers;
    }

    public List<Integer> getNumberShiphr(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < getAlphabetEN().size(); i++){
            numbers.add((int) (Math.random() * 100));
        }
        return numbers;
    }

}
