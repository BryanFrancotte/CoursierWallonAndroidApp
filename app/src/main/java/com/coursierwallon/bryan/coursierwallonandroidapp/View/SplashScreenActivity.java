package com.coursierwallon.bryan.coursierwallonandroidapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.coursierwallon.bryan.coursierwallonandroidapp.R;

/**
 * Created by franc on 19-10-17.
 */

public class SplashScreenActivity extends AppCompatActivity{
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image = findViewById(R.id.coursier_logo);
        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.anim_startup_transition);
        image.startAnimation(myAnim);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent intentToLogin = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intentToLogin);
                    finish();
                }
            }
        };
        timer.start();
    }
}