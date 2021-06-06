package com.vad.modulchit.utils;

import java.math.BigInteger;

public class RSAmod {

    //get n modulo for public key
    public int getN(int p, int q){
        return p*q;
    }

    //check is simple number
    public boolean isSimpleNumber(int number){
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime(number);
    }

    //function eller
    public int functionEller(int p, int q){
        return (p-1)*(q-1);
    }

    public int exponenta(int eller){
        return eller;
    }

}
