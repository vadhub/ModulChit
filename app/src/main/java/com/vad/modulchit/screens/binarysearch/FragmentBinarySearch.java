package com.vad.modulchit.screens.binarysearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.BinarySearchModel;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.models.Parser;
import com.vad.modulchit.models.search.BinarySearchImpl;
import com.vad.modulchit.screens.contract.Navigator;

import java.util.List;

public class FragmentBinarySearch extends Fragment implements HasCustomTitle {

    private EditText editTextArray;
    private EditText editTextElement;
    private CustomViewBinarySearch customViewBinarySearch;
    private Button btn;
    private final BinarySearchImpl binarySearch = new BinarySearchImpl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_binary_search, container, false);
        editTextArray = (EditText) v.findViewById(R.id.editTextEnterArray);
        editTextElement = (EditText) v.findViewById(R.id.editTextEnterElement);
        customViewBinarySearch = v.findViewById(R.id.binary_search);
        btn = v.findViewById(R.id.btnBinarySearch);

        btn.setOnClickListener(v1 -> {
            ((Navigator) requireActivity()).hideKeyBoard();
            if (editTextArray.getText().toString().equals("") || editTextElement.getText().toString().equals("")) {
                Toast.makeText(getActivity(), R.string.warning_enter_text, Toast.LENGTH_SHORT).show();
                return;
            }

            List<BinarySearchModel> binarySearchModels = binarySearch.search(Parser.parseToIntArray(editTextArray.getText().toString()), Integer.parseInt(editTextElement.getText().toString()));
            customViewBinarySearch.searchElement(binarySearchModels);
        });
        return v;
    }

    @Override
    public int getTitle() {
        return R.string.binary_search;
    }
}