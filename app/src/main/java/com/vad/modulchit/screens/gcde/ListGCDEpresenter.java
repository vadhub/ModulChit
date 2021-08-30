package com.vad.modulchit.screens.gcde;


import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListGCDEpresenter {

    private AlgebraMod algebraMod = new AlgebraMod();
    private ListGCDEView listGCDEView;

    public ListGCDEpresenter(ListGCDEView listGCDEView) {
        this.listGCDEView = listGCDEView;
    }

    public void loadListGCDE(int a, int b){

        Observable.just("")
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(objects -> algebraMod.gcdGraph(a, b))
                .subscribe(
                        tableNumberGCDeList -> listGCDEView.showData(tableNumberGCDeList)
                );
    }

    public void showResult(String aStr, String bStr){
        if (aStr.equals("")&&bStr.equals("")){
            listGCDEView.showError(R.string.warning_enter_text);
        }else {
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
        }

    }
}
