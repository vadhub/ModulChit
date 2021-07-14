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
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentRSAcrypt extends Fragment {

    private Button btnOk;
    private EditText enterTextToCrypt;
    private EditText editTextE;
    private EditText editTextN;
    private TextView textViewResult;
    private List<Integer> alphaviteCodes;
    private List<Integer> numbersCodesForCrypt;
    private RSAmod rsaMod;
    private RSAshiphr rsAshiphr;

    public FragmentRSAcrypt(List<Integer> alphaviteCodes) {
        this.alphaviteCodes = alphaviteCodes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_crypt, container, false);

        btnOk = (Button) v.findViewById(R.id.buttonCrypt);
        enterTextToCrypt = (EditText) v.findViewById(R.id.editTextTextCrypt);
        editTextE = (EditText) v.findViewById(R.id.editTextE);
        editTextN = (EditText) v.findViewById(R.id.editTextN);
        textViewResult = (TextView) v.findViewById(R.id.textViewResultCrypt);
        rsaMod = new RSAmod();
        rsAshiphr = new RSAshiphr();

        btnOk.setOnClickListener(clickListener);
        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            char[] strCrypt = enterTextToCrypt.getText().toString().toCharArray();
            numbersCodesForCrypt = new ArrayList<>();
            for(int i = 0; i < strCrypt.length; i++){
                for(int j =0; j < rsAshiphr.getAlphabyte().size(); j++){
                    if(rsAshiphr.getAlphabyte().get(j).equals(strCrypt[i])){
                        numbersCodesForCrypt.add(alphaviteCodes.get(j));
                        break;
                    }
                }
            }

            if(!editTextE.getText().toString().equals("")||!editTextE.getText().toString().equals("")||
                    (!editTextE.getText().toString().equals("")&&!editTextE.getText().toString().equals(""))){
                int e = Integer.parseInt(editTextE.getText().toString());
                int n = Integer.parseInt(editTextN.getText().toString());

                String str = rsaMod.crypting(e, n, numbersCodesForCrypt);
                textViewResult.setText(str);
            }
        }
    };
}