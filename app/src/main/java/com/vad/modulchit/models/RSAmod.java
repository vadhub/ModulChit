package com.vad.modulchit.models;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.pojos.TableNumberGCDe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

    public int getDPrivate(int eller, int exponent){
        int d = algebraMod.gcdGraph(eller, exponent).get(algebraMod.gcdGraph(eller,exponent).size()-1).getY2();

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

    //open exponenta for public key
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

    //generate private key on visaul gcde
    public List<TableNumberGCDe> getDGraph(int exp, int eller){
        List<TableNumberGCDe> gcdeList = algebraMod.gcdGraph(eller,exp);
        return gcdeList;
    }

    private List<Integer> getClastersFromString(String strCrypt, int n){
        List<Integer> clasters = new ArrayList<>();
        char[] charsCrypt = strCrypt.toCharArray();
        String claster = "";
        int clast = 0;
        int temp = 0;

        for(int i = 0; i<charsCrypt.length; i++){

            if(!claster.equals("")){
                temp = Integer.parseInt(claster);
            }

            claster+=charsCrypt[i];
            clast = Integer.parseInt(claster);

            if(clast>n){
                clasters.add(temp);
                claster=String.valueOf(charsCrypt[i]);
                clast = Integer.parseInt(claster);
            }
        }
        clasters.add(clast);
        return clasters;
    }

    //experimental
//    public void minimumSets(String strCrypt, int n){
//        int count = 0;
//        int num = 0;
//        String strNumber = "";
//
//        int length = strCrypt.length();
//        boolean isMore = false;
//
//        for(int i = 0; i<length;i++){
//            num = num * 10 + (strCrypt.charAt(i) - '0');
//
//            if(num <=n){
//                isMore = true;
//            }else {
//                if(isMore){
//                    count+=1;
//                    strNumber+=num+",";
//                }
//
//                num = strCrypt.charAt(i) - '0';
//                isMore = false;
//
//                if(num<=n){
//                    isMore = true;
//                }else{
//                    count+=1;
//                    strNumber+=num+",";
//                }
//            }
//        }
//
//        if(isMore){
//            count+=1;
//            strNumber+=num+",";
//        }
//
//        System.out.println("blocks: "+strNumber+"; sets"+count);
//    }

    private List<Integer> getClasters(List<Integer> numberCodes, int n){
        StringBuilder strCrypt = new StringBuilder();

        if(numberCodes!=null){
            for(int a: numberCodes){
                strCrypt.append(a);
            }
        }

        return getClastersFromString(strCrypt.toString(), n);
    }

    public String encrypting(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        List<String> result = new ArrayList<>();

        for (int num: clasters) {
            algebraMod.feGraph(num, e, n)
                    .subscribeOn(Schedulers.computation())
                    .doOnNext(p -> result.add(p.get(p.size()-2).getP()+""))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }

        return result.toString();
    }

    public List<TableNumberFE> encryptingFE(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        return getFE(e, n, clasters);
    }

    public List<TableNumberFE> getFE(int m, int n, List<Integer> numbers) {
        List<List<TableNumberFE>> temp = new ArrayList<>();
        List<TableNumberFE> result = new ArrayList<>();

        for(int num: numbers){
            algebraMod.feGraph(num, m, n)
                    .subscribeOn(Schedulers.computation())
                    .doOnNext(temp::add)
                    .subscribe();
        }

        temp.forEach(result::addAll);
        return result;
    }

    public List<TableNumberFE> decryptingFE(int d, int n, String strEncrypt){
        List<Integer> numberForDecrypt = getNumberCodes(strEncrypt);
        return getFE(d, n, numberForDecrypt);
    }

    private List<Integer> getNumberCodes(String strCode){
        List<Integer> numberCodes = new ArrayList<>();
        strCode = strCode.replaceAll("\\[", "");
        strCode = strCode.replaceAll("]", "");
        strCode = strCode.replaceAll("\\s", "");

        String[] numbers = strCode.split(",");

        for (String number : numbers) {
            numberCodes.add(Integer.parseInt(number));
        }

        return numberCodes;
    }

    public String decrypting(List<Integer> alphaviteCodes, int d, int n, String strCode){
        List<Integer> numberCodes = getNumberCodes(strCode);
        List<Integer> tempNumbersCode = new ArrayList<>();
        RSAshiphr rsAshiphr = new RSAshiphr();

        StringBuilder strCodes = new StringBuilder();
        for (Integer i: numberCodes) {
            algebraMod.feGraph(i, d, n)
                    .subscribeOn(Schedulers.computation())
                    .doOnNext(p -> {
                        int t = p.get(p.size() - 2).getP();
                        strCodes.append(t);
                        tempNumbersCode.add(t);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }

        return tempNumbersCode+"\n"+getNumberAlphavite(alphaviteCodes, strCodes.toString(), rsAshiphr);
    }

    private String getNumberAlphavite(List<Integer> alphaviteCodes, String strCode, RSAshiphr rsAshiphr){
        String temp = "";
        StringBuilder str = new StringBuilder();
        for(int i = 0; i<strCode.length();i++){
            temp += strCode.charAt(i);
            try {
                if(alphaviteCodes.contains(Integer.parseInt(temp))){
                    str.append(rsAshiphr.getAlphabyteEN().get(alphaviteCodes.indexOf(Integer.parseInt(temp))));
                    temp="";
                }
            }catch (NumberFormatException e){
                temp = "-1";
            }
        }
        return str.toString();
    }
}
