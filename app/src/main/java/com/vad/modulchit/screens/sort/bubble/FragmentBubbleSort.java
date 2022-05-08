package com.vad.modulchit.screens.sort.bubble;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.utils.Parser;
import com.vad.modulchit.utils.sort.BubbleSort;

public class FragmentBubbleSort extends Fragment implements HasCustomTitle {

    private CustomViewBubbleSort customView;
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buble_sort, container, false);
        customView = (CustomViewBubbleSort) v.findViewById(R.id.bubbleSort);
        editText = (EditText) v.findViewById(R.id.editTextArrSort);
        customView.setZOrderOnTop(true);
        customView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        v.findViewById(R.id.btnSort).setOnClickListener(v1 -> {
            customView.getRender().setArr(Parser.parseComma(editText.getText().toString()));
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