package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail,txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtPass  = (EditText) findViewById(R.id.txtLoginPass);
    }
    public void moveToForgotPassPage(View view){
        Operations.moveToPage(this,ForgotPassActivity.class,false);
    }
    private void moveToHomePage(){
        Operations.moveToPage(this,HomeActivity.class,true);

    }
    public void Log_in(View view){
        String email = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();
        Database.auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    moveToHomePage();
                }else{

                }
            }
        });


    }

}