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

    public int getDPrivate(int exponent, int eller){
        return algebraMod.gcdGraph(exponent, eller).get(algebraMod.gcdGraph(exponent, eller).size()-1).getY2();
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

        for(int i = 0; i<charNumb.length; i++){

            if(Integer.parseInt(numberClaster)<n){
                numberClaster+=charNumb[i];
                System.out.println(numberClaster);
            }else{
                clasters.add(Integer.parseInt(numberClaster));
                numberClaster=String.valueOf(charNumb[i]);
            }
        }
        System.out.println(clasters);
        return clasters;
    }

    public List<Integer> getClasters(List<Integer> numberCodes, int n){
        String strCrypt = "";

        for(int a: numberCodes){
            strCrypt += a;
        }

        System.out.println(strCrypt);

        return getClastersFromString(strCrypt, n);
    }

    public String crypting(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        System.out.println("crypt");
        String strCrypt = "[";

        for (int num: clasters) {
            System.out.println(num);
            strCrypt +=algebraMod.feGraph(num, e, n).get(algebraMod.feGraph(num, e, n).size()-1).getP()+", ";
        }

        strCrypt+="]";

        return strCrypt;
    }
}
