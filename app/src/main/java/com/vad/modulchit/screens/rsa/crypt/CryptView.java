package com.vad.modulchit.screens.rsa.crypt;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.screens.supportable.Supportable;

import java.util.List;

public interface CryptView extends Supportable {
    void showCalculating(List<TableNumberFE> tableNumberFEList);
    void showCalculatingExtra(String encrypt);
}
