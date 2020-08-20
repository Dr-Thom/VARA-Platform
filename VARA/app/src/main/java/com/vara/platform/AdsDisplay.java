package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Timer;
import java.util.TimerTask;

public class AdsDisplay extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_display);
        
        MobileAds.initialize(this, "ca-app-pub-3228504719032164/2388548591");
//                , new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            System.console().printf( initializationStatus.toString() );
//            }
//        });
        //AdLoader.Builder builder = new AdLoader.Builder(this, getString(R.string.nativeas_ad_unit_id));
        //AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3228504719032164/3318486888");
        AdLoader.Builder builder = new AdLoader.Builder(this, "ca-app-pub-3940256099942544/6300978111");
        
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                TemplateView templateView = findViewById(R.id.ads_view);
                templateView.setNativeAd(unifiedNativeAd);

            }
        });
        AdLoader adLoader = builder.build();
        AdRequest adRequest = new AdRequest.Builder().build();
        adLoader.loadAd(adRequest);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(AdsDisplay.this, FarewellActivity.class);
                startActivity(intent);
                finish();
            }
        }, 60000);

    }
}