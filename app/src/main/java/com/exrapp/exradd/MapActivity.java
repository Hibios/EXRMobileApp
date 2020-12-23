package com.exrapp.exradd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

public class MapActivity extends AppCompatActivity {

    SharedPreferences appSettingPrefs;
    SharedPreferences.Editor sharedPrefsEdit;

    public static String orgName = "Organization Name";
    public static String orgID = "ID XXXXXXX";
    public static String orgPhone = "PhoneNumber";

    @SuppressLint({"ResourceAsColor", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        appSettingPrefs = getSharedPreferences("AppSettingPrefs", 0);
        sharedPrefsEdit = appSettingPrefs.edit();
        ApplicationClass.isNightModeOn = appSettingPrefs.getBoolean("NightMode", false);

        if (ApplicationClass.isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        ((TextView) findViewById(R.id.orgDescName)).setText(orgName);
        ((TextView) findViewById(R.id.orgDescID)).setText(orgID);
        ((TextView) findViewById(R.id.orgPhone)).setText(orgPhone);

        Button next = findViewById(R.id.orgs_button);
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
