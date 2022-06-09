package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.covid19riskmeter.Classes.Database;
import com.bumptech.glide.Glide;
import com.covid19riskmeter.Classes.Blog;
import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {
    public TextView txtTitle;
    private Button btnRiskInfo;
    private ImageView imgBlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        txtTitle = (TextView) findViewById(R.id.txtHomeTitle);
        btnRiskInfo = (Button) findViewById(R.id.btnRiskInfo);
        imgBlog = (ImageView) findViewById(R.id.imgBlog2);
        txtTitle.setText("Hi, " + User.me.name);
        int user_risk = Operations.calculateRisk(User.me.age, User.me.vaccination, User.me.tobacco, User.me.gender);
        btnRiskInfo.setText("Your Potential Risk: %" + user_risk);
        Glide.with(HomeActivity.this).load(Blog.list.get(0).getCoverPhoto()).into(imgBlog);






    }
    public void moveToBlogPage(View view){

        Operations.moveToPage(this,BlogActivity.class,false);
    }
    private void setProfile(String json){
        String name = json.split("name=")[1].split(",")[0];
        String gender = json.split("gender=")[1].split(",")[0];
        int age = Integer.parseInt(json.split("age=")[1].split(",")[0]);
        User.me.setName(name);
        User.me.setGender(gender);
        User.me.setAge(age);
    }

    public void SignOut(View view){
        Database.auth.signOut();
        Operations.moveToPage(this,SplashActivity.class,true);

    }
    public void moveToCalculatePage(View view){
        Operations.moveToPage(this,CalculateActivity.class,false);
    }
    public void moveToMapPage(View view){
        Operations.moveToPage(this,MapsActivity.class,false);
    }
    public void moveToArticlePage(View view){
        Blog.current = 0;
        Operations.moveToPage(this,ArticleActivity.class,false);
    }
}