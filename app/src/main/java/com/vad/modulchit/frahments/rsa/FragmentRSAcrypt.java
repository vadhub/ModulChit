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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentRSAcrypt extends Fragment {

    private Button btnOk;
    private EditText enterTextToCrypt;
    private EditText editTextE;
    private EditText editTextN;
    private TextView textViewResult;
    private HashMap<Character, Integer> hashMapAlphavite;
    List<Integer> numbersCodesForCrypt;
    private RSAmod rsaMod;

    public FragmentRSAcrypt(HashMap<Character, Integer> hashMapAlphavite) {
        this.hashMapAlphavite = hashMapAlphavite;
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

        btnOk.setOnClickListener(clickListener);
        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            char[] strCrypt = enterTextToCrypt.getText().toString().toCharArray();
            numbersCodesForCrypt = new ArrayList<>();
            for (char c : strCrypt) {
                if (hashMapAlphavite.containsKey(c)) {
                        int num = hashMapAlphavite.get(c);
                        numbersCodesForCrypt.add(num);
                }
            }

            int e = Integer.parseInt(editTextE.getText().toString());
            int n = Integer.parseInt(editTextN.getText().toString());

            String str = rsaMod.crypting(e, n, numbersCodesForCrypt);

            textViewResult.setText(str);
        }
    };
}