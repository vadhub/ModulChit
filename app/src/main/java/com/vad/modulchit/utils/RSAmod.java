package com.vad.modulchit.utils;

import com.vad.modulchit.pojos.TableNumberGCDe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class RSAmod {

    private AlgebraMod algebraMod = new AlgebraMod();

    //get n modulo for public key
    public int getN(int p, int q){
        return p*q;
    }

    //function eller for public key
    public int functionEller(int p, int q){
        return (p-1)*(q-1);
    }

    public int getDPrivate(int exponent, int eller){
        int d = algebraMod.gcdGraph(exponent, eller).get(algebraMod.gcdGraph(exponent, eller).size()-1).getY2();
        if(d<0){
            d=+exponent;
        }
        return d;
    }

    //check is simple number
    public boolean isSimpleNumber(int number){
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime(number);
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
        char[] charsCrypt = strCrypt.toCharArray();
        String claster = String.valueOf(charsCrypt[0]);
        int clast = 0;
        int temp = 0;

        for(int i = 1; i<charsCrypt.length; i++){

            if(clast>n){
                System.out.println(clast+" "+temp);
                clasters.add(temp);
                claster=String.valueOf(charsCrypt[i-1]);
            }
            temp = Integer.parseInt(claster);

            claster+=charsCrypt[i];
            clast = Integer.parseInt(claster);

            if(i == charsCrypt.length-1){
                if(clast>n){
                    clasters.add(Integer.parseInt(charsCrypt[i-1]+""));
                    claster=String.valueOf(charsCrypt[i]);
                    clast = Integer.parseInt(claster);
                }
            }
        }

        clasters.add(clast);
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

    public String encrypting(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        List<String> result = new ArrayList<>();

        for (int num: clasters) {
            result.add(algebraMod.feGraph(num, e, n).get(algebraMod.feGraph(num, e, n).size()).getP()+"");
        }

        return result.toString();
    }

    private List<Integer> getNumberCodes(String strCode){
        List<Integer> numberCodes = new ArrayList<>();
        String[] numbers = strCode.split(",");

        for (String number : numbers) {
            numberCodes.add(Integer.parseInt(number));
        }

        return numberCodes;
    }

    public String decrypting(int d, int n, String strCode){
        List<Integer> numberCodes = getNumberCodes(strCode);

        for (Integer i: numberCodes){
            String str = String.valueOf(algebraMod.feGraph(i, d, n).get(algebraMod.feGraph(i, d, n).size()-1).getP());
        }
        return "";
    }
}
