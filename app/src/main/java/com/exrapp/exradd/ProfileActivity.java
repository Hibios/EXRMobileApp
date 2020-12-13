package com.exrapp.exradd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;


public class ProfileActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
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
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
