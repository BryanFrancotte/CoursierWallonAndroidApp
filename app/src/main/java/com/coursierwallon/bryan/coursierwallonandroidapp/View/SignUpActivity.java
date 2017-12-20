package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coursierwallon.bryan.coursierwallonandroidapp.DAO.UserDAO;
import com.coursierwallon.bryan.coursierwallonandroidapp.Model.UserModel;
import com.coursierwallon.bryan.coursierwallonandroidapp.R;

import java.net.HttpURLConnection;

/**
 * Created by franc on 19-10-17.
 */

public class SignUpActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar customToolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(customToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button registerButton = findViewById(R.id.register);
        final EditText username = findViewById(R.id.userInput);
        final EditText email = findViewById(R.id.emailInput);
        final EditText password = findViewById(R.id.passwordInput);
        final EditText passwordConfirm = findViewById(R.id.confirmPasswordInput);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel newUser = new UserModel(
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        passwordConfirm.getText().toString()
                );
                if(newUser.isvalid()){
                    ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                        new Registration().execute(newUser);
                    }else{
                        Toast.makeText(SignUpActivity.this, R.string.connection_lost, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(SignUpActivity.this, R.string.SignUp_InfoError, Toast.LENGTH_SHORT).show();// TODO : faire ça avec @string
                }
            }
        });
    }


    private class Registration extends AsyncTask<UserModel, Void, Integer>{

        @Override
        protected Integer doInBackground(UserModel... userModels) {

            Integer resultCode = null;
            UserDAO dao = new UserDAO();

            try{
                resultCode = dao.registration(userModels[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
            return resultCode;
        }

        @Override
        protected void onPostExecute(Integer resultCode) {
            if(resultCode == HttpURLConnection.HTTP_OK){
                Toast.makeText(SignUpActivity.this, R.string.SignUp_Success, Toast.LENGTH_SHORT).show();// TODO: faire ça avec @string
                Intent intentToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentToLogin);
            }else{
                Toast.makeText(SignUpActivity.this, R.string.SignUp_Error, Toast.LENGTH_SHORT).show();// TODO: faire ça avec @string
            }
        }
    }
}
