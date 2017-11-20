package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;

/**
 * Created by franc on 26-10-17.
 */

public class HomeActivity extends AppCompatActivity {
    private Button parcelButton,letterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        parcelButton = findViewById(R.id.parcelButton);
        letterButton = findViewById(R.id.letterButton);

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
}
