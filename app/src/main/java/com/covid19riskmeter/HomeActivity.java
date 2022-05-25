package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void moveToMapPage(View view){
        Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);
    }
    public void moveToArticlePage(View view){
        // article = Db.find article title,image,description

        Intent i = new Intent(this,ArticleActivity.class);
        startActivity(i);
    }
}