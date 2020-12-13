package com.exrapp.exradd;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrgListActivity extends AppCompatActivity {

    ArrayList<Organization> organizations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_list);

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
        if(item.getItemId() == R.id.home_action) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void onCardClick(View view) {
        if(view.getId() == R.id.item_card)
        {
            TextView name = view.findViewById(R.id.org_name);
            TextView id = view.findViewById(R.id.org_id);
            OrgDescriptionActivity.orgName = name.getText().toString();
            OrgDescriptionActivity.orgID = id.getText().toString();
            Intent intent = new Intent(this, OrgDescriptionActivity.class);
            startActivity(intent);
        }
    }
}