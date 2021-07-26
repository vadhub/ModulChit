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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterNOK;
import com.vad.modulchit.pojos.TableNumberNOK;
import com.vad.modulchit.utils.AlgebraMod;

import java.util.List;


public class FragmentNOK extends Fragment {

    private EditText editTextMod;
    private Button btnNok;
    private RecyclerView mRecyclerView;
    private AdapterNOK adapterNOK;
    private AlgebraMod algebraMod;
    private TextView textViewResult;
    private View includeMG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_n_o_k, container, false);

        editTextMod = (EditText) v.findViewById(R.id.editTextTextMod);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
        btnNok = (Button) v.findViewById(R.id.buttonNOK);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerNok);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        includeMG = (View) v.findViewById(R.id.includeMG);
        adapterNOK = new AdapterNOK();
        algebraMod = new AlgebraMod();

        btnNok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextMod.getText().toString().equals("")){
                    int m = Integer.parseInt(editTextMod.getText().toString());

                    includeMG.setVisibility(View.VISIBLE);
                    if(m!=0){
                        List<TableNumberNOK> numberNOKS = algebraMod.nokGraph(m);
                        textViewResult.setText(getResult(numberNOKS));
                        adapterNOK.setTableNumberNOKS(numberNOKS);
                        mRecyclerView.setAdapter(adapterNOK);
                    }else{
                        Toast.makeText(getContext(), "Zero is invalid!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Enter the number!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private String getResult(List<TableNumberNOK> noks){
        String txtRes = "(1";

        int tmp = 0;
        for(TableNumberNOK nok: noks){
            if(nok.getBn()==0&&nok.getAn()==1){
                if(tmp!=nok.getI()){
                    txtRes+="; "+ nok.getI();
                }
            }
        }

        return txtRes+")";
    }
}