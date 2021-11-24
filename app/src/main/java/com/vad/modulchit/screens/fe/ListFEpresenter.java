package com.vad.modulchit.screens.fe;

import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFEpresenter {

    private AlgebraMod algebraMod = new AlgebraMod();
    private ListFEView listFEView;

    public ListFEpresenter(ListFEView listFEView) {
        this.listFEView = listFEView;
    }

    public void loadListFE(int a, int m, int n){

        Observable.just("")
                .subscribeOn(Schedulers.io())
                .map(o -> algebraMod.feGraph(a, m, n))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tableNumberFES -> listFEView.showData(tableNumberFES));
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

}
