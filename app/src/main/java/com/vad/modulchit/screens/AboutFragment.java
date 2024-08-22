package com.vad.modulchit.screens;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vad.modulchit.R;
import com.vad.modulchit.screens.contract.HasCustomTitle;

public class AboutFragment extends Fragment implements HasCustomTitle {

    @Override
    public int getTitle() {
        return R.string.about;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @SuppressLint("IntentReset")
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        TextView textViewVersion = v.findViewById(R.id.textViewVersion);
        TextView textViewEmail = v.findViewById(R.id.textViewEmail);
        TextView textViewGithub = v.findViewById(R.id.textViewGithub);

        Context context = v.getContext();

        String version;
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        textViewVersion.setText("Version " + version);

        textViewEmail.setOnClickListener((view) -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:gabderahmanov99@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Analysis of algorithms");
            startActivity(Intent.createChooser(intent, getString(R.string.send_message)));
        });

        textViewGithub.setOnClickListener((view) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/vadhub/ModulChit"));
            startActivity(browserIntent);
        });
    }
}
