package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterRSAalphabyte;
import com.vad.modulchit.utils.RSAmod;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class AddAlphaviteFragment extends Fragment {

    private Button btnNext;
    private EditText numberForFirstLetter;
    private EditText numberP;
    private EditText numberQ;
    private RecyclerView mRecyclerView;
    private AdapterRSAalphabyte adapterRSAalphabyte;
    private List<Integer> numberCodes;
    private Spinner spinner;
    private RSAshiphr shiphr;
    private RSAmod rsaMod;
    private int theChoice = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_alphavite, container, false);
        btnNext = (Button) v.findViewById(R.id.btnNetx);
        numberForFirstLetter = (EditText) v.findViewById(R.id.textViewNumberFirst);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerAlphabyte);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapterRSAalphabyte = new AdapterRSAalphabyte();
        spinner = (Spinner) v.findViewById(R.id.spinner);
        numberP = (EditText) v.findViewById(R.id.editTextNumberP);
        numberQ = (EditText) v.findViewById(R.id.editTextNumberQ);
        shiphr = new RSAshiphr();
        rsaMod = new RSAmod();
        numberCodes = new ArrayList<>();

        numberCodes.addAll(shiphr.getNumberShiphr());
        adapterRSAalphabyte.setNumbersCode(numberCodes);
        mRecyclerView.setAdapter(adapterRSAalphabyte);


        ArrayAdapter<?> adapterSpinner = ArrayAdapter.createFromResource(getContext(), R.array.modifyRsaAlpabyte, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        btnNext.setOnClickListener(clickListener);
        numberForFirstLetter.addTextChangedListener(textWatcher);

        return v;
    }

    //selecting modification on aphavite
    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
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
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!numberP.getText().toString().equals("")&&!numberQ.getText().toString().equals("")){
                int p = Integer.parseInt(numberP.getText().toString());
                int q = Integer.parseInt(numberQ.getText().toString());

                if(!rsaMod.isSimpleNumber(p)&&!rsaMod.isSimpleNumber(q)){
                    Toast.makeText(getContext(), "Enter simple numbers", Toast.LENGTH_SHORT).show();
                }
            }else{

                if(numberForFirstLetter.getText().toString().equals("")){

                    if(theChoice==0){
                        getFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentRSAcrypt(numberCodes, Integer.parseInt(numberP.getText().toString()), Integer.parseInt(numberQ.getText().toString()))).commit();
                    }else {
                        Toast.makeText(getContext(), "Enter text", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentRSAcrypt(numberCodes, Integer.parseInt(numberP.getText().toString()), Integer.parseInt(numberQ.getText().toString()))).commit();
                }
            }

        }
    };

    //on edittext to enter start number
    private TextWatcher textWatcher = new TextWatcher() {
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
                Toast.makeText(getContext(), "Enter text", Toast.LENGTH_SHORT).show();
            }
        }else{
            switch (i){
                case 0:
                    numberCodes = shiphr.getNumberShiphr();
                    break;
                case 1:
                    numberCodes = shiphr.getNumberShiphr(Integer.parseInt(numberForFirstLetter.getText().toString()));
                    break;
            }

            adapterRSAalphabyte.setNumbersCode(numberCodes);
            mRecyclerView.setAdapter(adapterRSAalphabyte);

        }


    }
}