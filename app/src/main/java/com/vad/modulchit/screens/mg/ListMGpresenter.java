package com.vad.modulchit.screens.mg;

import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberNOK;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class ListMGpresenter {

    private ListMGView listMGView;
    private AlgebraMod algebraMod = new AlgebraMod();

    public ListMGpresenter(ListMGView listMGView) {
        this.listMGView = listMGView;
    }

    public void loadListMG(int m){
        List<TableNumberNOK> numberNOKS = algebraMod.nokGraph(m);
        listMGView.showData(numberNOKS);
    }

    public void showResult(String modStr){

        Observable.just(modStr).filter(s -> modStr.length()==0).subscribe(s -> {listMGView.showError(R.string.warning_enter_text);}).dispose();

        Observable.just(modStr)
                .filter(s->s.length()!=0)
                .subscribe(s -> {
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
                });



    }
}
