package com.vad.modulchit.screens.binarysearch;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.BinarySearchModel;
import com.vad.modulchit.models.search.BinarySearch;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.models.Parser;
import com.vad.modulchit.models.search.BinarySearchImpl;
import com.vad.modulchit.screens.contract.Navigator;

import java.util.List;

public class FragmentBinarySearch extends Fragment implements HasCustomTitle {

    private EditText editTextArray;
    private EditText editTextElement;
    private final BinarySearchImpl binarySearch = new BinarySearchImpl();
    private Navigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_binary_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        editTextArray = (EditText) v.findViewById(R.id.editTextEnterArray);
        editTextElement = (EditText) v.findViewById(R.id.editTextEnterElement);

        LinearLayout linearRoot = (LinearLayout) v.findViewById(R.id.rootLinear);
        BinarySearchView binarySearchView = new BinarySearchView(getContext(), linearRoot);

        v.findViewById(R.id.btnBinarySearch).setOnClickListener(v1 -> {
            navigator.hideKeyBoard();
            if (editTextArray.getText().toString().equals("") || editTextElement.getText().toString().equals("")) {
                Toast.makeText(getActivity(), R.string.warning_enter_text, Toast.LENGTH_SHORT).show();
                return;
            }

            List<BinarySearchModel> binarySearchModels = binarySearch.search(Parser.parseToIntArray(editTextArray.getText().toString()), Integer.parseInt(editTextElement.getText().toString()));
            binarySearchView.search(binarySearchModels);
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }

    @Override
    public int getTitle() {
        return R.string.binary_search;
    }
}