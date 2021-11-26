package com.vad.modulchit.screens.rsa.crypt;


import com.vad.modulchit.R;
import com.vad.modulchit.utils.RSAmod;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CryptPresenter {

    private CryptView view;
    private List<Integer> numbersCodesForCrypt = new ArrayList<>();
    private RSAmod rsaMod = new RSAmod();

    public CryptPresenter(CryptView view) {
        this.view = view;
    }

    private void encrypt(List<Integer> alphaviteCodes, String textToEncrypt){

        char[] strCrypt = textToEncrypt.toLowerCase().toCharArray();

        Observable.fromArray(strCrypt)
                .subscribeOn(Schedulers.io())
                .flatMap(arr -> {
                    for (char s: arr) {
                        return Observable.just(s);
                    }
                    return null;
                }).filter(Objects::nonNull)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    System.out.println(s);
                });

//        for (int j = 0; j < rsAshiphr.getAlphabyteEN().size(); j++) {
//            if (rsAshiphr.getAlphabyteEN().get(j).equals(chars)) {
//                numbersCodesForCrypt.add(alphaviteCodes.get(j));
//                break;
//            }
//        }
    }

    public void result(List<Integer> alphaviteCodes, String textToEncrypt, String eStr, String nStr){
        //is letters?
        if(textToEncrypt.matches("[a-zA-Z\\s]+")){

            encrypt(alphaviteCodes, textToEncrypt);
            //output calculated result
            if(!eStr.equals("")&&!nStr.equals("")){
                int e = -1;
                int n = -1;

                try {
                    e = Integer.parseInt(eStr);
                    n = Integer.parseInt(nStr);
                }catch (NumberFormatException ex){
                    view.showError(R.string.warning_out_bounds);
                }

                view.showTitle();

                int finalE = e;
                int finalN = n;

                view.showCalculating(rsaMod.encryptingFE(finalE, finalN, numbersCodesForCrypt));
                view.showCalculatingExtra(rsaMod.encrypting(finalE, finalN, numbersCodesForCrypt)+"\n");
                numbersCodesForCrypt=null;

            }else{
                view.showError(R.string.warning_enter_text);
            }

        }else {
            view.showError(R.string.warning_enter_letter);
        }

    }
}
