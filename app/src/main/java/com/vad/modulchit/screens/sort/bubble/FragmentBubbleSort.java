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
    private BubbleSort bubbleSort;
    private Render render;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buble_sort, container, false);
        customView = (CustomViewBubbleSort) v.findViewById(R.id.bubbleSort);
        bubbleSort = new BubbleSort();
        int[] arr = {8, 5, 6, 7 ,1, 4};
        customView.setArr(arr);

        render = new Render(customView, bubbleSort);
        render.start();
        return v;
    }

    @Override
    public int getTitle() {
        return R.string.bubble_sort;
    }
}