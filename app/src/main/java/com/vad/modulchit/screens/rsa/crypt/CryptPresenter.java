package com.vad.modulchit.screens.rsa.crypt;

import com.vad.modulchit.R;
import com.vad.modulchit.models.RSAmod;
import com.vad.modulchit.models.RSAshiphr;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CryptPresenter {

    private CryptView view;
    private volatile List<Integer> numbersCodesForCrypt = new ArrayList<>();
    private RSAshiphr cypher = new RSAshiphr();
    private RSAmod rsaMod = new RSAmod();
    private CompositeDisposable compositeDisposable;
    private Disposable disposable;

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

                numbersCodesForCrypt = encrypt(alphaviteCodes, textToEncrypt);
                disposable = Observable.just(rsaMod)
                        .subscribeOn(Schedulers.computation())
                        .map(rsa -> rsa.encrypting(finalE, finalN, numbersCodesForCrypt))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(rsa -> {
                            view.showCalculating(rsa);
                            view.showCalculatingExtra(rsa.toString()+"\n");
                        });
                compositeDisposable.add(disposable);
                numbersCodesForCrypt=null;

            }else{
                view.showError(R.string.warning_enter_text);
            }

        }else {
            view.showError(R.string.warning_enter_letter);
        }

    }

    public void disposableDispose() {
        if (compositeDisposable!=null) {
            compositeDisposable.dispose();
        }
    }
}
