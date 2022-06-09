package com.covid19riskmeter.Classes;


import com.google.android.gms.maps.model.LatLng;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Database {
    public static FirebaseAuth auth;
    public static List<LatLng> locations;
    public static FirebaseUser user;
    public final static  DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public static void upload(User user){
        database.child("users").child(user.id).setValue(user);
    }
    public static void update_one(String userid,String var,Object o){
        database.child("users").child(userid).child(var).setValue(o);
    }
    public static void createFakeLocations(){ //creating fake maps data
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString();
            Random r = new Random();
            int a = r.nextInt(1000);
            double d = a * 0.000001;
            LatLng center = new LatLng(41.07409,28.987787);
            LatLng rand = Operations.createRandomLocation(center,10000);
            database.child("locations").child(uid).child("latitude").setValue(rand.latitude);
            database.child("locations").child(uid).child("longitude").setValue(rand.longitude);
        }

    }





}
