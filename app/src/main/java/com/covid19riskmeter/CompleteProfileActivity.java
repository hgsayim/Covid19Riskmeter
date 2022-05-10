package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CompleteProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
    }
    public void moveToHomePage(View view){
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }
}