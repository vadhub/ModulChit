package com.vad.modulchit.animation;

import com.vad.modulchit.animation.StatusAnimation;

public interface RenderState {
    StatusAnimation getStateRun();
    void setStateRun(StatusAnimation status);

    void setStatePause(StatusAnimation status);
}
