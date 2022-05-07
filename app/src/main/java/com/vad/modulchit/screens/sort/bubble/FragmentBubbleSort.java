package com.vad.modulchit.screens.sort.bubble;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.utils.sort.BubbleSort;

public class FragmentBubbleSort extends Fragment implements HasCustomTitle {

    private CustomViewBubbleSort customView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buble_sort, container, false);
        customView = (CustomViewBubbleSort) v.findViewById(R.id.bubbleSort);
        v.findViewById(R.id.btnSort).setOnClickListener(v1 -> {
            int[] arr = {8, 5, 6, 7 ,1, 4};
            customView.getRender().setArr(arr);
            customView.getRender().start();
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        customView = null;;
    }

    @Override
    public int getTitle() {
        return R.string.bubble_sort;
    }
}