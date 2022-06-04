package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {
    EditText email,pass1,pass2;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.txtEmail);
        pass1 = (EditText) findViewById(R.id.txtPass);
        pass2 = (EditText) findViewById(R.id.txtPass2);
        info = (TextView) findViewById(R.id.txtInfo);


    }
    public void SignUp(View view){
        String pass = pass1.getText().toString();
        String _pass = pass2.getText().toString();
        String _email = email.getText().toString();
        if(_email.contains("@") & _email.length()>=5){
            if (pass.equals(_pass) & pass.length()>=8){
                createUser(_email,pass);

            }else{
                Operations.errorLabel(info,"Password must be 8 digits or more and passwords must be matched.",3);
            }

        }else{
            Operations.errorLabel(info,"Please enter a valid email address.",3);

        }

    }
    public void showCreateProfilePage(){
        Operations.moveToPage(this,CreateProfileActivity.class,true);
    }

    public void createUser(String email, String password){
        Database.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = Database.auth.getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                User.me = new User(user.getUid(),email,password,"null",0,0,false,"null","null");
                                                Log.d("USER", User.me.toString());
                                                showCreateProfilePage();
                                                Database.upload(User.me);


                                            }
                                            else {
                                                Operations.errorLabel(info,"This email address is used by another user.",3);
                                            }
                                        }
                                    });
                        } else {
                            Operations.errorLabel(info,"This email address is used by another user.",3);
                        }

                    }
                });


    }
}