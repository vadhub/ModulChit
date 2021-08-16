package com.vad.modulchit.screens.fe;

import com.vad.modulchit.R;
import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;

public class ListFEpresenter {

    private AlgebraMod algebraMod = new AlgebraMod();
    private ListFEView listFEView;

    public ListFEpresenter(ListFEView listFEView) {
        this.listFEView = listFEView;
    }

    public void loadListFE(int a, int m, int n){
        List<TableNumberFE> tableNumberFES = algebraMod.feGraph(a, m, n);
        listFEView.showData(tableNumberFES);
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
