package com.vad.modulchit.screens.sort.bubble;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.models.animation.common.RenderState;
import com.vad.modulchit.screens.BaseFragment;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.sort.ScreenSort;
import com.vad.modulchit.models.animation.common.StatusAnimation;
import com.vad.modulchit.models.Parser;
import com.vad.modulchit.models.sort.Sort;
import com.vad.modulchit.models.sort.SortFactory;
import com.vad.modulchit.models.sort.SortType;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.sort.CustomViewSorted;

public class FragmentBubbleSort extends Fragment implements HasCustomTitle, ScreenSort {

    protected CustomViewSorted customView;
    private EditText editText;
    private Button btn;
    private boolean isRun = true;
    private Drawable imgPlay;
    private Drawable imgPause;
    private RenderState render;
    private Sort sort;
    private TextView log;
    private StringBuilder logs = new StringBuilder();
    private Navigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        customView = (CustomViewSorted) v.findViewById(R.id.customViewSort);
        editText = (EditText) v.findViewById(R.id.editTextArrSort);
        btn = (Button) v.findViewById(R.id.btnSort);
        log = (TextView) v.findViewById(R.id.log);

        customView.setVisibility(View.INVISIBLE);

        imgPause = getResources().getDrawable(R.drawable.ic_baseline_pause_24);
        imgPlay = getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24);

        sort = getSort();

        render = customView.getRender();
        render.setScreenSort(this);

        editText.setText("9 8 7 6 5 4 3 2 1");
        int[] ar1 = Parser.parseToIntArray(editText.getText().toString());
        sort.setStepRecorder(new StepRecorder());
        render.setStateStart(sort.sorting(ar1));

        btn.setOnClickListener(v1 -> {

            navigator.hideKeyBoard();

            if (editText.getText().toString().equals("")) {
                Toast.makeText(getActivity(), R.string.warning_enter_text, Toast.LENGTH_SHORT).show();
                return;
            }
            if (isRun) {
                customView.setVisibility(View.VISIBLE);
                if (render.getStateRun() == StatusAnimation.PAUSE) {
                    render.setStateRestart();
                }
                if (render.getStateRun() == StatusAnimation.STOP) {
                    if (logs!=null) logs = null;
                    logs = new StringBuilder();
                    int[] arr = Parser.parseToIntArray(editText.getText().toString());
                    sort.setStepRecorder(new StepRecorder());
                    render.setStateStart(sort.sorting(arr));
                }
                btn.setCompoundDrawablesWithIntrinsicBounds(imgPause, null, null, null);
                isRun = false;
            } else {
                render.setStatePause();
                btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
                isRun = true;
            }
        });

    }

    protected Sort getSort() {
        return new SortFactory().createSort(SortType.BUBBLE_SORT);
    }

    @Override
    public int getTitle() {
        return R.string.bubble_sort;
    }

    @Override
    public void setButtonState() {
        if (isAdded()) {
            requireActivity().runOnUiThread(() -> {
                btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
            });
        }
        isRun = true;
    }

    @Override
    public void write(String text) {
        logs.append(text);
        if (isAdded()) {
            requireActivity().runOnUiThread(() -> {
                log.setText(logs.toString());
            });
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        render.setStateStop();
    }

    @Override
    public void onDestroy() {
        render.setStateStop();
        render = null;
        customView = null;
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}