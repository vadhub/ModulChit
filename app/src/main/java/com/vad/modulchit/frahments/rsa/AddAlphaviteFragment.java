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
import com.vad.modulchit.utils.RSAshiphr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class AddAlphaviteFragment extends Fragment {

    private Button btnNext;
    private EditText numberForFirstLetter;
    private RecyclerView mRecyclerView;
    private AdapterRSAalphabyte adapterRSAalphabyte;
    private List<Integer> numbersCode;
    private Spinner spinner;
    private RSAshiphr shiphr;
    private int theChoice;

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
        shiphr = new RSAshiphr();
        numbersCode = new ArrayList<>();

        numbersCode.addAll(shiphr.getNumberShiphr());
        adapterRSAalphabyte.setNumbersCode(numbersCode);
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
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    //btn next
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!numberForFirstLetter.getText().toString().equals("")){
                getFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentRSAcrypt(numbersCode)).commit();
            }else {
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
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
            List<Integer> numberCodes = new LinkedList<>();
            if(!numberForFirstLetter.getText().toString().equals("")){

                switch (theChoice){
                    case 0:
                        numberCodes = shiphr.getNumberShiphr(Integer.parseInt(numberForFirstLetter.getText().toString()), 10);
                        break;
                    case 1:
                        numberCodes = shiphr.getNumberShiphr(Integer.parseInt(numberForFirstLetter.getText().toString()));
                        break;
                    case 2:
                        numberCodes = shiphr.getNumberShiphr();
                        break;
                }

            }else{
                Toast.makeText(getContext(), "Enete text", Toast.LENGTH_SHORT);
            }

            adapterRSAalphabyte.setNumbersCode(numberCodes);
            mRecyclerView.setAdapter(adapterRSAalphabyte);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}