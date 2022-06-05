package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database.auth = FirebaseAuth.getInstance();

        //Database.auth.signOut();
        if(Database.auth.getCurrentUser()!=null)
            Operations.moveToPage(this,HomeActivity.class,true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    public void moveToRegisterPage(View view){
        Operations.moveToPage(this,RegisterActivity.class,false);

    }
    public void moveToLoginPage(View view){
      Operations.moveToPage(this,LoginActivity.class,false);

    }

}