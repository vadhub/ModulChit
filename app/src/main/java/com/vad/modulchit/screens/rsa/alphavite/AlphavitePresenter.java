package com.vad.modulchit.screens.rsa.alphavite;

import androidx.fragment.app.Fragment;
import com.vad.modulchit.R;
import com.vad.modulchit.screens.rsa.crypt.FragmentRSAcrypt;
import com.vad.modulchit.screens.rsa.decrypt.FragmentRSAdecrypt;
import com.vad.modulchit.utils.RSAmod;
import com.vad.modulchit.utils.RSAshiphr;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AlphavitePresenter {

    private AlphaviteView alphaviteView;
    private RSAshiphr shiphr = new RSAshiphr();
    private RSAmod rsaMod = new RSAmod();
    private List<Integer> alphaviteCodes;

    public AlphavitePresenter(AlphaviteView alphaviteView) {
        this.alphaviteView = alphaviteView;
    }

    public void alphaviteLoad(){
        List<Integer> alphaviteCodes = shiphr.getNumberShiphr();
        alphaviteView.alphaviteLoad(alphaviteCodes);
    }

    public void alphaviteChosen(int i, int numberForFirstLetter){

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

    public void fragmentChoosen(boolean isEncrypt, String qStr, String pStr){


        Observable.just(isEncrypt)
                .subscribeOn(Schedulers.computation())
                .groupBy(aBoolean -> true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(booleanGroupedObservable -> {
                    Fragment fragment = null;
                    int n;
                    int eller;
                    List<Integer> exponents;

                    if(booleanGroupedObservable.getKey()){
                        if(!qStr.equals("")&&!pStr.equals("")){
                            int p = Integer.parseInt(pStr);
                            int q = Integer.parseInt(qStr);

                            n = rsaMod.getN(p,q);
                            eller = rsaMod.functionEller(p,q);
                            exponents = rsaMod.exponenta(eller);

                            if(rsaMod.isSimpleNumber(p)&&rsaMod.isSimpleNumber(q)){
                                fragment = new FragmentRSAcrypt(alphaviteCodes, n, exponents);
                            }else{
                                alphaviteView.showError(R.string.warning_prime);
                            }
                        }else{
                            fragment = new FragmentRSAcrypt(alphaviteCodes);
                        }
                    }else {
                        if(!qStr.equals("")&&!pStr.equals("")){
                            int p = Integer.parseInt(pStr);
                            int q = Integer.parseInt(qStr);

                            n =rsaMod.getN(p,q);
                            eller = rsaMod.functionEller(p,q);
                            exponents = rsaMod.exponenta(eller);
                            int d = rsaMod.getDPrivate(eller, exponents.get(0));

                            if(rsaMod.isSimpleNumber(p)&&rsaMod.isSimpleNumber(q)){
                                fragment = new FragmentRSAdecrypt(alphaviteCodes, n, d, eller, exponents.get(0), p, q);
                            }else{
                                alphaviteView.showError(R.string.warning_prime);
                            }
                        }else{
                            alphaviteView.showError(R.string.warning_enter_p_q);
                        }

                    }

                    alphaviteView.fragmentLoad(fragment);
                });
    }
}
