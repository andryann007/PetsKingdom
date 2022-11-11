package com.example.petskingdom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //start main screen after 2seconds
        new Handler().postDelayed(this::checkUser,2500);//2500 means 2.5 seconds
    }

    private void checkUser(){
        startActivity(new Intent(Splash.this, Login.class));
        finish();
    }
}