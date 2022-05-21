package com.vad.modulchit.animation.common;

import com.vad.modulchit.animation.common.StatusAnimation;

public interface RenderState {
    StatusAnimation getStateRun();
    void setStateRun();
    void setStateStop();
    void setStatePause();
    void setStateRestart();
}
