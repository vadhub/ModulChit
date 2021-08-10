package com.vad.modulchit.screens.mod;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.utils.AlgebraMod;

public class FragmentMod extends Fragment {

    private EditText editTextA;
    private EditText editTextB;

    private TextView textViewResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mod, container, false);

        editTextA = (EditText) v.findViewById(R.id.editTextNumberA);
        editTextB = (EditText) v.findViewById(R.id.editTextNumberB);

        textViewResult = (TextView) v.findViewById(R.id.textViewResultMod);

        Button btnMod = (Button) v.findViewById(R.id.buttonMod);

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextA.getText().toString().equals("")&&!editTextB.getText().toString().equals("")){
                    int a = Integer.parseInt(editTextA.getText().toString());
                    int b = Integer.parseInt(editTextB.getText().toString());

                    if(a!=0 && b!=0) {
                        textViewResult.setText(AlgebraMod.mod(a, b)+"");
                    }else{
                        Toast.makeText(getContext(), getResources().getString(R.string.warning_zero), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_text), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}