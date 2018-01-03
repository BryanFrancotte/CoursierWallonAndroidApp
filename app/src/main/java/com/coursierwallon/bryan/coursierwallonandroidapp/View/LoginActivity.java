package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.Constant.ApiConstant;
import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.UserDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Exceptions.HttpResultException;
import com.coursierwallon.bryan.coursierwallonandroidapp.FirebaseService.MyFirebaseTokenService;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.AccessToken;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.UserModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;

/**
 * Created by bryan on 17-10-17.
 */

public class LoginActivity extends AppCompatActivity{

    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        Button guestButton = findViewById(R.id.guestButton);
        //Button signUpButton = findViewById(R.id.signUpButton);
        final EditText emailInput = findViewById(R.id.emailInput);
        final EditText passwordInput = findViewById(R.id.passwordInput);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                UserModel user = new UserModel(email, password);

                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                    new UserConnexion().execute(user);
                }else{
                    Toast.makeText(LoginActivity.this, R.string.connection_lost, Toast.LENGTH_SHORT).show();
                }
            }
        });

        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intentToHome = new Intent(LoginActivity.this, SignUpActivity.class);
               startActivity(intentToHome);
            }
        });

        //signUpButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intentToSignUp = new Intent(LoginActivity.this,SignUpActivity.class);
        //        startActivity(intentToSignUp);
        //    }
        //});
    }
    private class UserConnexion extends AsyncTask<UserModel, Void, AccessToken> {

        private HttpResultException exception;

        @Override
        protected AccessToken doInBackground(UserModel... users) {
            UserDAO userDAO = new UserDAO();
            AccessToken token = null;
            try {
                token = userDAO.connexion(users[0]);
            }catch(HttpResultException e){
                exception = e;
                cancel(true);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return token;
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(AccessToken token){

            preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(ApiConstant.TOKEN, token.getToken());
            if(editor.commit()) {
                Intent intentToHome = new Intent(LoginActivity.this, HomeActivity.class);
                new MyFirebaseTokenService(LoginActivity.this).onTokenRefresh();
                startActivity(intentToHome);
            }else {
                Toast.makeText(LoginActivity.this, R.string.LoginActivity_Error, Toast.LENGTH_SHORT).show();// TODO: faire ça avec @string
            }
        }
    }
}