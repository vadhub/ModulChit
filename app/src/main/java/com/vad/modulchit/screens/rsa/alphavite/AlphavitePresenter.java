package com.vad.modulchit.screens.rsa.alphavite;

import android.os.Handler;
import android.os.Looper;

import androidx.fragment.app.Fragment;
import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.Navigator;
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
    private Navigator navigator;
    private RSAshiphr shiphr = new RSAshiphr();
    private RSAmod rsaMod = new RSAmod();
    private List<Integer> alphaviteCodes;

    public AlphavitePresenter(AlphaviteView alphaviteView, Navigator navigator) {
        this.alphaviteView = alphaviteView;
        this.navigator = navigator;
    }

    public void alphaviteLoad() {
        new Thread(() -> {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                alphaviteCodes = shiphr.getNumberShiphr();
                alphaviteView.alphaviteLoad(alphaviteCodes);
            });
        }).start();

    }

    public void alphaviteLoad(int numberForFirstLetter) {
        new Thread(() -> {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> {
                alphaviteCodes = shiphr.getNumberShiphr(numberForFirstLetter);
                alphaviteView.alphaviteLoad(alphaviteCodes);
            });
        }).start();
    }

    public void alphaviteChosen(int i, int numberForFirstLetter) {
        switch (i) {
            case 0:
                alphaviteLoad();
                break;
            case 1:
                alphaviteLoad(numberForFirstLetter);
                break;
        }
    }

    public void fragmentChoosen(boolean isEncrypt, String qStr, String pStr) {

        Fragment fragment = null;
        int n = 0;
        int eller = 0;
        List<Integer> exponents = null;

        int p = 0;
        int q = 0;

        if (!qStr.equals("") && !pStr.equals("")) {
            p = Integer.parseInt(pStr);
            q = Integer.parseInt(qStr);
            n = rsaMod.getN(p,q);
            eller = rsaMod.functionEller(p,q);
            exponents = rsaMod.exponenta(eller);

            if (rsaMod.isSimpleNumber(p) && rsaMod.isSimpleNumber(q)) {
                if (isEncrypt) {
                    fragment = FragmentRSAcrypt.newInstance(alphaviteCodes, n, exponents);
                } else {
                    int d = rsaMod.getDPrivate(eller, exponents.get(0));
                    fragment = FragmentRSAdecrypt.newInstance(alphaviteCodes, n, d, eller, exponents.get(0), p, q);
                }
            } else {
                alphaviteView.showError(R.string.warning_prime);
            }
        } else {
            alphaviteView.showError(R.string.warning_enter_p_q);
        }

        navigator.startFragment(fragment);
        //alphaviteView.fragmentLoad(fragment);

    }
}
