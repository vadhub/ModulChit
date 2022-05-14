package com.vad.modulchit.animation;

import com.vad.modulchit.animation.StatusAnimation;

public interface RenderState {
    StatusAnimation getStateRun();
    void setStateRun();
    void setStatePause();
    void setStateRestart();
}
