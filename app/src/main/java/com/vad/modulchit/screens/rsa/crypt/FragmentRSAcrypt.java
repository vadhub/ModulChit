package com.vad.modulchit.screens.rsa.crypt;

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
import com.vad.modulchit.models.pojos.TableNumberFE;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
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
    private Navigator navigator;

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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alphabetCodes = requireArguments().getIntegerArrayList(ARG_ALPHABET);
        n = requireArguments().getInt(ARG_N_INT);
        exponents = requireArguments().getIntegerArrayList(ARG_EXPONENTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rsa_crypt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        cardIncludeFe = v.findViewById(R.id.cardIncludeFe);
        cardResultCrypt = v.findViewById(R.id.cardResultCrypt);
        cryptPresenter = new CryptPresenter(this);
        enterTextToCrypt = v.findViewById(R.id.editTextTextCrypt);
        editTextE = v.findViewById(R.id.editTextE);
        editTextN = v.findViewById(R.id.editTextN);
        textViewResult = v.findViewById(R.id.textViewResultCrypt);
        mRecyclerFeCrypt = v.findViewById(R.id.cryptRecycler);
        adapterFE = new AdapterFE();
        includeFeCrypt = v.findViewById(R.id.includeFe);
        mRecyclerFeCrypt.setLayoutManager(new LinearLayoutManager(getContext()));

        ((TextView) v.findViewById(R.id.textViewMfere)).setText("e");

        if (exponents != null) {
            editTextE.setText(String.valueOf(exponents.get(0)));
            editTextN.setText(String.valueOf(n));
        }

        v.findViewById(R.id.buttonCrypt).setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            navigator.hideKeyBoard();
            String eStr = editTextE.getText().toString();
            String nStr = editTextN.getText().toString();
            String textToEncrypt = enterTextToCrypt.getText().toString();
            cryptPresenter.result(alphabetCodes, textToEncrypt, eStr, nStr);
        }
    };

    @Override
    public void showError(int resource) {
        Toast.makeText(getContext(), "" + getString(resource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCalculating(List<TableNumberFE> tableNumberFEList) {
        adapterFE.setTableNumberFES(tableNumberFEList);
        mRecyclerFeCrypt.setAdapter(adapterFE);
    }

    @Override
    public void showCalculatingExtra(String encrypt) {
        String str = encrypt + getString(R.string.from_list) + exponents + getString(R.string.get_first) + editTextE.getText().toString();
        textViewResult.setText(str);
    }

    @Override
    public void showTitle() {
        cardResultCrypt.setVisibility(View.VISIBLE);
        cardIncludeFe.setVisibility(View.VISIBLE);
        includeFeCrypt.setVisibility(View.VISIBLE);
    }

    @Override
    public int getTitle() {
        return R.string.rsa_encrypt;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24, () -> {
            navigator.startFragment(new FragmentCryptExpl());
        });
    }

    @Override
    public void onDestroy() {
        cryptPresenter.disposableDispose();
        adapterFE = null;
        cryptPresenter = null;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}