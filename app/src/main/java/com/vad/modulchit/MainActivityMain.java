package com.vad.modulchit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vad.modulchit.frahments.FragmentFE;
import com.vad.modulchit.frahments.FragmentGCDe;
import com.vad.modulchit.frahments.FragmentMod;
import com.vad.modulchit.frahments.FragmentNOK;

public class MainActivityMain extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_main);

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
                    break;

                case R.id.nokItem:
                    fragment = new FragmentNOK();
                    break;
                case R.id.feItem:
                    fragment = new FragmentFE();
                    break;

                case R.id.modItem:
                    fragment = new FragmentMod();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, fragment).commit();

            return true;
        }
    };
}