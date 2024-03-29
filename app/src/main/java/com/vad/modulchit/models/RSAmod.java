package com.vad.modulchit.models;

import android.util.Pair;

import com.vad.modulchit.models.pojos.TableNumberFE;
import com.vad.modulchit.models.pojos.TableNumberGCDe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class RSAmod {

    private final AlgebraMod algebraMod;

    public RSAmod(AlgebraMod algebraMod) {
        this.algebraMod = algebraMod;
    }

    //get n modulo for public key
    public int getN(int p, int q){
        return p*q;
    }

    //function eller for public key
    public int functionEller(int p, int q){
        return (p-1)*(q-1);
    }

    public int getDPrivate(int eller, int exponent){
        List<TableNumberGCDe> listStep = algebraMod.gcdGraph(eller, exponent);
        int d = listStep.get(listStep.size()-1).getY2();

        if(d<0){
            d+=eller;
        }
        return d;
    }

    //check is simple number
    public boolean isSimpleNumber(int number){
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime(number);
    }

    //open exponent for public key
    public List<Integer> exponenta(int eller){
        List<Integer> simpleNumber = new ArrayList<>();

        for(int i = 2; i<eller; i++) {
            if(i==15){
                break;
            }
            if (isSimpleNumber(i)) {
                if (AlgebraMod.gcd(eller, i) == 1) {
                    simpleNumber.add(i);
                }
            }
        }
        return simpleNumber;
    }

    //generate private key on visual gcde
    public List<TableNumberGCDe> getDGraph(int exp, int eller){
        return algebraMod.gcdGraph(eller,exp);
    }

    private List<Integer> getClastersFromString(String strCrypt, int n){
        List<Integer> clasters = new ArrayList<>();
        char[] charsCrypt = strCrypt.toCharArray();
        String claster = "";
        int clast = 0;
        int temp = 0;

        for (char c : charsCrypt) {

            if (!claster.equals("")) {
                temp = Integer.parseInt(claster);
            }

            claster += c;
            clast = Integer.parseInt(claster);

            if (clast > n) {
                clasters.add(temp);
                claster = String.valueOf(c);
                clast = Integer.parseInt(claster);
            }
        }
        clasters.add(clast);
        return clasters;
    }

    private List<Integer> getClasters(List<Integer> numberCodes, int n){
        StringBuilder strCrypt = new StringBuilder();

        if(numberCodes!=null){
            for(int a: numberCodes){
                strCrypt.append(a);
            }
        }

        return getClastersFromString(strCrypt.toString(), n);
    }

    public Pair<List<TableNumberFE>, List<Integer>> encrypting(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        return getFE(e, n, clasters);
    }

    public Pair<List<TableNumberFE>, List<Integer>> decryptingFE(int d, int n, String strEncrypt){
        List<Integer> numberForDecrypt = Parser.parseToIntList(strEncrypt);
        return getFE(d, n, numberForDecrypt);
    }

    public Pair<List<TableNumberFE>, List<Integer>> getFE(int m, int n, List<Integer> numbers) {
        List<List<TableNumberFE>> temp = new ArrayList<>();
        List<TableNumberFE> listOfStep = new ArrayList<>();
        List<Integer> listOfP = new ArrayList<>();

        for(int num: numbers){
            List<TableNumberFE> tableNumberFE = algebraMod.feGraph(num, m, n);
            temp.add(tableNumberFE);
            listOfP.add(tableNumberFE.get(tableNumberFE.size()-2).getP());
        }

        temp.forEach(listOfStep::addAll);
        return new Pair<>(listOfStep, listOfP);
    }

    public String decrypting(List<Integer> alphabetCodes, int d, int n, String strCode){
        List<Integer> numberCodes = Parser.parseToIntList(strCode);
        List<Integer> tempNumbersCode = new ArrayList<>();
        List<TableNumberFE> tableNumberFEs = new ArrayList<>();
        RSAshiphr rsAshiphr = new RSAshiphr();

        StringBuilder strCodes = new StringBuilder();
        for (Integer i: numberCodes) {
            tableNumberFEs.addAll(algebraMod.feGraph(i, d, n));
            int t = tableNumberFEs.get(tableNumberFEs.size() - 2).getP();
            strCodes.append(t);
            tempNumbersCode.add(t);
        }

        return tempNumbersCode+"\n"+getNumberAlphabet(alphabetCodes, strCodes.toString(), rsAshiphr);
    }

    private String getNumberAlphabet(List<Integer> alphabetCodes, String strCode, RSAshiphr rsAshiphr){
        String temp = "";
        StringBuilder str = new StringBuilder();
        for(int i = 0; i<strCode.length();i++){
            temp += strCode.charAt(i);
            try {
                if(alphabetCodes.contains(Integer.parseInt(temp))){
                    str.append(rsAshiphr.getAlphabetEN().get(alphabetCodes.indexOf(Integer.parseInt(temp))));
                    temp="";
                }
            }catch (NumberFormatException e){
                temp = "-1";
            }
        }
        return str.toString();
    }
}
