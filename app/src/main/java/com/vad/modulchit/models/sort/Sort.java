package com.vad.modulchit.models.sort;

import com.vad.modulchit.models.animation.StepRecorder;

public interface Sort {
    void setStepRecorder(StepRecorder stepRecorder);
    StepRecorder sorting(int[] arr);
}
