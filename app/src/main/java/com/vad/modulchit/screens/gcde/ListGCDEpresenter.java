package com.vad.modulchit.screens.gcde;

import com.vad.modulchit.R;
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

    public void showResult(String aStr, String bStr){
        if((!aStr.equals("")&&!bStr.equals(""))){
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
        }else{
            listGCDEView.showError(R.string.warning_enter_text);
        }
    }
}
