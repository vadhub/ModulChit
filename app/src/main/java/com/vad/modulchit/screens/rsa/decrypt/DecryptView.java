package com.vad.modulchit.screens.rsa.decrypt;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.pojos.TableNumberGCDe;
import com.vad.modulchit.screens.Supportable;

import java.util.List;

public interface DecryptView extends Supportable {

    void showCalculating(List<TableNumberFE> tableNumberFEList);
    void showCalculatingExtra(List<TableNumberGCDe> tableNumberGCDeList);
    void showCalculatingExtra(String decrypt);
}
