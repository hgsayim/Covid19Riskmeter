package com.covid19riskmeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ForgotPassActivity extends AppCompatActivity {
    EditText txtForgot;
    TextView txtForgotInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        txtForgot = (EditText) findViewById(R.id.txtForgotEmail);
        txtForgotInfo = (TextView) findViewById(R.id.txtForgotInfo);

    }
    public void sendForgotLink(View view){
        String email = txtForgot.getText().toString();
        if(email.contains("@") & email.length()>=5)
        {
            Database.auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Operations.toastNotification(getApplicationContext(),"An email sent to " + email  + " for resetting your password.");
                    Intent i = new Intent(ForgotPassActivity.this,LoginActivity.class);
                    startActivity(i);
                }
            });
        }else{
            Operations.errorLabel(txtForgotInfo,"Please enter a valid email address.",3);
        }
    }
}