package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtTitle = (TextView) findViewById(R.id.txtHomeTitle);
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
                    String email_notparsed = json.split("email=")[1].split(",")[0];
                    String email;
                    if (email_notparsed.endsWith("\\}")){
                        email = email_notparsed.split("\\}")[0];
                    }else{
                        email = email_notparsed;
                    }
                    String pass = json.split("password=")[1].split(",")[0];
                    User.me = new User(user.getUid(),email,pass,null,0,0,false,null,null);


                    if(profileLevel==0){ //  no name, age , gender, --just have mail and pass
                        Log.d("LEVEL0000000 email----- ", email);
                        Operations.moveToPage(HomeActivity.this,CreateProfileActivity.class,true);
                    }else if(profileLevel==1){ // no blood type, tobacco, vaccination, --- just have mail, pass, name
                        setProfile(json);
                        Operations.moveToPage(HomeActivity.this,CompleteProfileActivity.class,true);
                    }else{ // full profile
                        setProfile(json);
                        txtTitle.setText("Hi, " + User.me.name);
                    }





                }
            }
        });





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