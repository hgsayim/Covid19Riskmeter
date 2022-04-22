package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnMoveToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void moveToRegisterPage(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }
    public void moveToLoginPage(View view){
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
    }

}