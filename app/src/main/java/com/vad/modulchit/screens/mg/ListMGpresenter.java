package com.vad.modulchit.screens.mg;

import com.vad.modulchit.pojos.TableNumberNOK;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;

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
}
