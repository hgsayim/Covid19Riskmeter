package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import kotlin.text.Regex;

public class HomeActivity extends AppCompatActivity {
    public TextView txtTitle;
    private Button btnRiskInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtTitle = (TextView) findViewById(R.id.txtHomeTitle);
        btnRiskInfo = (Button) findViewById(R.id.btnRiskInfo);
        FirebaseUser user = Database.auth.getCurrentUser();
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
                    Log.d("json----- ", json);
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
        // article = Db.find article title,image,description
        Operations.moveToPage(this,ArticleActivity.class,false);
    }
}