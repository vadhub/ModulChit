package com.vad.modulchit.screens.rsa.alphabet;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private Navigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_alphabet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {

        setRetainInstance(true);

        presenter = new AlphabetPresenter(this, navigator);

        numberForFirstLetter = (EditText) v.findViewById(R.id.editTextViewNumberFirst);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerAlphabet);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapterRSAalphabyte = new AdapterRSAalphabyte();
        progressBar = (ProgressBar) v.findViewById(R.id.progressBarAlpha);

        numberP = (EditText) v.findViewById(R.id.editTextNumberP);
        numberQ = (EditText) v.findViewById(R.id.editTextNumberQ);

        RadioButton radioButtonEncrypt = (RadioButton) v.findViewById(R.id.radioButtonEncrypt);
        RadioButton radioButtonDecrypt = (RadioButton) v.findViewById(R.id.radioButtonDecrypt);

        v.findViewById(R.id.btnNetx).setOnClickListener(clickListener);

        numberForFirstLetter.addTextChangedListener(textWatcher);
        radioButtonDecrypt.setOnClickListener(radioButtonClick);
        radioButtonEncrypt.setOnClickListener(radioButtonClick);

        presenter.alphabetLoad();
    }

    private final View.OnClickListener radioButtonClick = view -> {
        RadioButton radioButton = (RadioButton) view;
        if (radioButton.getId() == R.id.radioButtonEncrypt) {
            isEncrypt = true;
        } else if(radioButton.getId() == R.id.radioButtonDecrypt) {
            isEncrypt = false;
        }
    };

    //btn next
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            navigator.hideKeyBoard();
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

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}