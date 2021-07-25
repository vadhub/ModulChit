package com.vad.modulchit.utils;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.pojos.TableNumberGCDe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
            if (isSimpleNumber(i)) {
                if (AlgebraMod.gcd(eller, i) == 1) {
                    simpleNumber.add(i);
                    break;
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

    private List<Integer> getClastersFromString(String strCrypt){
        List<Integer> clasters = new ArrayList<>();
        int lengthSymbol = 2;

        for(int i = 0; i<strCrypt.length(); i+=lengthSymbol){
            if(i+lengthSymbol<=strCrypt.length()){
                clasters.add(Integer.parseInt(strCrypt.substring(i, i+lengthSymbol)));
            }else{
                clasters.add(Integer.parseInt(strCrypt.substring(i)));
            }
        }
        System.out.println(clasters);
        return clasters;
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
        String strCrypt = "";

        for(int a: numberCodes){
            strCrypt += a;
        }

        return getClastersFromString(strCrypt, n);
    }

    public String encrypting(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        List<String> result = new ArrayList<>();
        
        for (int num: clasters) {
            result.add(algebraMod.feGraph(num, e, n).get(algebraMod.feGraph(num, e, n).size()-2).getP()+"");
        }

        return result.toString();
    }

    public List<TableNumberFE> encryptingFE(int e, int n, List<Integer> numberCodes){
        List<Integer> clasters = getClasters(numberCodes, n);
        List<List<TableNumberFE>> temp = new ArrayList<>();
        List<TableNumberFE> result = new ArrayList<>();

        for(int num: clasters){
            temp.add(algebraMod.feGraph(num, e, n));
        }
        temp.forEach(result::addAll);

        return result;
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

    public String decrypting(List<Integer> alphaviteCodes,int d, int n, String strCode){
        List<Integer> numberCodes = getNumberCodes(strCode);
        RSAshiphr rsAshiphr = new RSAshiphr();

        System.out.println(numberCodes);
        int temp = 0;
        String str = "";
        for (Integer i: numberCodes) {
            temp = algebraMod.feGraph(i, d, n).get(algebraMod.feGraph(i, d, n).size() - 2).getP();
            str += temp;
        }

        List<Integer> clasters = getClastersFromString(str);
        str="";
        str+=clasters+"\n";
        for(Integer i: clasters){
            if(alphaviteCodes.contains(i)){
                str+=rsAshiphr.getAlphabyteEN().get(alphaviteCodes.indexOf(i));
            }else{
                str+=" ("+i+") ";
            }
        }

        return str;
    }
}
