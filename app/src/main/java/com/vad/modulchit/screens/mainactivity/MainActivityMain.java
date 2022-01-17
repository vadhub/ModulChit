package com.vad.modulchit.screens.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.fe.FragmentFE;
import com.vad.modulchit.screens.gcde.FragmentGCDe;
import com.vad.modulchit.screens.mg.FragmentMG;
import com.vad.modulchit.screens.rsa.alphavite.AddAlphaviteFragment;

public class MainActivityMain extends AppCompatActivity implements Navigator {

    private BottomNavigationView bottomNavigationView;
    private AdView mAdView;
    private Fragment currentFragment = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);

        MobileAds.initialize(this, initializationStatus -> {
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentListener, false);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigate);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

//        if(savedInstanceState!=null){
//            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "mFragment");
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, fragment).commit();
//        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentGCDe()).commit();
//        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {

        switch (item.getItemId()){
            case R.id.gcdItem:
                currentFragment = new FragmentGCDe();
                break;

            case R.id.nokItem:
                currentFragment = new FragmentMG();
                break;
            case R.id.feItem:
                currentFragment = new FragmentFE();
                break;

            case R.id.modItem:
                currentFragment = new AddAlphaviteFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, currentFragment).commit();

        return true;
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currentFragment != null)
        getSupportFragmentManager().putFragment(outState, "mFragment", currentFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        updateOnUI();

        return true;
    }

    private FragmentManager.FragmentLifecycleCallbacks fragmentListener = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            updateOnUI();
        }
    };

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.frame_replace);
    }

    private void updateOnUI() {
        Fragment fragment = getCurrentFragment();

        if (fragment instanceof HasCustomTitle) {
            toolbar.setTitle(((HasCustomTitle) fragment).getTitle());
        }

        if (fragment instanceof HasCustomAction) {
            createCustomToolbarAction(((HasCustomAction)fragment).setCustomAction(this));
        } else {
            toolbar.getMenu().clear();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() > 0);
    }

    @SuppressLint("ResourceType")
    private void createCustomToolbarAction(CustomActionFragment customActionFragment) {
        toolbar.getMenu().clear();

        Drawable iconDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, customActionFragment.getIconRes()));
        iconDrawable.setTint(Color.WHITE);
        MenuItem menuItem = toolbar.getMenu().add("");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(iconDrawable);
        menuItem.setOnMenuItemClickListener(menuItem1 -> {
            customActionFragment.getOnCustomAction().run();
            return true;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(fragmentListener);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void startFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_replace, fragment)
                    .commit();
        }
    }
}