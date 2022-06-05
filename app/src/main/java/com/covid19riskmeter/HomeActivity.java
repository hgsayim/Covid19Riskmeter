package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.List;

import kotlin.text.Regex;

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
        imgBlog = (ImageView) findViewById(R.id.imgBlog);
        FirebaseUser user = Database.auth.getCurrentUser();
        //started to get user info
        Query query = Database.database.child("users").orderByChild("id").equalTo(user.getUid());
        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase----------------", "Error getting data", task.getException());
                }
                else {
                    String json = String.valueOf(task.getResult().getValue()).substring(30);
                    int profileLevel = Integer.parseInt(json.split("profileLevel=")[1].split(",")[0]);
                    String email = json.split("email=")[1].split(",")[0];
                    if (email.endsWith("\\}")) // if email object is at the end of the json
                        email = email.split("\\}")[0];

                    String pass = json.split("password=")[1].split(",")[0];
                    User.me = new User(user.getUid(),email,pass,null,0,0,false,null,null);
                    if(profileLevel==0){ //  no name, age , gender, --just have mail and pass

                        Operations.moveToPage(HomeActivity.this,CreateProfileActivity.class,true);
                    }else if(profileLevel==1){ // no blood type, tobacco, vaccination, --- just have mail, pass, name
                        setProfile(json);
                        Operations.moveToPage(HomeActivity.this,CompleteProfileActivity.class,true);
                    }else{ // full profile
                        setProfile(json);
                        String strTobacco = json.split("tobacco=")[1].split(",")[0];
                        if(strTobacco.equals("true"))
                            User.me.tobacco=true;
                        else
                            User.me.tobacco=false;
                        String vaccination = json.split("vaccination=")[1].split("\\}")[0];
                        String blood = json.split("blood=")[1].split(",")[0];
                        User.me.setBlood(blood);
                        User.me.setVaccination(vaccination);
                        int user_risk = Operations.calculateRisk(User.me.age,User.me.vaccination,User.me.tobacco,User.me.gender);
                        btnRiskInfo.setText("Your potential risk: " + user_risk + "%");
                        txtTitle.setText("Hi, " + User.me.name);
                        Blog.list = new ArrayList<Blog>();

                        // getting blogs after getting user info
                        Query query = Database.database.child("blogs");
                        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                if (!task.isSuccessful()) {
                                    Log.e("firebase----------------", "Error getting data", task.getException());
                                }
                                else {
                                    String json = String.valueOf(task.getResult().getValue());
                                    String[] blogs = json.split("\\{");
                                    for(String s:blogs){
                                        if(!s.equals("[null, ")){
                                            int id = Integer.parseInt(s.split("id=")[1].split(",")[0]);
                                            String title = s.split("title=")[1].split(",")[0];
                                            String img_url = s.split("url=")[1].split("\\}")[0];
                                            String content = s.split("content=")[1].split(", url=")[0];
                                            Blog b = new Blog(id, "05.06.2022",title,content,img_url);
                                        }
                                    }
                                    Glide.with(HomeActivity.this).load(Blog.list.get(Blog.list.size()-1).getCoverPhoto()).into(imgBlog);
                                }
                            }
                        });
                    }
                }
            }
        });






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
        Operations.moveToPage(this,MainActivity.class,true);

    }
    public void moveToCalculatePage(View view){
        Operations.moveToPage(this,CalculateActivity.class,false);
    }
    public void moveToMapPage(View view){
        Operations.moveToPage(this,MapsActivity.class,false);
    }
    public void moveToArticlePage(View view){
        Blog.current = Blog.list.size()-1;
        Operations.moveToPage(this,ArticleActivity.class,false);
    }
}