package com.vad.modulchit.screens.rsa.alphavite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterRSAalphabyte;
import com.vad.modulchit.screens.rsa.FragmentRSAcrypt;
import com.vad.modulchit.screens.rsa.FragmentRSAdecrypt;
import com.vad.modulchit.utils.RSAmod;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.List;

public class AddAlphaviteFragment extends Fragment implements AlphaviteView{

    private EditText numberForFirstLetter;
    private EditText numberP;
    private EditText numberQ;
    private RecyclerView mRecyclerView;
    private AdapterRSAalphabyte adapterRSAalphabyte;
    private Spinner spinner;

    private RSAmod rsaMod;
    private int theChoice = 0;
    private boolean isEncrypt = true;
    private ProgressBar progressBar;
    private AlphavitePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_alphavite, container, false);
        Button btnNext = (Button) v.findViewById(R.id.btnNetx);
        setRetainInstance(true);

        presenter = new AlphavitePresenter(this);

        numberForFirstLetter = (EditText) v.findViewById(R.id.textViewNumberFirst);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerAlphabyte);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterRSAalphabyte = new AdapterRSAalphabyte();
        spinner = (Spinner) v.findViewById(R.id.spinner);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBarAlpha);

        numberP = (EditText) v.findViewById(R.id.editTextNumberP);
        numberQ = (EditText) v.findViewById(R.id.editTextNumberQ);

        rsaMod = new RSAmod();

        RadioButton radioButtonEncrypt = (RadioButton) v.findViewById(R.id.radioButtonEncrypt);
        RadioButton radioButtonDecrypt = (RadioButton) v.findViewById(R.id.radioButtonDecrypt);

        ArrayAdapter<?> adapterSpinner = ArrayAdapter.createFromResource(getContext(), R.array.modifyRsaAlpabyte, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        btnNext.setOnClickListener(clickListener);

        numberForFirstLetter.addTextChangedListener(textWatcher);
        radioButtonDecrypt.setOnClickListener(radioButtonClick);
        radioButtonEncrypt.setOnClickListener(radioButtonClick);

        presenter.alphaviteLoad();
        return v;
    }

    private final View.OnClickListener radioButtonClick = view -> {
        RadioButton radioButton = (RadioButton)view;
        switch (radioButton.getId()){
            case R.id.radioButtonEncrypt: isEncrypt = true;
            break;
            case R.id.radioButtonDecrypt: isEncrypt = false;
            break;
        }
    };

    //selecting modification on aphavite
    private final AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            theChoice = i;
            update(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    //btn next
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = null;

            String qStr = numberQ.getText().toString();
            String pStr = numberP.getText().toString();

            int n;
            int eller;
            List<Integer> exponents;
            if(isEncrypt){
                if(!qStr.equals("")&&!pStr.equals("")){
                    int p = Integer.parseInt(qStr);
                    int q = Integer.parseInt(pStr);

                    n = rsaMod.getN(p,q);
                    eller = rsaMod.functionEller(p,q);
                    exponents = rsaMod.exponenta(eller);

                    if(rsaMod.isSimpleNumber(p)&&rsaMod.isSimpleNumber(q)){
                        fragment = new FragmentRSAcrypt(alphaviteCodes, n, exponents);
                    }else{
                        Toast.makeText(getContext(), getResources().getString(R.string.warning_prime), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    fragment = new FragmentRSAcrypt(alphaviteCodes);
                }
            }else{
                if(!qStr.equals("")&&!pStr.equals("")){
                    int p = Integer.parseInt(pStr);
                    int q = Integer.parseInt(qStr);

                    n =rsaMod.getN(p,q);
                    eller = rsaMod.functionEller(p,q);
                    exponents = rsaMod.exponenta(eller);
                    int d = rsaMod.getDPrivate(eller, exponents.get(0));

                    if(rsaMod.isSimpleNumber(p)&&rsaMod.isSimpleNumber(q)){
                        fragment = new FragmentRSAdecrypt(alphaviteCodes, n, d, eller, exponents.get(0), p, q);
                    }else{
                        Toast.makeText(getContext(), getResources().getString(R.string.warning_prime), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_p_q), Toast.LENGTH_SHORT).show();
                }
            }

            if(fragment!=null){
                getFragmentManager().beginTransaction().replace(R.id.frame_replace, fragment).commit();
            }
        }
    };

    //on edittext to enter start number
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            spinner.setSelection(1);
            update(theChoice);

        }
    };

    private void update(int i){

        if(numberForFirstLetter.getText().toString().equals("")){
            if(i!=0){
                Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_text), Toast.LENGTH_SHORT).show();
            }
        }else{
            presenter.alphaviteChosen(i, Integer.parseInt(numberForFirstLetter.getText().toString()));
        }

    }

    @Override
    public void alphaviteLoad(List<Integer> alphaviteCodes) {
        new Thread(() -> getActivity().runOnUiThread(() -> {
            progressBar.setVisibility(View.VISIBLE);
            adapterRSAalphabyte.setNumbersCode(alphaviteCodes);
            mRecyclerView.setAdapter(adapterRSAalphabyte);
            progressBar.setVisibility(View.INVISIBLE);
        })).start();
    }
}