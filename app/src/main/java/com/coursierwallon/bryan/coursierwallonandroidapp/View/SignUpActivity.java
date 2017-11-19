package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;

/**
 * Created by franc on 19-10-17.
 */

public class SignUpActivity extends AppCompatActivity{
    private Button registerButton;
    private EditText email, password, paswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar customToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        registerButton = findViewById(R.id.register);
        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);

        final Intent intentToInfoSignUp = new Intent(this, InfoSignUpActivity.class);
        intentToInfoSignUp.putExtra("email", email.getText().toString());
        intentToInfoSignUp.putExtra("password", password.getText().toString());

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    startActivity(intentToInfoSignUp);
                }else{
                    Toast.makeText(SignUpActivity.this, R.string.empty_input_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
