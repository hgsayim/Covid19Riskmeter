package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;

import com.covid19riskmeter.Classes.Database;
import com.covid19riskmeter.Classes.Operations;
import com.covid19riskmeter.Classes.User;
import com.google.firebase.auth.FirebaseUser;

public class CompleteProfileActivity extends AppCompatActivity {
    Spinner spnBlood,spnVaccination,spnCovid;
    Switch switchTobacco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        spnBlood = (Spinner) findViewById(R.id.spnBlood);
        spnVaccination = (Spinner) findViewById(R.id.spnVaccination);
        spnCovid = (Spinner) findViewById(R.id.spnCovid);
        switchTobacco = (Switch) findViewById(R.id.switchTobacco);
    }
    public void moveToHomePage(View view){
        FirebaseUser user = Database.auth.getCurrentUser();
        String userid = user.getUid();
        User.me.setBlood(spnBlood.getSelectedItem().toString());
        Database.update_one(userid,"blood",User.me.getBlood());
        User.me.setVaccination(spnVaccination.getSelectedItem().toString());
        Database.update_one(userid,"vaccination",User.me.getVaccination());
        User.me.tobacco = switchTobacco.isChecked();
        Database.update_one(userid,"tobacco",User.me.tobacco);
        User.me.setProfileLevel(2);
        Database.update_one(userid,"profileLevel",User.me.getProfileLevel());
        Operations.moveToPage(this,HomeActivity.class,true);
    }
}