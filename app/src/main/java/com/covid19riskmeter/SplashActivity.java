package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.covid19riskmeter.Classes.Blog;
import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Database.auth = FirebaseAuth.getInstance();
        //Database.auth.signOut();
        if(Database.auth.getCurrentUser()!=null){
            Operations.defineLaunchScreen(this);
            //started to get user info

        }else{
            new Timer().schedule(new TimerTask(){
                public void run() {
                    Operations.moveToPage(SplashActivity.this,MainActivity.class,true);
                }
            }, 1000);

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

}