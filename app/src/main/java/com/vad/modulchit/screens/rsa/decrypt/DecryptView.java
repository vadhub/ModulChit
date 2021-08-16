package com.vad.modulchit.screens.rsa.decrypt;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.pojos.TableNumberGCDe;

import java.util.List;

public interface DecryptView {

    void showError(int resource);
    void showCalculating(List<TableNumberFE> tableNumberFEList);
    void showCalculatingExtra(List<TableNumberGCDe> tableNumberGCDeList);
    void showCalculatingExtra(String decrypt);
}
