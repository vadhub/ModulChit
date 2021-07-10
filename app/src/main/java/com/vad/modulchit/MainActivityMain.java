package com.vad.modulchit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vad.modulchit.frahments.FragmentFE;
import com.vad.modulchit.frahments.FragmentGCDe;
import com.vad.modulchit.frahments.FragmentMod;
import com.vad.modulchit.frahments.FragmentNOK;
import com.vad.modulchit.frahments.rsa.AddAlphaviteFragment;

import java.util.LinkedHashMap;

public class MainActivityMain extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigate);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentGCDe()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.gcdItem:
                    fragment = new FragmentGCDe();
                    setTitle(R.string.gcd_e);
                    break;

                case R.id.nokItem:
                    fragment = new FragmentNOK();
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
        }
    };
}