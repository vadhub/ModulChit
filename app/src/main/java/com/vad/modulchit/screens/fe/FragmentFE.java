package com.vad.modulchit.screens.fe;

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


public class FragmentFE extends Fragment implements ListFEView{

    private EditText editTextA;
    private EditText editTextM;
    private EditText editTextN;

    private RecyclerView mRecyclerView;
    private AdapterFE adapterFE;

    private Button btnOk;
    private View includeFE;
    private ListFEpresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_f_e, container, false);

        presenter = new ListFEpresenter(this);
        editTextA = (EditText) v.findViewById(R.id.editTextNumberAFe);
        editTextM = (EditText) v.findViewById(R.id.editTextNumberMFe);
        editTextN = (EditText) v.findViewById(R.id.editTextNumberNFe);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerFE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        includeFE = (View) v.findViewById(R.id.includeFE);
        adapterFE = new AdapterFE();

        btnOk = (Button) v.findViewById(R.id.buttonFE);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aStr = editTextA.getText().toString();
                String mStr = editTextM.getText().toString();
                String nStr = editTextN.getText().toString();

                if(!aStr.equals("")&&!mStr.equals("")&&!nStr.equals("")){
                    int a =-1;
                    int m =-1;
                    int n =-1;

                    try {
                        a = Integer.parseInt(aStr);
                        m = Integer.parseInt(mStr);
                        n = Integer.parseInt(nStr);
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), getResources().getString(R.string.warning_out_bounds), Toast.LENGTH_SHORT).show();
                    }

                    includeFE.setVisibility(View.VISIBLE);
                    if(m!=0&&n!=0){
                        presenter.loadListFE(a, m, n);
                    }else{
                        Toast.makeText(getContext(), getResources().getString(R.string.warning_zero), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_text), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    @Override
    public void showData(List<TableNumberFE> tableNumberFEList) {
        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerView.setAdapter(adapterFE);
    }
}