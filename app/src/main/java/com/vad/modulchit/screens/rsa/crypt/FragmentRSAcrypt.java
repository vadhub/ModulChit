package com.vad.modulchit.screens.rsa.crypt;

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
import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.fe.explfe.FragmentFEexpl;
import com.vad.modulchit.screens.rsa.crypt.explcrypt.FragmentCryptExpl;

import java.util.ArrayList;
import java.util.List;

public class FragmentRSAcrypt extends Fragment implements CryptView, HasCustomTitle, HasCustomAction {

    private EditText enterTextToCrypt;
    private EditText editTextE;
    private EditText editTextN;
    private RecyclerView mRecyclerFeCrypt;
    private AdapterFE adapterFE;
    private TextView textViewResult;

    private int n;
    private List<Integer> exponents;
    private List<Integer> alphabetCodes;

    private CryptPresenter cryptPresenter;
    private CardView cardResultCrypt;
    private CardView cardIncludeFe;

    private View includeFeCrypt;

    private static final String ARG_ALPHABET = "alphaviteCodes";
    private static final String ARG_N_INT = "n_int";
    private static final String ARG_EXPONENTS = "exponents";

    public static FragmentRSAcrypt newInstance(List<Integer> alphaviteCodes, int n, List<Integer> exponents) {
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_ALPHABET, (ArrayList<Integer>) alphaviteCodes);
        args.putInt(ARG_N_INT, n);
        args.putIntegerArrayList(ARG_EXPONENTS, (ArrayList<Integer>) exponents);
        FragmentRSAcrypt fragmentRSAcrypt = new FragmentRSAcrypt();
        fragmentRSAcrypt.setArguments(args);
        return fragmentRSAcrypt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alphabetCodes = getArguments().getIntegerArrayList(ARG_ALPHABET);
        n = requireArguments().getInt(ARG_N_INT);
        exponents = requireArguments().getIntegerArrayList(ARG_EXPONENTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_crypt, container, false);

        cardIncludeFe = (CardView) v.findViewById(R.id.cardIncludeFe);
        cardResultCrypt = (CardView) v.findViewById(R.id.cardResultCrypt);
        cryptPresenter = new CryptPresenter(this);
        Button btnOk = (Button) v.findViewById(R.id.buttonCrypt);
        enterTextToCrypt = (EditText) v.findViewById(R.id.editTextTextCrypt);
        editTextE = (EditText) v.findViewById(R.id.editTextE);
        editTextN = (EditText) v.findViewById(R.id.editTextN);
        textViewResult = (TextView) v.findViewById(R.id.textViewResultCrypt);
        TextView textViewMfere = (TextView) v.findViewById(R.id.textViewMfere);
        mRecyclerFeCrypt = (RecyclerView) v.findViewById(R.id.cryptRecycler);
        adapterFE = new AdapterFE();
        includeFeCrypt = (View) v.findViewById(R.id.includeFe);
        mRecyclerFeCrypt.setLayoutManager(new LinearLayoutManager(getContext()));

        textViewMfere.setText("e");

        if (exponents != null) {
            editTextE.setText(String.valueOf(exponents.get(0)));
            editTextN.setText(String.valueOf(n));
        }

        btnOk.setOnClickListener(clickListener);
        return v;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String eStr = editTextE.getText().toString();
            String nStr = editTextN.getText().toString();
            String textToEncrypt = enterTextToCrypt.getText().toString();
            cryptPresenter.result(alphabetCodes, textToEncrypt, eStr, nStr);
            cardResultCrypt.setVisibility(View.VISIBLE);
            cardIncludeFe.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), ""+getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCalculating(List<TableNumberFE> tableNumberFEList) {
        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerFeCrypt.setAdapter(adapterFE);
    }

    @Override
    public void showCalculatingExtra(String encrypt) {
        String str = encrypt + getString(R.string.from_list)+exponents+getString(R.string.get_first)+ editTextE.getText().toString();
        textViewResult.setText(str);
    }

    @Override
    public void showTitle() {
        includeFeCrypt.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.rsa_encrypt;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24,() -> {
            ((Navigator) requireActivity()).startFragment(new FragmentCryptExpl());
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cryptPresenter.disposableDispose();
    }
}