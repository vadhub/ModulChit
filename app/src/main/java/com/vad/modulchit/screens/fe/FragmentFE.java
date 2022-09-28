package com.vad.modulchit.screens.fe;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.adapters.AdapterFE;
import com.vad.modulchit.models.pojos.TableNumberFE;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.fe.explfe.FragmentFEexpl;

import java.util.List;


public class FragmentFE extends Fragment implements ListFEView, HasCustomTitle, HasCustomAction {

    private EditText editTextA;
    private EditText editTextM;
    private EditText editTextN;

    private RecyclerView mRecyclerView;
    private AdapterFE adapterFE;

    private View includeFE;
    private FEpresenter presenter;
    private Navigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        presenter = new FEpresenter(this);
        editTextA = v.findViewById(R.id.editTextNumberAFe);
        editTextM = v.findViewById(R.id.editTextNumberMFe);
        editTextN = v.findViewById(R.id.editTextNumberNFe);

        mRecyclerView = v.findViewById(R.id.mRecyclerFE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        includeFE = v.findViewById(R.id.includeFE);
        adapterFE = new AdapterFE();

        v.findViewById(R.id.buttonFE).setOnClickListener(view -> {
            navigator.hideKeyBoard();
            String aStr = editTextA.getText().toString();
            String mStr = editTextM.getText().toString();
            String nStr = editTextN.getText().toString();

            presenter.viewResult(aStr, mStr, nStr);
        });
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), "" + getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showData(List<TableNumberFE> tableNumberFEList) {
        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerView.setAdapter(adapterFE);
    }

    @Override
    public void showTitle() {
        includeFE.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.fast_exponentiation;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24, () -> {
            navigator.startFragment(new FragmentFEexpl());
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.disposableDispose();
        }
        presenter = null;
        adapterFE = null;
        super.onDestroy();
    }
}