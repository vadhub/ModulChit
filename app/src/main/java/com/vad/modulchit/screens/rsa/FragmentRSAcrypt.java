package com.vad.modulchit.screens.rsa;

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
import com.vad.modulchit.utils.RSAmod;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
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

        getActivity().setTitle("RSA Encrypt");

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

        if(exponents!=null){
            editTextE.setText(String.valueOf(exponents.get(0)));
            editTextN.setText(String.valueOf(n));
        }

        btnOk.setOnClickListener(clickListener);
        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String eStr = editTextE.getText().toString();
            String nStr = editTextN.getText().toString();

            includeFeCrypt.setVisibility(View.VISIBLE);

            if(enterTextToCrypt.getText().toString().matches("[a-zA-Z\\s]+")){
                char[] strCrypt = enterTextToCrypt.getText().toString().toLowerCase().toCharArray();

                numbersCodesForCrypt = new ArrayList<>();

                new Thread(() -> {
                    for (char c : strCrypt) {
                        for (int j = 0; j < rsAshiphr.getAlphabyteEN().size(); j++) {
                            if (rsAshiphr.getAlphabyteEN().get(j).equals(c)) {
                                numbersCodesForCrypt.add(alphaviteCodes.get(j));
                                break;
                            }
                        }
                    }
                }).start();

                if(!eStr.equals("")&&!nStr.equals("")){
                    int e = -1;
                    int n = -1;

                    try {
                        e = Integer.parseInt(eStr);
                        n = Integer.parseInt(nStr);
                    }catch (NumberFormatException ex){
                        Toast.makeText(getContext(), getResources().getString(R.string.warning_out_bounds), Toast.LENGTH_SHORT).show();
                    }

                    int finalE = e;
                    int finalN = n;
                    new Thread(() -> getActivity().runOnUiThread(() -> {
                        adapterFE.setTableNumberFES(rsaMod.encryptingFE(finalE, finalN, numbersCodesForCrypt));
                        mRecyclerFeCrypt.setAdapter(adapterFE);

                        System.out.println(numbersCodesForCrypt);

                        String str = rsaMod.encrypting(finalE, finalN, numbersCodesForCrypt)+"\n";
                        str+=getString(R.string.from_list)+exponents+getString(R.string.get_first)+ finalE;

                        textViewResult.setText(str);
                        numbersCodesForCrypt=null;
                    })).start();

                }else{
                    Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_text), Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_letter), Toast.LENGTH_SHORT).show();
            }

        }
    };
}