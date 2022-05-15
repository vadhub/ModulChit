package com.vad.modulchit.screens.sort.shell;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;


public class FragmentShellSort extends FragmentBubbleSort {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shell_sort, container, false);
    }
}