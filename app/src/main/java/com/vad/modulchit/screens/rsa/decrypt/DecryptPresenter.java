package com.vad.modulchit.screens.rsa.decrypt;


import android.os.Handler;
import android.os.Looper;

import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;
import com.vad.modulchit.models.RSAmod;
import java.util.List;

public class DecryptPresenter {

    private final DecryptView view;
    private final AlgebraMod algebraMod;
    private final RSAmod rsaMod;

    public DecryptPresenter(DecryptView view) {
        algebraMod = new AlgebraMod();
        rsaMod = new RSAmod(algebraMod);
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

            view.showTitle();
            Handler handler = new Handler(Looper.getMainLooper());

            new Thread(() -> {
                handler.post(() -> {
                    calculateExtraData(eller, exponent, alphaviteCodes, finalNInt, finalDInt, p, q, enterCodeDecrypt);
                });
            }).start();

            view.showCalculating(rsaMod.decryptingFE(finalDInt, finalNInt, enterCodeDecrypt).first);
            view.showCalculatingExtra(algebraMod.gcdGraph(eller, exponent));
        }else{
            view.showError(R.string.warning_enter_text);
        }
    }

    private void calculateExtraData(int eller, int exponent, List<Integer> alphaviteCodes, int n, int d, int p, int q, String enterCodeDecrypt){

        int dView = algebraMod.gcdGraph(eller, exponent).get(algebraMod.gcdGraph(eller, exponent).size()-1).getY2();

        StringBuilder builder = new StringBuilder();
        builder.append("result: ").append(rsaMod.decrypting(alphaviteCodes, d, n, enterCodeDecrypt).toUpperCase()).append("\n").append("\n");
        System.out.println(rsaMod.decrypting(alphaviteCodes, d, n, enterCodeDecrypt).toUpperCase());
        builder.append("e: ").append(exponent).append(";\n");
        builder.append("n = ").append(p).append("*").append(q).append(" = ").append(n).append(";\n");
        builder.append("Euler = (").append(p).append("-1").append(")*(").append(q).append("-1").append(") = ")
                .append(eller).append(";\n");

        if (dView < 0) {
            builder.append("d = ").append(eller).append(" ").append(dView).append("=").append(d).append(";");
        }
        view.showCalculatingExtra(builder.toString());
    }
}
