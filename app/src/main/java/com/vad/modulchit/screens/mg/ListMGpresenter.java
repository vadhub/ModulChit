package com.vad.modulchit.screens.mg;

import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
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

    public void loadListMG(int m){

        Disposable disposable = Observable.just(algebraMod)
                .subscribeOn(Schedulers.computation())
                .map(o -> o.nokGraph(m))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listMGView::showData);
        compositeDisposable.add(disposable);
    }

    public void showResult(String modStr){

        if(!modStr.equals("")){
            int m = -1;

            try{
                m = Integer.parseInt(modStr);
            }catch (NumberFormatException e){
                listMGView.showError(R.string.warning_out_bounds);
            }
            listMGView.showTitle();
            if(m!=0){
                loadListMG(m);
            }else{
                listMGView.showError(R.string.warning_zero);
            }
        }else {
            listMGView.showError(R.string.warning_enter_text);
        }
    }

    public void disposableDispose() {
        compositeDisposable.dispose();
    }
}
