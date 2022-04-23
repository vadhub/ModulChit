package com.vad.modulchit.screens.binarysearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.HasCustomTitle;

public class FragmentBinarySearch extends Fragment implements HasCustomTitle {

    private EditText editTextArray;
    private CustomViewBinarySearch customViewBinarySearch;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_binary_search, container, false);
        editTextArray = (EditText) v.findViewById(R.id.editTextEnterArray);
        customViewBinarySearch = v.findViewById(R.id.binary_search);
        btn = v.findViewById(R.id.btnBinarySearch);
        btn.setOnClickListener(v1 -> customViewBinarySearch.setArr(parseStringToArray(editTextArray.getText().toString())));
        return v;
    }

    public static int[] parseStringToArray(String str) {
        String[] spitString = str.split(",");
        int[] tempArr = new int[spitString.length];

        for (int i = 0; i < spitString.length; i++) {
            tempArr[i] = Integer.parseInt(spitString[i]);
        }

        return tempArr;
    }

    @Override
    public int getTitle() {
        return R.string.binary_search;
    }
}