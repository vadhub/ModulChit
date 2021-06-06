package com.vad.modulchit.frahments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterFE;
import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;


public class FragmentFE extends Fragment {

    private EditText editTextA;
    private EditText editTextM;
    private EditText editTextN;

    private RecyclerView mRecyclerView;
    private AdapterFE adapterFE;

    private Button btnOk;

    private AlgebraMod algebraMod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_f_e, container, false);

        editTextA = (EditText) v.findViewById(R.id.editTextNumberAFe);
        editTextM = (EditText) v.findViewById(R.id.editTextNumberMFe);
        editTextN = (EditText) v.findViewById(R.id.editTextNumberNFe);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerFE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterFE = new AdapterFE();

        btnOk = (Button) v.findViewById(R.id.buttonFE);

        algebraMod = new AlgebraMod();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                        (!editTextA.getText().toString().equals("")||!editTextM.getText().toString().equals("")||!editTextN.getText().toString().equals(""))
                        &&(!editTextA.getText().toString().equals("")&&!editTextM.getText().toString().equals("")||!editTextN.getText().toString().equals(""))
                        &&(!editTextA.getText().toString().equals("")||!editTextM.getText().toString().equals("")&&!editTextN.getText().toString().equals(""))
                        &&(!editTextA.getText().toString().equals("")&&!editTextM.getText().toString().equals("")&&!editTextN.getText().toString().equals(""))
                ){

                    int a = Integer.parseInt(editTextA.getText().toString());
                    int m = Integer.parseInt(editTextM.getText().toString());
                    int n = Integer.parseInt(editTextN.getText().toString());

                    if(m!=0||n!=0){
                        if(m!=0&&n!=0){
                            List<TableNumberFE> tableNumberFES = algebraMod.feGraph(a, m, n);
                            adapterFE.setTableNumberFES(tableNumberFES);
                            mRecyclerView.setAdapter(adapterFE);
                        }else{
                            Toast.makeText(getContext(), "Zero is invalid!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getContext(), "Zero is invalid!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Enter the number!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return v;
    }
}