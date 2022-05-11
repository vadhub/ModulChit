package com.vad.modulchit.screens.sort.bubble;

import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.utils.Parser;

public class FragmentBubbleSort extends Fragment implements HasCustomTitle {

    private CustomViewBubbleSort customView;
    private EditText editText;
    private Button btn;
    private boolean isRun = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_buble_sort, container, false);
        customView = (CustomViewBubbleSort) v.findViewById(R.id.bubbleSort);
        editText = (EditText) v.findViewById(R.id.editTextArrSort);
        btn = (Button) v.findViewById(R.id.btnSort);

        customView.setZOrderOnTop(true);
        customView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        Drawable imgRestart = getResources().getDrawable(R.drawable.ic_baseline_refresh_24);
        Drawable imgPlay = getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24);

        btn.setOnClickListener(v1 -> {

            if (editText.getText().toString().equals("")) {
                Toast.makeText(getActivity(), R.string.warning_enter_text, Toast.LENGTH_SHORT).show();
                return;
            }

            if (isRun) {
                customView.getRender().setArr(Parser.parseComma(editText.getText().toString()));
                customView.getRender().setStateRun(true);
                btn.setCompoundDrawablesWithIntrinsicBounds(imgRestart, null, null, null);
                isRun = false;
            } else {
                customView.getRender().setStateRun(false);
                btn.setCompoundDrawablesWithIntrinsicBounds(imgPlay, null, null, null);
                isRun = true;
            }
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