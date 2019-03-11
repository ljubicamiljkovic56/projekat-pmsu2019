package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pmsu_2019_projekat.R;

import static com.example.pmsu_2019_projekat.R.*;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_splash);

        new InitTask().execute();
    }

    private class InitTask extends AsyncTask<Void, Void, Void>
    {
        private long startTime;

        @Override
        protected void onPreExecute()
        {
            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            continueLogin();

            return null;
        }

        private void continueLogin()
        {
            long timeLeft = SPLASH_TIME_OUT - (System.currentTimeMillis() - startTime);
            if(timeLeft < 0) timeLeft = 0;
            SystemClock.sleep(timeLeft);
            startMainActivity();
        }
    }

    private void startMainActivity()
    {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
