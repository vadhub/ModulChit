package com.vad.modulchit.screens.rsa.crypt;


import android.os.Handler;
import android.os.Looper;

import com.vad.modulchit.R;
import com.vad.modulchit.models.RSAmod;
import com.vad.modulchit.models.RSAshiphr;

import java.util.ArrayList;
import java.util.List;

public class CryptPresenter {

    private CryptView view;
    private volatile List<Integer> numbersCodesForCrypt = new ArrayList<>();
    private RSAshiphr cypher = new RSAshiphr();
    private RSAmod rsaMod = new RSAmod();

    public CryptPresenter(CryptView view) {
        this.view = view;
    }

    private List<Integer> encrypt(List<Integer> alphaviteCodes, String textToEncrypt){
        List<Integer> numbersCodesForCrypt = new ArrayList<>();
            for (char c : textToEncrypt.toLowerCase().toCharArray()) {
                for (int j = 0; j < cypher.getAlphabyteEN().size(); j++) {
                    if (cypher.getAlphabyteEN().get(j).equals(c)) {
                        numbersCodesForCrypt.add(alphaviteCodes.get(j));
                        break;
                    }
                }
            }
            return numbersCodesForCrypt;
    }

    public void result(List<Integer> alphaviteCodes, String textToEncrypt, String eStr, String nStr){
        //is letters?
        if(textToEncrypt.matches("[a-zA-Z\\s]+")){
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

                new Thread(() -> {
                    numbersCodesForCrypt = encrypt(alphaviteCodes, textToEncrypt);

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {
                        view.showCalculating(rsaMod.encryptingFE(finalE, finalN, numbersCodesForCrypt));
                        view.showCalculatingExtra(rsaMod.encrypting(finalE, finalN, numbersCodesForCrypt)+"\n");
                    });

                }).start();
                numbersCodesForCrypt=null;

            }else{
                view.showError(R.string.warning_enter_text);
            }

        }else {
            view.showError(R.string.warning_enter_letter);
        }

    }
}
