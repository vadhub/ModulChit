package com.vad.modulchit.screens.rsa.decrypt;


import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;
import com.vad.modulchit.models.RSAmod;
import com.vad.modulchit.pojos.TableNumberGCDe;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DecryptPresenter {

    private final DecryptView view;
    private final AlgebraMod algebraMod;
    private final RSAmod rsaMod;
    private final CompositeDisposable compositeDisposable;

    public DecryptPresenter(DecryptView view) {
        algebraMod = new AlgebraMod();
        rsaMod = new RSAmod(algebraMod);
        compositeDisposable = new CompositeDisposable();
        this.view = view;
    }

    public void decrypt(String dStr, String nStr, int eller, int exponent, List<Integer> alphabetCodes, String enterCodeDecrypt, int p, int q){
        int dInt = -1;
        int nInt = -1;

        if(!dStr.equals("")&&!nStr.equals("")){

            try{
                dInt = Integer.parseInt(dStr);
                nInt = Integer.parseInt(nStr);
            }catch (NumberFormatException e){
                view.showError(R.string.warning_out_bounds);
            }
            int finalDInt = dInt;
            int finalNInt = nInt;

            view.showTitle();
            Disposable disposable = Observable.just("")
                    .subscribeOn(Schedulers.computation())
                    .map(o -> calculateExtraData(eller, exponent, alphabetCodes, finalNInt, finalDInt, p, q, enterCodeDecrypt))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(view::showCalculatingExtra)
                    .subscribeOn(Schedulers.computation())
                    .map(list -> rsaMod.decryptingFE(finalDInt, finalNInt, enterCodeDecrypt).first)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(view::showCalculating)
                    .subscribeOn(Schedulers.computation())
                    .map(list -> algebraMod.gcdGraph(eller, exponent))
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(view::showCalculatingExtraWithList);

            compositeDisposable.add(disposable);
        }else{
            view.showError(R.string.warning_enter_text);
        }
    }

    private String calculateExtraData(int eller, int exponent, List<Integer> alphabetCodes, int n, int d, int p, int q, String enterCodeDecrypt){

        StringBuilder builder = new StringBuilder();
        builder.append("result: ").append(rsaMod.decrypting(alphabetCodes, d, n, enterCodeDecrypt).toUpperCase()).append("\n").append("\n");
        builder.append("e: ").append(exponent).append(";\n");
        builder.append("n = ").append(p).append("*").append(q).append(" = ").append(n).append(";\n");
        builder.append("Euler = (").append(p).append("-1").append(")*(").append(q).append("-1").append(") = ")
                .append(eller).append(";\n");

        List<TableNumberGCDe> list = algebraMod.gcdGraph(eller, exponent);
        int dView = list.get(list.size()-1).getY2();

        if (dView < 0) {
            builder.append("d = ").append(eller).append(" ").append(dView).append("=").append(d).append(";");
        }
        return builder.toString();
    }

    public void disposeDisposable() {
        compositeDisposable.dispose();
    }
 }
