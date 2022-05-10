package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class CreateProfileActivity extends AppCompatActivity {
    private Spinner spnGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }
    public void moveToCompleteProfilePage(View view){
        Intent i = new Intent(this,CompleteProfileActivity.class);
        startActivity(i);
    }
}