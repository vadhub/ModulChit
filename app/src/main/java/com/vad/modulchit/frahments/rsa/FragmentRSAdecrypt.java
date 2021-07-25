package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterFE;
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
    private AdapterFE adapterFE;

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
        adapterFE = new AdapterFE();

        textViewMfere.setText("d");
        rsaMod = new RSAmod();

        editTextD.setText(d+"");
        editTextN.setText(n+"");

        btnResult.setOnClickListener(clickListener);

        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String strResult = rsaMod.decrypting(alphaviteCodes,Integer.parseInt(editTextD.getText().toString()), n, enterCodeDecrypt.getText().toString()).toUpperCase()+"\n"+"\n";
            strResult+="n = "+p+"*"+q+" = "+n+";\n"+
                    "eller = ("+p+"-1"+"*"+q+"-1"+") = "+eller+";\n"+
                    "exponent: "+exponent+";";

            adapterFE.setTableNumberFES(rsaMod.decryptingFE(d, n, enterCodeDecrypt.getText().toString()));
            mRecyclerDecrypt.setAdapter(adapterFE);
            resultDecrypt.setText(strResult);
        }
    };
}