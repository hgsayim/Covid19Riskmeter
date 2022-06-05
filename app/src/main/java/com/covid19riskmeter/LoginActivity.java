package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail,txtPass;
    TextView txtInfo;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = (EditText) findViewById(R.id.txtLoginEmail);
        txtPass  = (EditText) findViewById(R.id.txtLoginPass);
        txtInfo  = (TextView) findViewById(R.id.txtInfo2);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
    public void moveToForgotPassPage(View view){
        Operations.moveToPage(this,ForgotPassActivity.class,false);
    }
    private void moveToHomePage(){
        Operations.moveToPage(this,HomeActivity.class,true);

    }
    public void SignIn(View view){
        String email = txtEmail.getText().toString();
        String pass = txtPass.getText().toString();
        if(email.contains("@") & email.length()>5){
            if(pass.length()>=8){
                btnLogin.setEnabled(false);
                Log_in(email,pass);
            }else{
                Operations.errorLabel(txtInfo,"Please enter your password correct.",3);
            }
        }else{
            Operations.errorLabel(txtInfo,"Please enter a valid email.",3);
        }
    }
    public void Log_in(String email,String pass){

        Database.auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    moveToHomePage();
                }else{
                    btnLogin.setEnabled(true);
                    Operations.errorLabel(txtInfo,"Email and password does not matched. Please check your information.",3);
                }
            }
        });


    }

}