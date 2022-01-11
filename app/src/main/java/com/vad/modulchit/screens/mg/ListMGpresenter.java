package com.vad.modulchit.screens.mg;

import com.vad.modulchit.R;
import com.vad.modulchit.utils.AlgebraMod;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListMGpresenter {

    private ListMGView listMGView;
    private AlgebraMod algebraMod = new AlgebraMod();

    public ListMGpresenter(ListMGView listMGView) {
        this.listMGView = listMGView;
    }

    public void loadListMG(int m){

        Observable.just(algebraMod)
                .subscribeOn(Schedulers.io())
                .map(o -> o.nokGraph(m))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(numberNOKS -> listMGView.showData(numberNOKS));
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
}
