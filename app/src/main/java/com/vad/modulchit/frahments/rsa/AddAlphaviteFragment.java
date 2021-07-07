package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vad.modulchit.R;

public class AddAlphaviteFragment extends Fragment {

    private Button btnNext;
    private EditText numberForFirstLetter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_alphavite, container, false);
        return v;
    }
}