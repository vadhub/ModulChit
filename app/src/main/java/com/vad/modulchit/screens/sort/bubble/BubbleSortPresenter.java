package com.vad.modulchit.screens.sort.bubble;

public class BubbleSortPresenter implements StatusButton {

    private BubbleSortView view;

    public BubbleSortView getView() {
        return view;
    }

    @Override
    public void setView(BubbleSortView view) {
        this.view = view;
    }

    @Override
    public void setStatus() {
        view.setButtonStatus();
    }
}
