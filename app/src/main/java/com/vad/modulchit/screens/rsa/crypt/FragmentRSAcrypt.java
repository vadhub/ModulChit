package com.vad.modulchit.screens.rsa.crypt;

import android.os.Bundle;

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

import java.util.ArrayList;
import java.util.List;

public class FragmentRSAcrypt extends Fragment implements CryptView{

    private Button btnOk;
    private EditText enterTextToCrypt;
    private EditText editTextE;
    private EditText editTextN;
    private TextView textViewMfere;
    private RecyclerView mRecyclerFeCrypt;
    private AdapterFE adapterFE;
    private int n;
    private List<Integer> exponents;
    private TextView textViewResult;
    private List<Integer> alphaviteCodes;
    private CryptPresenter cryptPresenter;
    private CardView cardResultCrypt;
    private CardView cardIncludeFe;

    private View includeFeCrypt;

    public static FragmentRSAcrypt newInstance(List<Integer> alphaviteCodes, int n, List<Integer> exponents) {
        Bundle args = new Bundle();
        args.putIntegerArrayList("alphaviteCodes", (ArrayList<Integer>) alphaviteCodes);
        args.putInt("n_int", n);
        args.putIntegerArrayList("exponents", (ArrayList<Integer>) exponents);
        return new FragmentRSAcrypt();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_crypt, container, false);

        getActivity().setTitle("RSA Encrypt");

        cardIncludeFe = (CardView) v.findViewById(R.id.cardIncludeFe);
        cardResultCrypt = (CardView) v.findViewById(R.id.cardResultCrypt);
        cryptPresenter = new CryptPresenter(this);
        btnOk = (Button) v.findViewById(R.id.buttonCrypt);
        enterTextToCrypt = (EditText) v.findViewById(R.id.editTextTextCrypt);
        editTextE = (EditText) v.findViewById(R.id.editTextE);
        editTextN = (EditText) v.findViewById(R.id.editTextN);
        textViewResult = (TextView) v.findViewById(R.id.textViewResultCrypt);
        textViewMfere = (TextView) v.findViewById(R.id.textViewMfere);
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
            cryptPresenter.result(alphaviteCodes, textToEncrypt, eStr, nStr);
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
}