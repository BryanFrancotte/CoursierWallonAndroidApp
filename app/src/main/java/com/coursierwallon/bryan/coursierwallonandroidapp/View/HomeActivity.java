package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.ValuesDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AccessToken;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.gson.Gson;

/**
 * Created by franc on 26-10-17.
 */

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Button parcelButton = findViewById(R.id.parcelButton);
        Button letterButton = findViewById(R.id.letterButton);

        parcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOrder = new Intent(HomeActivity.this, PickupParcelActivity.class);
                startActivity(intentToOrder);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // calling the home screen from the phone
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
