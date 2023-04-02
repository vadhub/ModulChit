package com.vad.modulchit.screens.mg;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;

import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;
import com.vad.modulchit.models.pojos.TableNumberNOK;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListMGpresenter {

    private final ListMGView listMGView;
    private final AlgebraMod algebraMod;
    private final CompositeDisposable compositeDisposable;

    public ListMGpresenter(ListMGView listMGView) {
        this.listMGView = listMGView;
        algebraMod = new AlgebraMod();
        compositeDisposable = new CompositeDisposable();
    }

    public void loadListMG(int m) {

        Disposable disposable = Observable
                .fromCallable(() -> {
                    Log.d("##li", Thread.currentThread().getName());
                    return algebraMod.getResult(algebraMod.nokGraph(m));
                }).map(pair -> {
                    List<TableNumberNOK> tableNumberNOKS;
                    if (pair.first.size() > 100) {
                        tableNumberNOKS = new ArrayList(pair.first.subList(0, 100));
                        return new Pair<>(tableNumberNOKS, pair.second);
                    }
                    return pair;
                })
                .subscribeOn(Schedulers.single())
                .doOnError(error -> System.out.println(error.getMessage() + " -----------------------"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> {
                    Log.d("##li", Thread.currentThread().getName());
                    listMGView.showData(pair.first, pair.second);
                });
        compositeDisposable.add(disposable);
    }


    public void showResult(String modStr) {

        if (!modStr.equals("")) {
            int m = -1;

            try {
                m = Integer.parseInt(modStr);
            } catch (NumberFormatException e) {
                listMGView.showError(R.string.warning_out_bounds);
            }
            listMGView.showTitle();
            if (m != 0) {
                loadListMG(m);
            } else {
                listMGView.showError(R.string.warning_zero);
            }
        } else {
            listMGView.showError(R.string.warning_enter_text);
        }
    }

    public void disposableDispose() {
        compositeDisposable.dispose();
    }
}
