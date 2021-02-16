package com.example.nearme.ui.Acitivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.nearme.R;
import com.example.nearme.addcontrollers.BannerAddController;
import com.google.android.gms.ads.AdView;

public class MapWebViewActivity extends AppCompatActivity {
    private BannerAddController bannerAddController;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_web_view);
        mAdView = findViewById(R.id.adView);
        bannerAddController=new BannerAddController(getApplicationContext(),mAdView);
        bannerAddController.LoadBannerAdd();

        String webview=getIntent().getExtras().getString("url");
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl(webview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onBackPressed();
    }
}