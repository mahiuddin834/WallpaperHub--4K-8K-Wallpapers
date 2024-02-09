package com.itnation.wallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {

    ImageView wallpaperView;
    Button setWallpaperButton;
    WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wallpaper);

        setWallpaperButton = findViewById(R.id.setWallpaperButton);
        wallpaperView = findViewById(R.id.wallpaperView);

        String imgUrl = getIntent().getStringExtra("wallpaperURL");
        Glide.with(this).load(imgUrl).into(wallpaperView);


        wallpaperManager=WallpaperManager.getInstance(getApplicationContext());

        setWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // showRewardedVideo();



                Glide.with(WallpaperActivity.this).asBitmap().load(imgUrl).listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Bitmap> target, boolean isFirstResource) {

                        Toast.makeText(WallpaperActivity.this, "Failed to set wallpaper,,,", Toast.LENGTH_SHORT).show();

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Bitmap resource, @NonNull Object model, Target<Bitmap> target, @NonNull DataSource dataSource, boolean isFirstResource) {

                        try {

                            wallpaperManager.setBitmap(resource);

                        }catch (IOException e){

                            e.printStackTrace();

                            Toast.makeText(WallpaperActivity.this, "Failed to set wallpaper,,,", Toast.LENGTH_SHORT).show();


                        }

                        return false;
                    }
                }).submit();


                FancyToast.makeText(WallpaperActivity.this,"Wallpaper Set To home Screen",FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();


            }
        });


    }

    // reworded adds

    public void showRewardedVideo() {
        final StartAppAd rewardedVideo = new StartAppAd(this);

        rewardedVideo.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {
                // Grant the reward to user
            }
        });

        rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                rewardedVideo.showAd();
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                // Can't show rewarded video
            }
        });
    }



}