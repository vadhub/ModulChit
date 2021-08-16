package com.vad.modulchit.screens.rsa.decrypt;

import android.view.View;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.utils.AlgebraMod;
import com.vad.modulchit.utils.RSAmod;

import java.util.List;

public class DecryptPresenter {

    private DecryptView view;
    private AlgebraMod algebraMod = new AlgebraMod();
    private RSAmod rsaMod = new RSAmod();

    public DecryptPresenter(DecryptView view) {
        this.view = view;
    }


    public void decrypt(String dStr, String nStr, int eller, int exponent, List<Integer> alphaviteCodes, String enterCodeDecrypt, int p, int q){
        int dInt = -1;
        int nInt = -1;

        if(!dStr.equals("")&&!nStr.equals("")){

            try{
                dInt = Integer.parseInt(dStr);
                nInt = Integer.parseInt(nStr);
            }catch (NumberFormatException e){
                view.showError(R.string.warning_out_bounds);
            }
            int finalDInt = dInt;
            int finalNInt = nInt;

            calculateExtraData(eller, exponent, alphaviteCodes, finalNInt, finalDInt, p, q, enterCodeDecrypt);

            view.showCalculating(rsaMod.decryptingFE(finalDInt, finalNInt, enterCodeDecrypt));

            view.showCalculatingExtra(algebraMod.gcdGraph(eller, exponent));

        }else{
            view.showError(R.string.warning_enter_text);
        }
    }

    private void calculateExtraData(int eller, int exponent, List<Integer> alphaviteCodes, int n, int d, int p, int q, String enterCodeDecrypt){
        int dView = algebraMod.gcdGraph(eller, exponent).get(algebraMod.gcdGraph(eller, exponent).size()-1).getY2();
        final String[] strResult = {""};
        strResult[0] ="result : "+ rsaMod.decrypting(alphaviteCodes,d, n, enterCodeDecrypt).toUpperCase()+"\n"+"\n";

        strResult[0] +="n = "+p+"*"+q+" = "+n+";\n";

        strResult[0] +="eller = ("+p+"-1"+"*"+q+"-1"+") = "+eller+";\n"+
                "exponent: "+exponent+";\n";

        if (algebraMod.gcdGraph(eller, exponent).get(algebraMod.gcdGraph(eller, exponent).size()-1).getY2() < 0) {
            strResult[0] += "d = "+eller+" "+dView+"="+d+";";
        }
        view.showCalculatingExtra(strResult[0]);
    }
}
