package com.vad.modulchit.screens.gcde;

import com.vad.modulchit.models.pojos.TableNumberGCDe;
import com.vad.modulchit.screens.supportable.Supportable;

import java.util.List;

public interface ListGCDEView extends Supportable {
    void showData(List<TableNumberGCDe> tableNumberGCDeList);
}
