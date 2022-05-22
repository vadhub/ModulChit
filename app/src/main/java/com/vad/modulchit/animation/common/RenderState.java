package com.vad.modulchit.animation.common;

import com.vad.modulchit.animation.StepRecorder;
import com.vad.modulchit.animation.common.StatusAnimation;

public interface RenderState {
    StatusAnimation getStateRun();
    void setStateRun(StepRecorder stepRecorder);
    void setStateStop();
    void setStatePause();
    void setStateRestart();
}
