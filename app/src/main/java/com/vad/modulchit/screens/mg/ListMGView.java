package com.vad.modulchit.screens.mg;

import com.vad.modulchit.pojos.TableNumberNOK;
import com.vad.modulchit.screens.Supportable;

import java.util.List;

public interface ListMGView extends Supportable {
    void showData(List<TableNumberNOK> numberNOKS);
}
