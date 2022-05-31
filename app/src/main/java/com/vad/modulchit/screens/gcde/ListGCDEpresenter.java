package com.vad.modulchit.screens.gcde;


import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListGCDEpresenter {

    private final AlgebraMod algebraMod;
    private final ListGCDEView listGCDEView;
    private final CompositeDisposable compositeDisposable;

    public ListGCDEpresenter(ListGCDEView listGCDEView) {
        this.listGCDEView = listGCDEView;
        algebraMod = new AlgebraMod();
        compositeDisposable = new CompositeDisposable();
    }

    public void loadListGCDE(int a, int b){

        Disposable disposable = Observable.just(algebraMod)
                .subscribeOn(Schedulers.io())
                .map(o -> o.gcdGraph(a, b))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listGCDEView::showData);

        compositeDisposable.add(disposable);
    }

    public void showResult(String aStr, String bStr){
        if (aStr.trim().length()!=0&&bStr.trim().length()!=0){
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
        }else {
            listGCDEView.showError(R.string.warning_enter_text);
        }

    }

    public void disposableDispose() {
        compositeDisposable.dispose();
    }
}
