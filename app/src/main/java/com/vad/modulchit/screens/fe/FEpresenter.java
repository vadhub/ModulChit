package com.vad.modulchit.screens.fe;

import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FEpresenter {

    private AlgebraMod algebraMod;
    private ListFEView listFEView;
    private CompositeDisposable compositeDisposable;

    public FEpresenter(ListFEView listFEView) {
        this.listFEView = listFEView;
        algebraMod = new AlgebraMod();
        compositeDisposable = new CompositeDisposable();

    }

    public void loadListFE(int a, int m, int n){

        Disposable disposable = Observable.just(algebraMod)
                .subscribeOn(Schedulers.computation())
                .map(alm -> alm.feGraph(a, m, n))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listFEView::showData);

        compositeDisposable.add(disposable);

    }


    public void viewResult(String aStr, String mStr, String nStr){

        if(!aStr.equals("")&&!mStr.equals("")&&!nStr.equals("")){
            int a =-1;
            int m =-1;
            int n =-1;

            try {
                a = Integer.parseInt(aStr);
                m = Integer.parseInt(mStr);
                n = Integer.parseInt(nStr);
            }catch (NumberFormatException e){
                listFEView.showError(R.string.warning_out_bounds);
            }
            listFEView.showTitle();
            if(m!=0&&n!=0){
                loadListFE(a, m, n);
            }else{
                listFEView.showError(R.string.warning_zero);
            }
        }else {
            listFEView.showError(R.string.warning_enter_text);
        }

    }

    public void disposableDispose() {
        if (compositeDisposable!=null) {
            compositeDisposable.dispose();
        }
    }

}
