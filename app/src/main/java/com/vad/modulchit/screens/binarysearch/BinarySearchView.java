package com.vad.modulchit.screens.binarysearch;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vad.modulchit.R;
import com.vad.modulchit.models.pojos.BinarySearchModel;

import java.util.Arrays;
import java.util.List;

public class BinarySearchView {

    private final Context context;
    private final LinearLayout linearRoot;

    public BinarySearchView(Context context, LinearLayout linearRoot) {
        this.context = context;
        this.linearRoot = linearRoot;
    }

    public void search(List<BinarySearchModel> searchModels) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;

        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout linearLayout;

        int[] arr = searchModels.get(0).getArr();

        for (BinarySearchModel b : searchModels) {
            Log.d("##bb", b.toString());
        }

        for (int k = 0; k < searchModels.size(); k++) {
            linearLayout = new LinearLayout(context);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView arrow = new ImageView(context);
            arrow.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_baseline_arrow_right_alt_24));
            arrow.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));

            for (int i = searchModels.get(k).getLow(); i < searchModels.get(k).getHigh(); i++) {
                View view = inflater.inflate(R.layout.item_element, linearRoot, false);
                TextView index = view.findViewById(R.id.index);
                TextView elementText = view.findViewById(R.id.element);

                index.setText(i + "");
                elementText.setText(arr[i] + "");

                linearLayout.addView(view);
            }
            linearRoot.addView(linearLayout);

            if (k != searchModels.size()-1) linearRoot.addView(arrow);
        }
    }
}
