package com.vad.modulchit.screens.binarysearch;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vad.modulchit.R;
import com.vad.modulchit.pojos.BinarySearchModel;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.utils.search.BinarySearchImpl;

import java.util.ArrayList;
import java.util.List;

public class FragmentBinarySearch extends Fragment implements HasCustomTitle {

    private EditText editTextArray;
    private EditText editTextElement;
    private CustomViewBinarySearch customViewBinarySearch;
    private Button btn;
    private BinarySearchImpl binarySearch = new BinarySearchImpl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_binary_search, container, false);
        editTextArray = (EditText) v.findViewById(R.id.editTextEnterArray);
        editTextElement = (EditText) v.findViewById(R.id.editTextEnterElement);
        customViewBinarySearch = v.findViewById(R.id.binary_search);
        btn = v.findViewById(R.id.btnBinarySearch);

        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        btn.setOnClickListener(v1 -> {
            List<BinarySearchModel> binarySearchModels = binarySearch.search(arr, 3);
            customViewBinarySearch.searchElement(binarySearchModels);
        });
        return v;
    }

    public static int[] parseStringToArray(String str) {
        String[] spitString = str.split(", ");
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