package com.vad.modulchit.screens.sort.bubble;

import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vad.modulchit.R;
import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.animation.common.ButtonIconChange;
import com.vad.modulchit.animation.common.RenderSort;
import com.vad.modulchit.animation.common.RenderState;
import com.vad.modulchit.models.sort.SortArray;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.sort.CustomViewSorted;
import com.vad.modulchit.models.sort.bubleimpl.BubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentBubbleSort extends Fragment implements HasCustomTitle, ButtonIconChange {

    protected CustomViewSorted customView;
    private EditText editText;
    private Button btn;
    private boolean isRun = true;
    private Drawable imgPlay;
    private Drawable imgPause;
    private RenderState renderState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        customView = (CustomViewSorted) v.findViewById(R.id.bubbleSort);
        editText = (EditText) v.findViewById(R.id.editTextArrSort);
        btn = (Button) v.findViewById(R.id.btnSort);

        customView.setZOrderOnTop(true);
        customView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        imgPause = getResources().getDrawable(R.drawable.ic_baseline_pause_24);
        imgPlay = getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24);

        List<int[]> steps = new ArrayList<>();
        StepRecorder stepRecorder = new StepRecorder(steps);
        SortArray renderBubbleSort = new BubbleSort(stepRecorder);
        int[] arr = {10, 9, 8, 7, 6, 5, 3, 2, 1};
        StepRecorder s = renderBubbleSort.sorting(arr);
        s.getSteps().forEach(e -> System.out.println(Arrays.toString(e)+"0------"));

        //renderState = getRender();

//        btn.setOnClickListener(v1 -> {

//            if (editText.getText().toString().equals("")) {
//                Toast.makeText(getActivity(), R.string.warning_enter_text, Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (isRun) {
////                if (renderState.getStateRun() == StatusAnimation.PAUSE) {
////                    renderState.setStateRestart();
////                }
////
////                if (renderState.getStateRun() == StatusAnimation.STOP) {
////                    //customView.getRender().setSteps(renderBubbleSort.sort(Parser.parseNumber(editText.getText().toString())));
////                }
////
////                renderState.setStateRun();
//
//                btn.setCompoundDrawablesWithIntrinsicBounds(imgPause, null, null, null);
//                isRun = false;
//            } else {
////                renderState.setStatePause();
//                btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
//                isRun = true;
//            }
//        });

    }

    protected RenderState getRender() {
        RenderSort renderSort = new RenderSort(customView.getHolder());
        customView.setRenderSort(renderSort);
        customView.getRender().start();
        customView.getRender().setButtonIcon(this);

        return customView.getRender();
    }

    @Override
    public void onPause() {
        super.onPause();
        renderState.setStateStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        renderState = null;
        customView = null;
    }

    @Override
    public int getTitle() {
        return R.string.bubble_sort;
    }

    @Override
    public void setButtonStatus() {
        if (isAdded()) {
            requireActivity().runOnUiThread(() -> {
                btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
            });
        }
        isRun = true;
    }

}