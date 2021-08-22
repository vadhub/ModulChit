package com.vad.modulchit.screens.gcde;


import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListGCDEpresenter {

    private AlgebraMod algebraMod = new AlgebraMod();
    private ListGCDEView listGCDEView;

    public ListGCDEpresenter(ListGCDEView listGCDEView) {
        this.listGCDEView = listGCDEView;
    }

    public void loadListGCDE(int a, int b){
        List<TableNumberGCDe> tempTableNumberGCDes = algebraMod.gcdGraph(a, b);
        listGCDEView.showData(tempTableNumberGCDes);
    }

    public void showResult(String aStr, String bStr){


        Observable
                .just(aStr, bStr)
                .flatMap((Function<String, ObservableSource<?>>) s -> {
                    if(s.length()!=0){
                        listGCDEView.showError(R.string.warning_enter_text);
                        return null;
                    }
                    return Observable.just(aStr, bStr);
                }
                ).filter(Objects::nonNull)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    int a = -1;
                    int b = -1;

                    try{
                        a = Integer.parseInt(aStr);
                        b = Integer.parseInt(bStr);
                    }catch (NumberFormatException e){
                        listGCDEView.showError(R.string.warning_out_bounds);
                    }

                    listGCDEView.showTitle();

                    if(a!=0&&b!=0){
                        loadListGCDE(a, b);
                    }else{
                        listGCDEView.showError(R.string.warning_zero);
                    }
                });
    }
}
