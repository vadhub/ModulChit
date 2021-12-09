package com.vad.modulchit.screens.fe;

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
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterFE;
import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.screens.contract.HasCustomTitle;

import java.util.List;


public class FragmentFE extends Fragment implements ListFEView, HasCustomTitle {

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

        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        includeFE = (View) v.findViewById(R.id.includeFE);
        adapterFE = new AdapterFE();

        btnOk = (Button) v.findViewById(R.id.buttonFE);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aStr = editTextA.getText().toString();
                String mStr = editTextM.getText().toString();
                String nStr = editTextN.getText().toString();

                presenter.viewResult(aStr, mStr, nStr);
            }
        });
        return v;
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<TableNumberFE> tableNumberFEList) {
        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerView.setAdapter(adapterFE);
    }

    @Override
    public void showTitle() {
        includeFE.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.fast_exponentiation;
    }
}