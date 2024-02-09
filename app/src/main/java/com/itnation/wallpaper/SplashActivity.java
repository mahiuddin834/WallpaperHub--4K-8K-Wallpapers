package com.itnation.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.startapp.sdk.adsbase.StartAppAd;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        StartAppAd.disableSplash();

        final Handler myHaldler;
        myHaldler = new Handler();
        myHaldler.postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

            }
        }, 3000);



    }
}