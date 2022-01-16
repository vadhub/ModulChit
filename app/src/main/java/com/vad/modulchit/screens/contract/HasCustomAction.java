package com.vad.modulchit.screens.contract;

public interface HasCustomAction {
    void setCustomAction();
}

class CustomAction {
    int iconRes;
    Runnable onCustomAction;
}
