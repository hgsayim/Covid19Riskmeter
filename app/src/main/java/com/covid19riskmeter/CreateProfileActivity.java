package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.User;
import com.google.firebase.auth.FirebaseUser;

public class CreateProfileActivity extends AppCompatActivity {
    EditText txtName,txtAge;
    Spinner spnGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        txtName = (EditText) findViewById(R.id.txtName);
        txtAge = (EditText) findViewById(R.id.txtAge);
        spnGender = (Spinner) findViewById(R.id.spnGender);
    }
    public void moveToCompleteProfilePage(View view){

        Intent i = new Intent(this,CompleteProfileActivity.class);
        startActivity(i);

        FirebaseUser user = Database.auth.getCurrentUser();
        User.me.setName(txtName.getText().toString());
        Database.update_one(user.getUid(),"name",User.me.getName());
        User.me.setAge(Integer.parseInt(txtAge.getText().toString()));
        Database.update_one(user.getUid(),"age",User.me.getAge());
        User.me.setGender(spnGender.getSelectedItem().toString()); ;
        Database.update_one(user.getUid(),"gender",User.me.gender);
        User.me.setProfileLevel(1);
        Database.update_one(user.getUid(),"profileLevel",User.me.getProfileLevel());

    }
}