package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vad.modulchit.R;
import com.vad.modulchit.utils.RSAmod;

public class FragmentRSAdecrypt extends Fragment {

    private EditText enterCodeDecrypt;
    private EditText editTextD;
    private EditText editTextN;
    private TextView resultDecrypt;
    private Button btnResult;
    private int n;
    private int p;
    private int q;
    private int eller;
    private int e;
    private int d;
    private RSAmod rsaMod;

    public FragmentRSAdecrypt(int p, int q) {
        this.p=p;
        this.q=q;
    }

    public FragmentRSAdecrypt() {
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
        rsaMod = new RSAmod();

        if(q!=0&&p!=0||(q!=0||p!=0)){
            n= rsaMod.getN(p,q);
            eller = rsaMod.functionEller(p,q);
            e = rsaMod.exponenta(eller);
            d = rsaMod.getDPrivate(e, eller);
        }

        btnResult.setOnClickListener(clickListener);

        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String strResult = rsaMod.decrypting(d, n, enterCodeDecrypt.getText().toString());
            resultDecrypt.setText(strResult);

        }
    };
}