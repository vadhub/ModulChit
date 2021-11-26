package com.vad.modulchit.screens.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vad.modulchit.R;
import com.vad.modulchit.screens.fe.FragmentFE;
import com.vad.modulchit.screens.gcde.FragmentGCDe;
import com.vad.modulchit.screens.mg.FragmentMG;
import com.vad.modulchit.screens.rsa.alphavite.AddAlphaviteFragment;

public class MainActivityMain extends AppCompatActivity{

    private BottomNavigationView bottomNavigationView;
    private AdView mAdView;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_main);


        MobileAds.initialize(this, initializationStatus -> {
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigate);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        if(savedInstanceState!=null){
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "mFragment");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, fragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentGCDe()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {

        switch (item.getItemId()){
            case R.id.gcdItem:
                fragment = new FragmentGCDe();
                setTitle(R.string.gcd_e);
                break;

            case R.id.nokItem:
                fragment = new FragmentMG();
                setTitle(R.string.multiplicative_group);
                break;
            case R.id.feItem:
                fragment = new FragmentFE();
                setTitle(R.string.fast_exponentiation);
                break;

            case R.id.modItem:
                fragment = new AddAlphaviteFragment();
                setTitle(R.string.rsa);
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, fragment).commit();

        return true;
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment != null)
        getSupportFragmentManager().putFragment(outState, "mFragment", fragment);
    }
}