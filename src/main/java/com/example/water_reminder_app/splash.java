package com.example.water_reminder_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        SharedPreferences getshrd = getSharedPreferences("userdetail",MODE_PRIVATE);
        Integer weight= getshrd.getInt("weight",0);

        boolean check  = getshrd.getBoolean("flag",false);
        Intent inext2;
        if(check){
            inext2 = new Intent(splash.this,MainActivity.class);
        }else {
            inext2 = new Intent(splash.this,user_detail.class);
        }
//        ihome = new Intent(splash.this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(inext2);
                finish();
            }
        }, 3000);
    }
}