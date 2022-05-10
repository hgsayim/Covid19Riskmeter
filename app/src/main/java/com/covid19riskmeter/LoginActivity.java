package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void moveToForgotPassPage(View view){
        Intent i = new Intent(this,ForgotPassActivity.class);
        startActivity(i);
    }
    public void moveToCreateProfilePage(View view){
        Intent i = new Intent(this,CreateProfileActivity.class);
        startActivity(i);
    }
    public void Log_in(View view){
        boolean isFirst = true;
        if(isFirst){
            moveToCreateProfilePage(view);
        }
    }
}