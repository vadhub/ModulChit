package com.vad.modulchit.screens.rsa.alphavite;

import androidx.fragment.app.Fragment;

import com.vad.modulchit.screens.Supportable;

import java.util.List;

public interface AlphaviteView extends Supportable {

    void alphaviteLoad(List<Integer> alphaviteCodes);
    void fragmentLoad(Fragment fragment);
}
