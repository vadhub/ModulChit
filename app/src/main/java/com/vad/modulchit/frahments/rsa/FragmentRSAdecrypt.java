package com.vad.modulchit.frahments.rsa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vad.modulchit.R;

public class FragmentRSAdecrypt extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rsa_decrypt, container, false);
        return v;
    }
}