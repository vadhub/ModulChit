package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterFE;
import com.vad.modulchit.adapters.AdapterGCDe;
import com.vad.modulchit.utils.AlgebraMod;
import com.vad.modulchit.utils.RSAmod;

import java.util.List;

public class FragmentRSAdecrypt extends Fragment {

    private EditText enterCodeDecrypt;
    private EditText editTextD;
    private EditText editTextN;
    private TextView resultDecrypt;
    private Button btnResult;
    private int n;
    private int d;
    private int eller;
    private int exponent;
    private int p;
    private int q;
    private TextView textViewMfere;
    private RSAmod rsaMod;
    private List<Integer> alphaviteCodes;
    private RecyclerView mRecyclerDecrypt;
    private RecyclerView mRecyclerGCDe;
    private AdapterFE adapterFE;
    private AdapterGCDe adapterGCDe;
    private AlgebraMod algebraMod;

    private View includeFeDec;
    private View includeGCDEreverse;

    public FragmentRSAdecrypt(List<Integer> alphaviteCodes,int n, int d, int eller, int exponent, int p, int q) {
        this.alphaviteCodes = alphaviteCodes;
        this.n = n;
        this.d = d;
        this.eller = eller;
        this.exponent = exponent;
        this.p = p;
        this.q = q;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_decrypt, container, false);

        enterCodeDecrypt = (EditText) v.findViewById(R.id.editTextCodeDecrypt);
        editTextD = (EditText) v.findViewById(R.id.editTextDdecript);
        editTextN = (EditText) v.findViewById(R.id.editTextNdecrypt);
        resultDecrypt = (TextView) v.findViewById(R.id.textViewResultDecrypt);
        btnResult = (Button) v.findViewById(R.id.buttonDecrypt);
        textViewMfere = (TextView) v.findViewById(R.id.textViewMfere);
        mRecyclerDecrypt = (RecyclerView) v.findViewById(R.id.decryptRecycler);
        mRecyclerDecrypt.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerGCDe = (RecyclerView) v.findViewById(R.id.gcdeDecryptRecycler);
        mRecyclerGCDe.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterFE = new AdapterFE();
        adapterGCDe = new AdapterGCDe();
        includeFeDec = (View) v.findViewById(R.id.includeFeDec);
        includeGCDEreverse = (View) v.findViewById(R.id.includeGCDEreverse);

        textViewMfere.setText("d");
        rsaMod = new RSAmod();
        algebraMod = new AlgebraMod();

        editTextD.setText(d+"");
        editTextN.setText(n+"");

        btnResult.setOnClickListener(clickListener);

        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String dStr = editTextD.getText().toString();
            String nStr = editTextN.getText().toString();

            if(!dStr.equals("")&&!nStr.equals("")){

                includeFeDec.setVisibility(View.VISIBLE);
                includeGCDEreverse.setVisibility(View.VISIBLE);

                int dInt = -1;
                int nInt = -1;

                try{
                    dInt = Integer.parseInt(dStr);
                    nInt = Integer.parseInt(nStr);
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), getResources().getString(R.string.warning_out_bounds), Toast.LENGTH_SHORT).show();
                }

                int dView = algebraMod.gcdGraph(eller, exponent).get(algebraMod.gcdGraph(eller, exponent).size()-1).getY2();
                String strResult = rsaMod.decrypting(alphaviteCodes,dInt, nInt, enterCodeDecrypt.getText().toString()).toUpperCase()+"\n"+"\n";

                if(n==nInt){
                    strResult+="n = "+p+"*"+q+" = "+n+";\n";
                }

                strResult+="eller = ("+p+"-1"+"*"+q+"-1"+") = "+eller+";\n"+
                        "exponent: "+exponent+";\n";

                if (algebraMod.gcdGraph(eller, exponent).get(algebraMod.gcdGraph(eller, exponent).size()-1).getY2() < 0) {
                    strResult += "d = "+eller+" "+dView+";";
                }

                adapterGCDe.setTableNumbers(algebraMod.gcdGraph(eller, exponent));
                mRecyclerGCDe.setAdapter(adapterGCDe);
                adapterFE.setTableNumberFES(rsaMod.decryptingFE(dInt, nInt, enterCodeDecrypt.getText().toString()));
                mRecyclerDecrypt.setAdapter(adapterFE);
                resultDecrypt.setText(strResult);
            }else{
                Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_text), Toast.LENGTH_SHORT).show();
            }
        }
    };
}