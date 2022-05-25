package com.vad.modulchit.animation.common;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.screens.sort.ScreenSort;

public interface RenderState {
    void setScreenSort(ScreenSort buttonChanged);
    StatusAnimation getStateRun();
    void setStateRun(StepRecorder stepRecorder);
    void setStateStop();
    void setStatePause();
    void setStateRestart();
}
