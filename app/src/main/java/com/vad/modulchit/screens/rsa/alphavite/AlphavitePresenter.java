package com.vad.modulchit.screens.rsa.alphavite;

import com.vad.modulchit.utils.RSAshiphr;

import java.util.List;

public class AlphavitePresenter {

    private AlphaviteView alphaviteView;
    private RSAshiphr shiphr = new RSAshiphr();

    public AlphavitePresenter(AlphaviteView alphaviteView) {
        this.alphaviteView = alphaviteView;
    }

    public void alphaviteLoad(){
        List<Integer> alphaviteCodes = shiphr.getNumberShiphr();
        alphaviteView.alphaviteLoad(alphaviteCodes);
    }

    public void alphaviteChosen(int i, int numberForFirstLetter){

        List<Integer> alphaviteCodes;

        switch (i){
            case 0:
                alphaviteCodes = shiphr.getNumberShiphr();
                alphaviteView.alphaviteLoad(alphaviteCodes);
                break;
            case 1:
                alphaviteCodes = shiphr.getNumberShiphr(numberForFirstLetter);
                alphaviteView.alphaviteLoad(alphaviteCodes);
                break;
        }
    }

}
