package com.vad.modulchit.screens.rsa.decrypt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.adapters.AdapterFE;
import com.vad.modulchit.screens.adapters.AdapterGCDe;
import com.vad.modulchit.models.pojos.TableNumberFE;
import com.vad.modulchit.models.pojos.TableNumberGCDe;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.rsa.decrypt.expldecrypt.FragmentDecryptExpl;

import java.util.ArrayList;
import java.util.List;

public class FragmentRSAdecrypt extends Fragment implements DecryptView, HasCustomTitle, HasCustomAction {

    private EditText enterCodeDecrypt;
    private EditText editTextD;
    private EditText editTextN;
    private TextView resultDecrypt;

    private int n;
    private int d;
    private int euler;
    private int exponent;
    private int p;
    private int q;
    private List<Integer> alphabetCodes;

    private RecyclerView mRecyclerDecrypt;
    private RecyclerView mRecyclerGCDe;
    private AdapterFE adapterFE;
    private AdapterGCDe adapterGCDe;
    private View includeFeDec;
    private View includeGCDEreverse;
    private DecryptPresenter presenter;
    private CardView cardGCDEreverse;
    private CardView cardIncludeFeDec;
    private CardView cardResultDecrypt;
    private Navigator navigator;

    private static final String ARG_ALPAVITE_LIST = "alphaviteCodes";
    private static final String ARG_N_INT = "n_int";
    private static final String ARG_D_INT = "d_int";
    private static final String ARG_ELLER = "eller";
    private static final String ARG_EXPONENT = "exponent";
    private static final String ARG_P_INT = "p_int";
    private static final String ARG_Q_INT = "q_int";

    public static FragmentRSAdecrypt newInstance(List<Integer> alphabetCodes, int n, int d, int euler, int exponent, int p, int q) {
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_ALPAVITE_LIST, (ArrayList<Integer>) alphabetCodes);
        args.putInt(ARG_N_INT, n);
        args.putInt(ARG_D_INT, d);
        args.putInt(ARG_ELLER, euler);
        args.putInt(ARG_EXPONENT, exponent);
        args.putInt(ARG_P_INT, p);
        args.putInt(ARG_Q_INT, q);
        FragmentRSAdecrypt fragmentRSAdecrypt = new FragmentRSAdecrypt();
        fragmentRSAdecrypt.setArguments(args);
        return fragmentRSAdecrypt;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alphabetCodes = requireArguments().getIntegerArrayList(ARG_ALPAVITE_LIST);
        n = requireArguments().getInt(ARG_N_INT);
        d = requireArguments().getInt(ARG_D_INT);
        euler = requireArguments().getInt(ARG_ELLER);
        exponent = requireArguments().getInt(ARG_EXPONENT);
        p = requireArguments().getInt(ARG_P_INT);
        q = requireArguments().getInt(ARG_Q_INT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rsa_decrypt, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        cardResultDecrypt = v.findViewById(R.id.cardResultDecrypt);
        cardIncludeFeDec = v.findViewById(R.id.cardIncludeFeDec);
        cardGCDEreverse = v.findViewById(R.id.cardGCDEreverse);
        presenter = new DecryptPresenter(this);
        enterCodeDecrypt = v.findViewById(R.id.editTextCodeDecrypt);
        editTextD = v.findViewById(R.id.editTextDdecript);
        editTextN = v.findViewById(R.id.editTextNdecrypt);
        resultDecrypt = v.findViewById(R.id.textViewResultDecrypt);
        mRecyclerDecrypt = v.findViewById(R.id.decryptRecycler);
        mRecyclerDecrypt.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerDecrypt.setNestedScrollingEnabled(false);

        mRecyclerGCDe = v.findViewById(R.id.gcdeDecryptRecycler);
        mRecyclerGCDe.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerGCDe.setNestedScrollingEnabled(false);

        adapterFE = new AdapterFE();
        adapterGCDe = new AdapterGCDe();
        includeFeDec = v.findViewById(R.id.includeFeDec);
        includeGCDEreverse = v.findViewById(R.id.includeGCDEreverse);

        ((TextView) v.findViewById(R.id.textViewMfere)).setText("d");

        editTextD.setText(d + "");
        editTextN.setText(n + "");

        v.findViewById(R.id.buttonDecrypt).setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            navigator.hideKeyBoard();
            String dStr = editTextD.getText().toString();
            String nStr = editTextN.getText().toString();

            presenter.decrypt(dStr, nStr, euler, exponent, alphabetCodes, enterCodeDecrypt.getText().toString(), p, q);
        }
    };

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), "" + getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCalculating(List<TableNumberFE> tableNumberFEList) {

        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerDecrypt.setAdapter(adapterFE);
    }

    @Override
    public void showCalculatingExtraWithList(List<TableNumberGCDe> tableNumberGCDeList) {
        adapterGCDe.setTableNumbers(tableNumberGCDeList);
        mRecyclerGCDe.setAdapter(adapterGCDe);
    }

    @Override
    public void showCalculatingExtra(String decrypt) {
        resultDecrypt.setText(decrypt);
    }

    @Override
    public void showTitle() {
        cardIncludeFeDec.setVisibility(View.VISIBLE);
        cardGCDEreverse.setVisibility(View.VISIBLE);
        cardResultDecrypt.setVisibility(View.VISIBLE);
        includeFeDec.setVisibility(View.VISIBLE);
        includeGCDEreverse.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.rsa_decrypt;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24, () -> {
            navigator.startFragment(new FragmentDecryptExpl());
        });
    }

    @Override
    public void onDestroy() {
        presenter.disposeDisposable();
        presenter = null;
        adapterFE = null;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}