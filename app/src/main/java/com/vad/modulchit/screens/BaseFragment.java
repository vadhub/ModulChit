package com.vad.modulchit.screens;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.Navigator;

public class BaseFragment extends Fragment {
    protected Navigator navigator;
    protected LayoutAnimationController layoutAnimationController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_slide_in_random);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = ((Navigator) context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("##base", "detach");
        navigator = null;
    }
}
