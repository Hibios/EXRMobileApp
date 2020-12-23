package com.exrapp.exradd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrgListActivity extends AppCompatActivity {

    SharedPreferences appSettingPrefs;
    SharedPreferences.Editor sharedPrefsEdit;

    ArrayList<Organization> organizations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_list);

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

        RecyclerView rvOrganizations = findViewById(R.id.rvOrganizations);

        organizations = Organization.createOrgList(20);
        OrgAdapter adapter = new OrgAdapter(organizations);
        rvOrganizations.setAdapter(adapter);
        rvOrganizations.setLayoutManager(new LinearLayoutManager(this));
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

    public void onCardClick(View view) {
        if(view.getId() == R.id.item2_card)
        {
            TextView name = view.findViewById(R.id.org_name);
            TextView id = view.findViewById(R.id.org_id);
            TextView ph = view.findViewById(R.id.org_phone);
            OrgDescriptionActivity.orgName = name.getText().toString();
            OrgDescriptionActivity.orgID = id.getText().toString();
            OrgDescriptionActivity.orgPhone = ph.getText().toString();
            Intent intent = new Intent(this, OrgDescriptionActivity.class);
            startActivity(intent);
        }
    }
}