package com.vad.modulchit.screens.sort.bubble;

public interface RenderState {
    StatusAnimation getStateRun();
    void setStateRun(StatusAnimation status);

    void setStatePause(StatusAnimation status);
}
