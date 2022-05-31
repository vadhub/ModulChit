package com.vad.modulchit.screens.rsa.alphabet;

import com.vad.modulchit.screens.supportable.Supportable;

import java.util.List;

public interface AlphabetView extends Supportable {
    void setAlphabet(List<Integer> alphabetCodes);
}
