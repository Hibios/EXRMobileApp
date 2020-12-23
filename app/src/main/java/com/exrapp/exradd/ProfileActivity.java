package com.exrapp.exradd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;


public class ProfileActivity extends AppCompatActivity {

    SharedPreferences appSettingPrefs;
    SharedPreferences.Editor sharedPrefsEdit;

    @Override
    protected void onResume() {
        super.onResume();
        ApplicationClass.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApplicationClass.activityPaused();
    }

    @SuppressLint({"ResourceAsColor", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        appSettingPrefs = getSharedPreferences("AppSettingPrefs", 0);
        sharedPrefsEdit = appSettingPrefs.edit();
        ApplicationClass.isNightModeOn = appSettingPrefs.getBoolean("NightMode", false);

        if (ApplicationClass.isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        main_toolbar.setTitle("Профиль");
        setSupportActionBar(main_toolbar);

        if (ApplicationClass.QR_IMAGE == null) {
            try {
                ApplicationClass.generateQRCodeImage("https://youtu.be/dQw4w9WgXcQ");
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

        Button next = findViewById(R.id.stock_button);
        ImageView view = findViewById(R.id.qr_image);
        view.setImageBitmap(ApplicationClass.QR_IMAGE);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), OrgListActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home_action && !ApplicationClass.isActivityVisible()) {
            Log.i("mode", "Home button pressed!");
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.mode_action) {
            if (ApplicationClass.isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sharedPrefsEdit.putBoolean("NightMode", false);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sharedPrefsEdit.putBoolean("NightMode", true);
            }
            sharedPrefsEdit.apply();
        }
        return true;
    }
}
