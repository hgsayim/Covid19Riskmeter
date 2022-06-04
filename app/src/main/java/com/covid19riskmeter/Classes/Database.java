package com.covid19riskmeter.Classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.covid19riskmeter.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

public class Database {
    public static FirebaseAuth auth;
    public final static  DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public static void upload(User user){
        database.child("users").child(user.id).setValue(user);
    }
    public static void update_one(String userid,String var,Object o){
        database.child("users").child(userid).child(var).setValue(o);
    }




}
