package com.vad.modulchit.screens.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.CustomActionFragment;
import com.vad.modulchit.screens.contract.HasCustomAction;
import com.vad.modulchit.screens.contract.HasCustomTitle;
import com.vad.modulchit.screens.contract.Navigator;
import com.vad.modulchit.screens.menu.FragmentMenu;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;

public class MainActivity extends AppCompatActivity implements Navigator {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        BannerAdView mBanner = (BannerAdView) findViewById(R.id.adView);
        mBanner.setAdUnitId("R-M-2167912-1");
        mBanner.setAdSize(AdSize.stickySize(AdSize.FULL_SCREEN.getWidth()));
        AdRequest adRequest = new AdRequest.Builder().build();
        mBanner.loadAd(adRequest);

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentListener, false);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_replace, new FragmentMenu()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        updateOnUI();
        return true;
    }

    private final FragmentManager.FragmentLifecycleCallbacks fragmentListener = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            updateOnUI();
        }

        @Override
        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            super.onFragmentViewDestroyed(fm, f);
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
                    .add(R.id.frame_replace, fragment)
                    .commit();
        }
    }

    @Override
    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}