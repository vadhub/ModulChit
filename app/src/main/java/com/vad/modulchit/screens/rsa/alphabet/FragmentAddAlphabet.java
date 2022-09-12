package com.vad.modulchit.screens.rsa.alphabet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.adapters.AdapterRSAalphabyte;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;

import java.util.List;

public class FragmentAddAlphabet extends Fragment implements AlphabetView, HasCustomTitle {

    private EditText numberForFirstLetter;
    private EditText numberP;
    private EditText numberQ;
    private RecyclerView mRecyclerView;
    private AdapterRSAalphabyte adapterRSAalphabyte;

    private List<Integer> alphabet;
    private boolean isEncrypt = true;
    private ProgressBar progressBar;
    private AlphabetPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_alphabet, container, false);
        Button btnNext = (Button) v.findViewById(R.id.btnNetx);
        setRetainInstance(true);

        presenter = new AlphabetPresenter(this, (Navigator) requireActivity());

        numberForFirstLetter = (EditText) v.findViewById(R.id.editTextViewNumberFirst);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerAlphabet);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapterRSAalphabyte = new AdapterRSAalphabyte();
        progressBar = (ProgressBar) v.findViewById(R.id.progressBarAlpha);

        numberP = (EditText) v.findViewById(R.id.editTextNumberP);
        numberQ = (EditText) v.findViewById(R.id.editTextNumberQ);

        RadioButton radioButtonEncrypt = (RadioButton) v.findViewById(R.id.radioButtonEncrypt);
        RadioButton radioButtonDecrypt = (RadioButton) v.findViewById(R.id.radioButtonDecrypt);

        btnNext.setOnClickListener(clickListener);

        numberForFirstLetter.addTextChangedListener(textWatcher);
        radioButtonDecrypt.setOnClickListener(radioButtonClick);
        radioButtonEncrypt.setOnClickListener(radioButtonClick);

        presenter.alphabetLoad();
        return v;
    }

    private final View.OnClickListener radioButtonClick = view -> {
        RadioButton radioButton = (RadioButton)view;
        switch (radioButton.getId()){
            case R.id.radioButtonEncrypt: isEncrypt = true;
            break;
            case R.id.radioButtonDecrypt: isEncrypt = false;
            break;
        }
    };

    //btn next
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((Navigator) requireActivity()).hideKeyBoard();
            String qStr = numberQ.getText().toString();
            String pStr = numberP.getText().toString();
            presenter.fragmentChoose(isEncrypt, qStr, pStr, alphabet);
        }
    };

    //on edittext to enter start number
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (numberForFirstLetter.getText().toString().equals("")) {
                Toast.makeText(getContext(), getResources().getString(R.string.warning_enter_text), Toast.LENGTH_SHORT).show();
            } else {
                presenter.alphabetChosen(Integer.parseInt(numberForFirstLetter.getText().toString()));
            }
        }
    };


    @Override
    public void setAlphabet(List<Integer> alphabetCodes) {
        alphabet = alphabetCodes;
        progressBar.setVisibility(View.VISIBLE);
        adapterRSAalphabyte.setNumbersCode(alphabetCodes);
        mRecyclerView.setAdapter(adapterRSAalphabyte);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(int pathToResource) {
        Toast.makeText(getContext(), getContext().getResources().getString(pathToResource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle() {

    }

    @Override
    public int getTitle() {
        return R.string.rsa;
    }

    @Override
    public void onDestroy() {
        presenter.disposeDisposable();
        presenter = null;
        adapterRSAalphabyte = null;
        super.onDestroy();
    }
}