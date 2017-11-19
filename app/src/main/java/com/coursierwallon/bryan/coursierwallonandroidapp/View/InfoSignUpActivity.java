package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;

/**
 * Created by franc on 20-10-17.
 */

public class InfoSignUpActivity extends AppCompatActivity{
    private EditText firstName,
            lastName,
            phoneNumber,
            birtday,
            streetName,
            houseNumber,
            boxNumber,
            postcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_info);

        Toolbar customToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
