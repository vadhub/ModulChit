package com.vad.modulchit.utils;

import com.vad.modulchit.pojos.TableNumberGCDe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RSAmod {

    private AlgebraMod algebraMod = new AlgebraMod();

    //get n modulo for public key
    public int getN(int p, int q){
        return p*q;
    }

    //check is simple number
    public boolean isSimpleNumber(int number){
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime(number);
    }

    //function eller for public key
    public int functionEller(int p, int q){
        return (p-1)*(q-1);
    }

    //open exponenta for public key
    public int exponenta(int eller){
        List<Integer> simpleNumber = new ArrayList<>();
        int simple = -1;

        for(int i = 2; i<eller; i++) {
            if (isSimpleNumber(i)) {
                if (AlgebraMod.gcd(eller, i) == 1) {
                    simple = i;
                    break;
                }
            }
        }
        return simple;
    }

    //generate private key on visaul gcde
    public List<TableNumberGCDe> getDGraph(int exp, int eller){
        List<TableNumberGCDe> gcdeList = algebraMod.gcdGraph(eller,exp);
        return gcdeList;
    }

    public String crypting(int e, int n, LinkedHashMap<Character, Integer> keys){

    }
}
