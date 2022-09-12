package com.vad.modulchit.screens.gcde;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vad.modulchit.screens.adapters.AdapterGCDe;
import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.TableNumberGCDe;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.gcde.explgcde.FragmentGCDexpl;

import java.util.List;

public class FragmentGCDe extends Fragment implements ListGCDEView, HasCustomTitle, HasCustomAction {

    private EditText editTextA;
    private EditText editTextB;
    private Button btnOk;

    private RecyclerView mRecyclerView;
    private AdapterGCDe adapterGCDe;

    private View includeTitle;
    private ListGCDEpresenter presenter;
    private CardView cardView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gcde, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        presenter = new ListGCDEpresenter(this);
        editTextA = (EditText) v.findViewById(R.id.editTextA);
        editTextB = (EditText) v.findViewById(R.id.editTextB);
        btnOk = (Button) v.findViewById(R.id.button);
        includeTitle = (View) v.findViewById(R.id.includeGCDE);
        adapterGCDe = new AdapterGCDe();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.myRecyclerGcde);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cardView = v.findViewById(R.id.cardGCDErecycler);

        ((SimpleItemAnimator)mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        btnOk.setOnClickListener(view -> {
            ((Navigator) requireActivity()).hideKeyBoard();
            String aStr = editTextA.getText().toString();
            String bStr = editTextB.getText().toString();

            presenter.showResult(aStr, bStr);
        });

    }

    @Override
    public void showData(List<TableNumberGCDe> tableNumberGCDeList) {
        adapterGCDe.setTableNumbers(tableNumberGCDeList);
        mRecyclerView.setAdapter(adapterGCDe);
        cardView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle() {
        includeTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.gcd_e;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24,() -> {
            ((Navigator) requireActivity()).startFragment(new FragmentGCDexpl());
        });
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.disposableDispose();
        }
        presenter = null;
        adapterGCDe = null;
        super.onDestroy();
    }
}