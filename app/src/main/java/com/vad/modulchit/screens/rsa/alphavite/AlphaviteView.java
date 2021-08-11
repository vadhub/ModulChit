package com.vad.modulchit.screens.rsa.alphavite;

import android.content.Context;

import androidx.fragment.app.Fragment;

import java.util.List;

public interface AlphaviteView {

    void alphaviteLoad(List<Integer> alphaviteCodes);
    void fragmentLoad(Fragment fragment);
    void showError(int pathToResource);
}
