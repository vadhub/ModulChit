package com.vad.modulchit.screens.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vad.modulchit.R;
import com.vad.modulchit.adapters.AdapterMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AdapterMenu adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.menuRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterMenu();

        List<String> menuItem = Arrays.asList(getResources().getStringArray(R.array.titleScreen));

        adapter.setNamesMenu(menuItem);
        mRecyclerView.setAdapter(adapter);

        return v;
    }
}