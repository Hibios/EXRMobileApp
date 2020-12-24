package com.exrapp.exradd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

/**
 * The class is responsible for creating and
 * displaying a page with a map of partners as
 * well as some actions on separately selected persons.
 *
 * @author  Ivan Minakov, Kravtsov Stefan, Belousov Viktor, Tolstykh Mikhail
 * @version 3.0
 * @since   2020-12-24
 */
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

        // Retrieving the saved state of the application
        appSettingPrefs = getSharedPreferences("AppSettingPrefs", 0);
        sharedPrefsEdit = appSettingPrefs.edit();
        // Loading the state of the dark theme variable
        ApplicationClass.isNightModeOn = appSettingPrefs.getBoolean("NightMode", false);

        // Changing the theme depending on the state of the variable
        if (ApplicationClass.isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        // Fill in the fields with data
        ((TextView) findViewById(R.id.orgDescName)).setText(orgName);
        ((TextView) findViewById(R.id.orgDescID)).setText(orgID);
        ((TextView) findViewById(R.id.orgPhone)).setText(orgPhone);

        // Button to go to the page with organizations
        Button next = findViewById(R.id.orgs_button);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), OrgListActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    /**
     * The method inserts the necessary buttons
     * and elements into the menu when creating
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    /**
     * The method is called when you click on one of the items
     * on the menu bar, then it is determined which item was clicked.
     * In this method, the transition to the profile page is processed,
     * as well as the theme change, while maintaining its state.
     * @param item Menu bar item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home_action && !ApplicationClass.isActivityVisible()) {
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
