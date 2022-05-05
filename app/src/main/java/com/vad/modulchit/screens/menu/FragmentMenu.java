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
import com.vad.modulchit.screens.binarysearch.FragmentBinarySearch;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.fe.FragmentFE;
import com.vad.modulchit.screens.gcde.FragmentGCDe;
import com.vad.modulchit.screens.mg.FragmentMG;
import com.vad.modulchit.screens.rsa.alphabet.FragmentAddAlphabet;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentMenu extends Fragment implements HasCustomTitle {

    private RecyclerView mRecyclerView;
    private AdapterMenu adapter;
    private Navigator navigator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.menuRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterMenu();
        navigator = (Navigator) requireActivity();

        List<String> menuItem = Arrays.asList(getResources().getStringArray(R.array.titleScreen));

        adapter.setNamesMenu(menuItem);
        mRecyclerView.setAdapter(adapter);

        adapter.setClickListener((view, id) ->{
            Fragment fragment = getFragments().get(id);
            navigator.startFragment(fragment);
        });

        return v;
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentGCDe());
        fragments.add(new FragmentMG());
        fragments.add(new FragmentFE());
        fragments.add(new FragmentAddAlphabet());
        fragments.add(new FragmentBinarySearch());
        fragments.add(new FragmentBubbleSort());

        return fragments;
    }

    @Override
    public int getTitle() {
        return R.string.app_name;
    }
}