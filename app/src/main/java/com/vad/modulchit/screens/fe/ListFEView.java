package com.vad.modulchit.screens.fe;

import com.vad.modulchit.pojos.TableNumberFE;
import com.vad.modulchit.screens.supportable.Supportable;

import java.util.List;

public interface ListFEView extends Supportable {
    void showData(List<TableNumberFE> tableNumberFEList);
}
