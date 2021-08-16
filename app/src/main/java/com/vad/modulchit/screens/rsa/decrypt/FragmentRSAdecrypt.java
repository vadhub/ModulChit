package com.vad.modulchit.screens.rsa.decrypt;

import android.os.Bundle;

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
import com.vad.modulchit.screens.rsa.crypt.CryptView;
import com.vad.modulchit.utils.AlgebraMod;
import com.vad.modulchit.utils.RSAmod;

import java.util.List;

public class FragmentRSAdecrypt extends Fragment implements DecryptView {

    private EditText enterCodeDecrypt;
    private EditText editTextD;
    private EditText editTextN;
    private TextView resultDecrypt;
    private Button btnResult;
    private final int n;
    private final int d;
    private final int eller;
    private final int exponent;
    private final int p;
    private final int q;
    private TextView textViewMfere;
    private final List<Integer> alphaviteCodes;
    private RecyclerView mRecyclerDecrypt;
    private RecyclerView mRecyclerGCDe;
    private AdapterFE adapterFE;
    private AdapterGCDe adapterGCDe;

    private View includeFeDec;
    private View includeGCDEreverse;
    private DecryptPresenter presenter;

    public FragmentRSAdecrypt(List<Integer> alphaviteCodes,int n, int d, int eller, int exponent, int p, int q) {
        this.alphaviteCodes = alphaviteCodes;
        this.n = n;
        this.d = d;
        this.eller = eller;
        this.exponent = exponent;
        this.p = p;
        this.q = q;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_decrypt, container, false);

        getActivity().setTitle("RSA Decrypt");

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

        }
    };

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCalculating(List<TableNumberFE> tableNumberFEList) {

        includeFeDec.setVisibility(View.VISIBLE);
        includeGCDEreverse.setVisibility(View.VISIBLE);

        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerDecrypt.setAdapter(adapterFE);
    }

    @Override
    public void showCalculatingExtra(List<TableNumberGCDe> tableNumberGCDeList) {
        adapterGCDe.setTableNumbers(tableNumberGCDeList);
        mRecyclerGCDe.setAdapter(adapterGCDe);
    }

    @Override
    public void showCalculatingExtra(String decrypt) {
        resultDecrypt.setText(decrypt);
    }
}