package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

        Gson gson = new Gson();
        Bundle bundle = this.getIntent().getExtras();
        String tokenString = bundle.getString("accessToken");
        AccessToken token = gson.fromJson(tokenString, AccessToken.class);
        new Values().execute(token);
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

    private class Values extends AsyncTask<AccessToken, Void, String> {

        @Override
        protected String doInBackground(AccessToken... accessTokens) {
            ValuesDAO dao = new ValuesDAO();
            try {
                return dao.getAll(accessTokens[0]);
            }catch (Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(String string){
            if(string != null) {
                Toast.makeText(HomeActivity.this, string, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(HomeActivity.this, "pas autorisé", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
