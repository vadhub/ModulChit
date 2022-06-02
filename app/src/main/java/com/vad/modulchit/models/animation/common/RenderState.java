package com.vad.modulchit.models.animation.common;

import com.vad.modulchit.models.animation.StepRecorder;
import com.vad.modulchit.screens.sort.ScreenSort;

public interface RenderState {
    void setScreenSort(ScreenSort buttonChanged);
    StatusAnimation getStateRun();
    void setStateStart(StepRecorder stepRecorder);
    void setStateStop();
    void setStatePause();
    void setStateRestart();
}
