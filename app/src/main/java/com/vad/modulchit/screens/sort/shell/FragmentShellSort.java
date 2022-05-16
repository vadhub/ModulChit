package com.vad.modulchit.screens.sort.shell;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vad.modulchit.R;
import com.vad.modulchit.animation.RenderState;
import com.vad.modulchit.animation.SortFactory;
import com.vad.modulchit.animation.SortType;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;


public class FragmentShellSort extends FragmentBubbleSort {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buble_sort, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
    }

    @Override
    protected RenderState getRender() {
        SortFactory sortFactory = new SortFactory();
        customView.setRenderSort(sortFactory.createSort(SortType.SHELL_SORT, customView.getHolder()));
        customView.getRender().start();
        customView.getRender().setButtonIcon(this);
        return customView.getRender();
    }

    @Override
    public int getTitle() {
        return R.string.shell_sort;
    }
}