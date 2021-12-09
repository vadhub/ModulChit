 package com.vad.modulchit.screens.mg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterNOK;
import com.vad.modulchit.pojos.TableNumberNOK;
import com.vad.modulchit.screens.contract.HasCustomTitle;

import java.util.List;


public class FragmentMG extends Fragment implements ListMGView, HasCustomTitle {

    private EditText editTextMod;
    private Button btnNok;
    private RecyclerView mRecyclerView;
    private AdapterNOK adapterNOK;
    private ListMGpresenter presenter;

    private TextView textViewResult;
    private View includeMG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mg, container, false);

        presenter = new ListMGpresenter(this);

        editTextMod = (EditText) v.findViewById(R.id.editTextTextMod);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
        btnNok = (Button) v.findViewById(R.id.buttonNOK);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerNok);

        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        includeMG = (View) v.findViewById(R.id.includeMG);
        adapterNOK = new AdapterNOK();
        btnNok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String modStr =editTextMod.getText().toString();
                presenter.showResult(modStr);
            }

        });

        return v;
    }

    private String getResult(List<TableNumberNOK> noks){
        StringBuilder txtRes = new StringBuilder("(1");

        int tmp = 0;
        for(TableNumberNOK nok: noks){
            if(nok.getBn()==0&&nok.getAn()==1){
                if(tmp!=nok.getI()){
                    txtRes.append("; ").append(nok.getI());
                }
            }
        }

        return txtRes+")";
    }

    @Override
    public void showData(List<TableNumberNOK> numberNOKS) {
        textViewResult.setText(getResult(numberNOKS));
        adapterNOK.setTableNumberNOKS(numberNOKS);
        mRecyclerView.setAdapter(adapterNOK);
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle() {
        includeMG.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.multiplicative_group;
    }
}