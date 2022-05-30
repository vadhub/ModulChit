package com.vad.modulchit.screens.rsa.decrypt;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterFE;
import com.vad.modulchit.adapters.AdapterGCDe;
import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.pojos.TableNumberGCDe;
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
    private Button btnResult;

    private int n;
    private int d;
    private int eller;
    private int exponent;
    private int p;
    private int q;
    private List<Integer> alphaviteCodes;
    private TextView textViewMfere;

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

    private static final String ARG_ALPAVITE_LIST = "alphaviteCodes";
    private static final String ARG_N_INT = "n_int";
    private static final String ARG_D_INT = "d_int";
    private static final String ARG_ELLER = "eller";
    private static final String ARG_EXPONENT = "exponent";
    private static final String ARG_P_INT = "p_int";
    private static final String ARG_Q_INT = "q_int";

    public static FragmentRSAdecrypt newInstance(List<Integer> alphaviteCodes, int n, int d, int eller, int exponent, int p, int q) {
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_ALPAVITE_LIST, (ArrayList<Integer>) alphaviteCodes);
        args.putInt(ARG_N_INT, n);
        args.putInt(ARG_D_INT, d);
        args.putInt(ARG_ELLER, eller);
        args.putInt(ARG_EXPONENT, exponent);
        args.putInt(ARG_P_INT, p);
        args.putInt(ARG_Q_INT, q);
        FragmentRSAdecrypt fragmentRSAdecrypt = new FragmentRSAdecrypt();
        fragmentRSAdecrypt.setArguments(args);
        return fragmentRSAdecrypt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alphaviteCodes = requireArguments().getIntegerArrayList(ARG_ALPAVITE_LIST);
        n = requireArguments().getInt(ARG_N_INT);
        d = requireArguments().getInt(ARG_D_INT);
        eller = requireArguments().getInt(ARG_ELLER);
        exponent = requireArguments().getInt(ARG_EXPONENT);
        p = requireArguments().getInt(ARG_P_INT);
        q = requireArguments().getInt(ARG_Q_INT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_decrypt, container, false);

        cardResultDecrypt = (CardView) v.findViewById(R.id.cardResultDecrypt);
        cardIncludeFeDec = (CardView) v.findViewById(R.id.cardIncludeFeDec);
        cardGCDEreverse = (CardView) v.findViewById(R.id.cardGCDEreverse);
        presenter = new DecryptPresenter(this);
        enterCodeDecrypt = v.findViewById(R.id.editTextCodeDecrypt);
        editTextD = v.findViewById(R.id.editTextDdecript);
        editTextN = v.findViewById(R.id.editTextNdecrypt);
        resultDecrypt = v.findViewById(R.id.textViewResultDecrypt);
        btnResult = v.findViewById(R.id.buttonDecrypt);
        textViewMfere = v.findViewById(R.id.textViewMfere);
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

        textViewMfere.setText("d");

        editTextD.setText(d+"");
        editTextN.setText(n+"");

        btnResult.setOnClickListener(clickListener);

        return v;
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String dStr = editTextD.getText().toString();
            String nStr = editTextN.getText().toString();

            presenter.decrypt(dStr, nStr, eller, exponent, alphaviteCodes, enterCodeDecrypt.getText().toString(), p, q);
            cardIncludeFeDec.setVisibility(View.VISIBLE);
            cardGCDEreverse.setVisibility(View.VISIBLE);
            cardResultDecrypt.setVisibility(View.VISIBLE);

        }
    };

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
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
        includeFeDec.setVisibility(View.VISIBLE);
        includeGCDEreverse.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.rsa_decrypt;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24,() -> {
            ((Navigator) requireActivity()).startFragment(new FragmentDecryptExpl());
        });
    }

    @Override
    public void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }
}