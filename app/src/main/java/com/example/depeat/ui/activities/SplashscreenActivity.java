package com.example.depeat.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.depeat.R;

public class SplashscreenActivity extends AppCompatActivity {
    private long ms=0;
    private long splashTime=2000;
    private boolean splashActive = true;
    private boolean paused=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashscreenActivity.this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        SplashscreenActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splashscreen);
        /*Thread mythread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if(!paused)
                            ms=ms+100;
                        sleep(100);
                    }
                } catch(Exception e) {}
                finally {
                    Intent intent = new Intent(SplashscreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        mythread.start();*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // finish the splash activity so it can't be returned to
                SplashscreenActivity.this.finish();
                // create an Intent that will start the second activity
                Intent mainIntent = new Intent(SplashscreenActivity.this, MainActivity.class);
                SplashscreenActivity.this.startActivity(mainIntent);
            }
        }, 3000); // 3000 milliseconds
    }
}
