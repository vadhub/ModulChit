package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterRSAalphabyte;

public class AddAlphaviteFragment extends Fragment {

    private Button btnNext;
    private EditText numberForFirstLetter;
    private RecyclerView mRecyclerView;
    private AdapterRSAalphabyte adapterRSAalphabyte;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_alphavite, container, false);

        btnNext = (Button) v.findViewById(R.id.btnNetx);
        numberForFirstLetter = (EditText) v.findViewById(R.id.textViewNumberFirst);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerAlphabyte);
        adapterRSAalphabyte = new AdapterRSAalphabyte();
        spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<?> adapterSpinner = ArrayAdapter.createFromResource(getContext(), R.array.modifyRsaAlpabyte, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(onItemSelectedListener);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return v;
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}