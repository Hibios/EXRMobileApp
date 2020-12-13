package com.exrapp.exradd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OrgDescriptionActivity extends AppCompatActivity {

    public static String orgName = "Organization Name";
    public static String orgID = "ID XXXXXXX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_desc);

        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        main_toolbar.setTitle("Описание");
        setSupportActionBar(main_toolbar);

        ((TextView) findViewById(R.id.orgDescName)).setText(orgName);
        ((TextView) findViewById(R.id.orgDescID)).setText(orgID);

        Button next = findViewById(R.id.desc_button);
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
        if(item.getItemId() == R.id.home_action) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return true;
    }
}