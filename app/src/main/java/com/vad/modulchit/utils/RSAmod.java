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

    public List<Integer> getClastersFromString(String strCrypt, int n){
        List<Integer> clasters = new ArrayList<>();
        char[] charNumb = strCrypt.toCharArray();
        String numberClaster = String.valueOf(charNumb[0]);
        int number;

        for(int i = 1; i<charNumb.length; i++){
            number = Character.getNumericValue(charNumb[i]);
            if(number<n){
                numberClaster+=charNumb[0];
            }else{
                clasters.add(Integer.parseInt(numberClaster));
                numberClaster="";
            }
        }

        return clasters;
    }

    public List<Integer> getClasters(List<Integer> numberCodes){

    }

    public String crypting(int e, int n, List<Integer> numberCodes){

    }
}
