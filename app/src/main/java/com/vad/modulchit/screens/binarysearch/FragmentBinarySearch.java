package com.vad.modulchit.screens.binarysearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.HasCustomTitle;

public class FragmentBinarySearch extends Fragment implements HasCustomTitle {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        try {
            View v = inflater.inflate(R.layout.fragment_binary_search, container, false);
//            CustomViewBinarySearch customViewBinarySearch = (CustomViewBinarySearch) v.findViewById(R.id.binary_search);
            return v;
        } catch (Exception e) {
//            Log.i("custom view", R.id.binary_search+"");
            Log.e("error inflate", e.getMessage());
            throw e;
        }
    }

    @Override
    public int getTitle() {
        return R.string.binary_search;
    }
}