package com.vad.modulchit.screens.mg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.TableNumberNOK;
import com.vad.modulchit.screens.BaseFragment;
import com.vad.modulchit.screens.adapters.AdapterNOK;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.mg.explmg.FragmentMGexpl;

import java.util.List;


public class FragmentMG extends BaseFragment implements ListMGView, HasCustomTitle, HasCustomAction {

    private EditText editTextMod;
    private RecyclerView mRecyclerView;
    private AdapterNOK adapterNOK;
    private ListMGpresenter presenter;

    private TextView textViewResult;
    private View includeMG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        presenter = new ListMGpresenter(this);

        editTextMod = (EditText) v.findViewById(R.id.editTextTextMod);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerNok);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(v.getContext(), DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        includeMG = (View) v.findViewById(R.id.includeMG);
        adapterNOK = new AdapterNOK();
        v.findViewById(R.id.buttonNOK).setOnClickListener(view -> {
            navigator.hideKeyBoard();
            String modStr = editTextMod.getText().toString();
            presenter.showResult(modStr);
        });
    }

    @Override
    public void showData(List<TableNumberNOK> numberNOKS, String str) {
        textViewResult.setText(str);
        adapterNOK.setTableNumberNOKS(numberNOKS);
        mRecyclerView.setLayoutAnimation(layoutAnimationController);
        mRecyclerView.setAdapter(adapterNOK);
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), "" + getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle() {
        includeMG.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.multiplicative_group;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24, () -> {
            navigator.startFragment(new FragmentMGexpl());
        });
    }

    @Override
    public void onDestroy() {
        presenter.disposableDispose();
        presenter = null;
        adapterNOK = null;
        super.onDestroy();
    }
}