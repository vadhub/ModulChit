package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterFE;
import com.vad.modulchit.utils.AlgebraMod;
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
    private TextView textViewMfere;
    private RecyclerView mRecyclerFeCrypt;
    private AdapterFE adapterFE;
    private int n;
    private List<Integer> exponents;
    private TextView textViewResult;
    private List<Integer> alphaviteCodes;
    private List<Integer> numbersCodesForCrypt;
    private RSAmod rsaMod;
    private RSAshiphr rsAshiphr;
    private View includeFeCrypt;

    public FragmentRSAcrypt(List<Integer> alphaviteCodes, int n, List<Integer> exponents) {
        this.alphaviteCodes = alphaviteCodes;
        this.n=n;
        this.exponents=exponents;
    }

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
        textViewMfere = (TextView) v.findViewById(R.id.textViewMfere);
        mRecyclerFeCrypt = (RecyclerView) v.findViewById(R.id.cryptRecycler);
        adapterFE = new AdapterFE();
        includeFeCrypt = (View) v.findViewById(R.id.includeFe);
        mRecyclerFeCrypt.setLayoutManager(new LinearLayoutManager(getContext()));

        textViewMfere.setText("e");
        rsaMod = new RSAmod();
        rsAshiphr = new RSAshiphr();

        editTextE.setText(String.valueOf(exponents.get(0)));
        editTextN.setText(String.valueOf(n));

        btnOk.setOnClickListener(clickListener);
        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            includeFeCrypt.setVisibility(View.VISIBLE);
            if(enterTextToCrypt.getText().toString().matches("[a-zA-Z\\s]+")){
                char[] strCrypt = enterTextToCrypt.getText().toString().toLowerCase().toCharArray();
                numbersCodesForCrypt = new ArrayList<>();
                for(int i = 0; i < strCrypt.length; i++){
                    for(int j =0; j < rsAshiphr.getAlphabyteEN().size(); j++){
                        if(rsAshiphr.getAlphabyteEN().get(j).equals(strCrypt[i])){
                            numbersCodesForCrypt.add(alphaviteCodes.get(j));
                            break;
                        }
                    }
                }

                if(!editTextE.getText().toString().equals("")||!editTextE.getText().toString().equals("")||
                    (!editTextE.getText().toString().equals("")&&!editTextE.getText().toString().equals(""))){
                     int e = Integer.parseInt(editTextE.getText().toString());
                     int n = Integer.parseInt(editTextN.getText().toString());

                    String str = rsaMod.encrypting(e, n, numbersCodesForCrypt)+"\n";
                    str+="from list "+exponents+" get first: "+ e;
                    adapterFE.setTableNumberFES(rsaMod.encryptingFE(e, n, numbersCodesForCrypt));
                    mRecyclerFeCrypt.setAdapter(adapterFE);
                    textViewResult.setText(str);

                }else{
                    Toast.makeText(getContext(), "Enter text", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(getContext(), "Enter only letters", Toast.LENGTH_SHORT).show();
            }

        }
    };
}