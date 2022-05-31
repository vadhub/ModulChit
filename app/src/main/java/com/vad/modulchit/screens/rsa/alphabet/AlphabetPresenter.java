package com.vad.modulchit.screens.rsa.alphabet;

import androidx.fragment.app.Fragment;

import com.vad.modulchit.R;
import com.vad.modulchit.models.AlgebraMod;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.rsa.crypt.FragmentRSAcrypt;
import com.vad.modulchit.screens.rsa.decrypt.FragmentRSAdecrypt;
import com.vad.modulchit.models.RSAmod;
import com.vad.modulchit.models.RSAshiphr;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AlphabetPresenter {

    private final AlphabetView alphabetView;
    private final Navigator navigator;
    private final RSAshiphr shiphr;
    private final RSAmod rsaMod;
    private final CompositeDisposable compositeDisposable;

    public AlphabetPresenter(AlphabetView alphabetView, Navigator navigator) {
        shiphr = new RSAshiphr();
        AlgebraMod algebraMod = new AlgebraMod();
        rsaMod = new RSAmod(algebraMod);
        compositeDisposable = new CompositeDisposable();
        this.alphabetView = alphabetView;
        this.navigator = navigator;
    }

    public void alphabetLoad() {
        Disposable disposable = Observable.empty()
                .subscribeOn(Schedulers.computation())
                .map(r -> shiphr.getNumberShiphr())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(alphabetView::setAlphabet);
        compositeDisposable.add(disposable);

    }

    public void alphabetLoad(int numberForFirstLetter) {
        Disposable disposable = Observable.empty()
                .subscribeOn(Schedulers.computation())
                .map(empty -> shiphr.getNumberShiphr(numberForFirstLetter))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(alphabetView::setAlphabet);
        compositeDisposable.add(disposable);
    }

    public void alphabetChosen(int i, int numberForFirstLetter) {
        switch (i) {
            case 0:
                alphabetLoad();
                break;
            case 1:
                alphabetLoad(numberForFirstLetter);
                break;
        }
    }

    public void fragmentChoose(boolean isEncrypt, String qStr, String pStr, List<Integer> alphabetCodes) {

        Fragment fragment = null;
        int n = 0;
        int eller = 0;
        List<Integer> exponents;

        int p = 0;
        int q = 0;

        if (!qStr.equals("") && !pStr.equals("")) {
            p = Integer.parseInt(pStr);
            q = Integer.parseInt(qStr);
            n = rsaMod.getN(p, q);
            eller = rsaMod.functionEller(p, q);
            exponents = rsaMod.exponenta(eller);

            if (rsaMod.isSimpleNumber(p) && rsaMod.isSimpleNumber(q)) {
                if (isEncrypt) {
                    fragment = FragmentRSAcrypt.newInstance(alphabetCodes, n, exponents);
                } else {
                    startFragmentWithDNumber(navigator, rsaMod, alphabetCodes, eller, exponents.get(0), n, p, q);
                }
            } else {
                alphabetView.showError(R.string.warning_prime);
            }
        } else {
            alphabetView.showError(R.string.warning_enter_p_q);
        }

        navigator.startFragment(fragment);
        //alphaviteView.fragmentLoad(fragment);

    }

    public void startFragmentWithDNumber(Navigator navigator, RSAmod mod, List<Integer> alphabetCodes, int eller, int exponent, int n, int p, int q) {
        Disposable disposable = Observable.empty()
                .subscribeOn(Schedulers.computation())
                .map(empty -> mod.getDPrivate(eller, exponent))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> navigator.startFragment(FragmentRSAdecrypt.newInstance(alphabetCodes, n, d, eller, exponent, p, q)));
        compositeDisposable.add(disposable);
    }

    public void disposeDisposable() {
        compositeDisposable.dispose();
    }
}
