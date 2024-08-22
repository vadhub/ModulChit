package com.vad.modulchit.screens.menu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.AboutFragment;
import com.vad.modulchit.screens.adapters.AdapterMenu;
import com.vad.modulchit.screens.binarysearch.FragmentBinarySearch;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.fe.FragmentFE;
import com.vad.modulchit.screens.gcde.FragmentGCDe;
import com.vad.modulchit.screens.mg.FragmentMG;
import com.vad.modulchit.screens.rsa.alphabet.FragmentAddAlphabet;
import com.vad.modulchit.screens.sort.bubble.FragmentBubbleSort;
import com.vad.modulchit.screens.sort.insert.FragmentInsertSort;
import com.vad.modulchit.screens.sort.quick.FragmentQuickSort;
import com.vad.modulchit.screens.sort.selection.FragmentSelectionSort;
import com.vad.modulchit.screens.sort.shell.FragmentShellSort;

import java.util.Arrays;
import java.util.List;

public class FragmentMenu extends Fragment implements HasCustomTitle, HasCustomAction {

    private AdapterMenu adapter;
    private Navigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.menuRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdapterMenu();

        List<String> menuItem = Arrays.asList(getResources().getStringArray(R.array.titleScreen));

        adapter.setStrings(menuItem);
        mRecyclerView.setAdapter(adapter);

        adapter.setClickListener((view, id) -> {
            Fragment fragment = getFragments(id);
            navigator.startFragment(fragment);
        });
    }

    public Fragment getFragments(int id) {

        switch (id) {
            case 0:
                return new FragmentGCDe();
            case 1:
                return new FragmentMG();
            case 2:
                return new FragmentFE();
            case 3:
                return new FragmentAddAlphabet();
            case 4:
                return new FragmentBinarySearch();
            case 5:
                return new FragmentBubbleSort();
            case 6:
                return new FragmentShellSort();
            case 7:
                return new FragmentInsertSort();
            case 8:
                return new FragmentQuickSort();
            case 9:
                return new FragmentSelectionSort();
        }

        return new FragmentFE();
    }

    @Override
    public int getTitle() {
        return R.string.app_name;
    }

    @Override
    public void onDestroy() {
        adapter = null;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }

    @Override
    public CustomActionFragment setCustomAction(Navigator navigator) {
        return new CustomActionFragment(R.drawable.ic_baseline_info_24,() -> {
            navigator.startFragment(new AboutFragment());
        });
    }
}