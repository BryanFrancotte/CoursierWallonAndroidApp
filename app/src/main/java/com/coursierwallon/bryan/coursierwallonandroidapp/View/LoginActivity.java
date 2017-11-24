package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.UserDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.*;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;
import com.google.gson.Gson;

/**
 * Created by bryan on 17-10-17.
 */

public class LoginActivity extends AppCompatActivity{
    private Button loginButton, guestButton, signUpButton;
    private EditText emailInput, passwordInput;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        guestButton = findViewById(R.id.guestButton);
        signUpButton = findViewById(R.id.signUpButton);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                UserTemp userTemp = new UserTemp(email, password);
                new UserConnexion().execute(userTemp);
            }
        });

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Intent intentToHome = new Intent(LoginActivity.this, HomeActivity.class);
                //startActivity(intentToHome);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSignUp = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intentToSignUp);
            }
        });
    }
    private class UserConnexion extends AsyncTask<UserTemp, Void, AccessToken> {

        @Override
        protected AccessToken doInBackground(UserTemp... userTemps) {
            UserDAO userDAO = new UserDAO();
            AccessToken token = null;
            try {
                token = userDAO.connexion(userTemps[0]);
                Log.i("Contenu inputJsonString",  token.toString());
            }catch (Exception e) {
                e.printStackTrace();
            }
            return token;
        }

        @Override
        protected void onPostExecute(AccessToken token){
            if(token != null){
                Gson gson = new Gson();
                String tokenConverted = gson.toJson(token);
                Intent intentToHome = new Intent(LoginActivity.this, HomeActivity.class);
                intentToHome.putExtra("accessToken", tokenConverted);

                startActivity(intentToHome);
            }else{
                Toast.makeText(LoginActivity.this,"connexion Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
