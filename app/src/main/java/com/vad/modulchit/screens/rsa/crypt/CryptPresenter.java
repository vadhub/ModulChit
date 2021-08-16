package com.vad.modulchit.screens.rsa.crypt;


import com.vad.modulchit.R;
import com.vad.modulchit.utils.RSAmod;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.List;

public class CryptPresenter {

    private CryptView view;
    private List<Integer> numbersCodesForCrypt = new ArrayList<>();
    private RSAshiphr rsAshiphr = new RSAshiphr();
    private RSAmod rsaMod = new RSAmod();

    public CryptPresenter(CryptView view) {
        this.view = view;
    }

    private void encrypt(List<Integer> alphaviteCodes, String textToEncrypt){
        char[] strCrypt = textToEncrypt.toLowerCase().toCharArray();
        for (char c : strCrypt) {
            for (int j = 0; j < rsAshiphr.getAlphabyteEN().size(); j++) {
                if (rsAshiphr.getAlphabyteEN().get(j).equals(c)) {
                    numbersCodesForCrypt.add(alphaviteCodes.get(j));
                    break;
                }
            }
        }
    }

    public void result(List<Integer> alphaviteCodes, String textToEncrypt, String eStr, String nStr){
        //encrypt text
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
