package com.vad.modulchit.screens.gcde;

import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;

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
}
