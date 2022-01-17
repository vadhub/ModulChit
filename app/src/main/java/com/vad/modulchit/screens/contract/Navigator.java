package com.vad.modulchit.screens.contract;

import androidx.fragment.app.Fragment;

public interface Navigator {
    void goBack();
    void startFragment(Fragment fragment);
}
