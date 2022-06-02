package com.vad.modulchit.screens.mg;

import com.vad.modulchit.models.pojos.TableNumberNOK;
import com.vad.modulchit.screens.supportable.Supportable;

import java.util.List;

public interface ListMGView extends Supportable {
    void showData(List<TableNumberNOK> numberNOKS);
}
