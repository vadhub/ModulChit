package com.vad.modulchit.screens.rsa.crypt;

import com.vad.modulchit.pojos.TableNumberFE;

import java.util.List;

public interface CryptView {
    void showError(int resource);
    void showCalculating(List<TableNumberFE> tableNumberFEList);
    void showCalculatingExtra(String encrypt);
}
