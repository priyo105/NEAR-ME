package com.example.nearme.ui.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.nearme.MainActivity;
import com.example.nearme.R;
import com.example.nearme.addcontrollers.BannerAddController;
import com.example.nearme.ui.home.HomeFragment;
import com.google.android.gms.ads.AdView;

public class LocationExceptionActivity extends AppCompatActivity {
    private BannerAddController bannerAddController;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_exception);
        mAdView = findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getApplicationContext(),mAdView);
        bannerAddController.LoadBannerAdd();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}