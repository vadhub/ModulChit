package com.vad.modulchit.screens.contract;

public class CustomActionFragment {
    private int iconRes;
    private Runnable onCustomAction;

    public CustomActionFragment(int iconRes ,Runnable onCustomAction) {
        this.iconRes = iconRes;
        this.onCustomAction = onCustomAction;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public Runnable getOnCustomAction() {
        return onCustomAction;
    }

    public void setOnCustomAction(Runnable onCustomAction) {
        this.onCustomAction = onCustomAction;
    }
}
