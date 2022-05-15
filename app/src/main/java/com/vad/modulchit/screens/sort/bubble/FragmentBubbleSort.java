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
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.animation.ButtonIconChange;
import com.vad.modulchit.animation.RenderState;
import com.vad.modulchit.animation.StatusAnimation;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.sort.CustomViewSorted;
import com.vad.modulchit.utils.Parser;

public class FragmentBubbleSort extends Fragment implements HasCustomTitle, ButtonIconChange {

    private CustomViewSorted customView;
    private EditText editText;
    private Button btn;
    private boolean isRun = true;
    private Drawable imgPlay;
    private Drawable imgPause;
    private  RenderState renderState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buble_sort, container, false);
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

        setRender(customView.getRender());

        btn.setOnClickListener(v1 -> {

            if (editText.getText().toString().equals("")) {
                Toast.makeText(getActivity(), R.string.warning_enter_text, Toast.LENGTH_SHORT).show();
                return;
            }

            if (isRun) {
                if (renderState.getStateRun() == StatusAnimation.PAUSE) {
                    renderState.setStateRestart();
                    System.out.println("restart");
                }

                if (renderState.getStateRun() == StatusAnimation.STOP) {
                    customView.getRender().setArr(Parser.parseComma(editText.getText().toString()));
                }

                renderState.setStateRun();

                btn.setCompoundDrawablesWithIntrinsicBounds(imgPause, null, null, null);
                isRun = false;
            } else {
                renderState.setStatePause();
                btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
                isRun = true;
            }
        });
    }

    protected void setRender(RenderState render) {
        renderState = render;
        customView.getRender().setButtonIcon(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        customView = null;
    }

    @Override
    public int getTitle() {
        return R.string.bubble_sort;
    }

    @Override
    public void setButtonStatus() {
        requireActivity().runOnUiThread(() ->{
            btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
        });
        isRun = true;
    }
}