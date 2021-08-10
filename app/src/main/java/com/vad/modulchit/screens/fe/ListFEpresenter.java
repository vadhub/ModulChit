package com.vad.modulchit.screens.fe;

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
}
