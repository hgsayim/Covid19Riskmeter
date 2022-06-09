package com.covid19riskmeter.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.covid19riskmeter.CompleteProfileActivity;
import com.covid19riskmeter.CreateProfileActivity;
import com.covid19riskmeter.HomeActivity;
import com.covid19riskmeter.SplashActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Operations {
    public static int calculated_risk; // defined for being accessible from any class
    public static String getFromJSON(String json, String key){
        String s = json.split(key + "=")[1];
        if(s.contains(","))
            return s.split(",")[0];
        else
            return s.split("\\}")[0]; //if key=value pair is at the end of JSON
    }

    public static void errorLabel(TextView label, String errorLine,int seconds){
        if(label.getCurrentTextColor()!=Color.RED){
            final String firstLine = label.getText().toString();
            final int firstColor = label.getCurrentTextColor();
            ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

            final int[] secondsToWait = {seconds};
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    // changes label text and color for a duration
                    secondsToWait[0]--;
                    label.setText(errorLine);
                    label.setTextColor(Color.RED);

                    if (secondsToWait[0] == 0) {
                        label.setText(firstLine);
                        label.setTextColor(firstColor);
                        exec.shutdown();
                    }
                }
            };
            label.setText(errorLine);
            label.setTextColor(Color.RED);
            exec.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        }


    }

    public static void toastNotification(Context context,CharSequence text){ // for forgot password link sent
        //shows toast notification below the screen
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public static int calculateRisk(int age, String vaccination, boolean tobacco, String gender){
        int ageVal,vacVal,tobaccoVal,genderVal;
        if (age>=64)
            ageVal=4;
        else if (age>=54)
            ageVal=3;
        else if(age>=34)
            ageVal=2;
        else if(age>=25)
            ageVal=1;
        else
            ageVal=0;
        if(vaccination.equals("Not vaccinated"))
            vacVal=2;
        else if(vaccination.equals("1 dose"))
            vacVal=1;
        else
            vacVal=0;
        if(tobacco)
            tobaccoVal=1;
        else
            tobaccoVal=0;
        if(gender.equals("Male"))
            genderVal=0;
        else
            genderVal=1;
        double result = (0.15 *ageVal) + (0.37*vacVal) + (0.09*tobaccoVal) - (0.17*genderVal);
        double _result = ((result*100)+17)*0.62;
        return (int) _result;

    }
    public static void getBlogs(){
        Blog.list = new ArrayList<Blog>();

        // getting blogs from db to local
        Query query = Database.database.child("blogs");
        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                String json = String.valueOf(task.getResult().getValue());
                String[] blogs = json.split("\\{");
                for(String s:blogs){
                    if(!s.equals("[null, ")){
                        Log.d("BLOG------", "Inserted Blogs");
                        int id = Integer.parseInt(getFromJSON(s,"id"));
                        String title = getFromJSON(s,"title");
                        String img_url = s.split("url=")[1].split("\\}")[0];
                        Log.d("BLOG", getFromJSON(s,"url"));
                        String content = s.split("content=")[1].split(", url=")[0]; // since blog content may have many comma's inside it ..
                        String createdAt = getFromJSON(s,"createdAt");
                        Blog b = new Blog(id, createdAt,title,content,img_url);
                        }
                    }
                    Collections.reverse(Blog.list); // reversing the blog list for getting the latest blogs first.

            }
        });
    }
    public static void getLocations(){
        Database.locations = new ArrayList<LatLng>();
        Query query = Database.database.child("locations");
        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase----------------", "Error getting data", task.getException());
                }
                else {
                    String json = String.valueOf(task.getResult().getValue());
                    String[] locs = json.split("\\}");
                    for(String s:locs){
                            double latit = Double.parseDouble(getFromJSON(s,"latitude"));
                            double longt = Double.parseDouble(getFromJSON(s,"longitude"));
                            LatLng l = new LatLng(latit,longt);
                            Database.locations.add(l);
                    }
                }
            }
        });
    }

    public static void moveToPage(Activity current,Class to,boolean removeHistory){
        Intent intent = new Intent(current, to);
        if (removeHistory)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        current.startActivity(intent);

        if (removeHistory)
            current.finish();
    }

    public static void defineLaunchScreen(Activity currentActivity){
        Database.user = Database.auth.getCurrentUser();
        Operations.getBlogs();
        Query query = Database.database.child("users").orderByChild("id").equalTo(Database.user.getUid());
        query.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase----------------", "Error getting data", task.getException());
                }
                else {
                    String json = String.valueOf(task.getResult().getValue()).substring(30);
                    int profileLevel = Integer.parseInt(getFromJSON(json,"profileLevel"));
                    String email = getFromJSON(json,"email");
                    String pass = getFromJSON(json,"password");
                    User.me = new User(Database.user.getUid(),email,pass,null,0,0,false,null,null);
                    if(profileLevel==0){ //  no name, age , gender, --just have mail and pass
                        Operations.moveToPage(currentActivity, CreateProfileActivity.class,true);
                    }else if(profileLevel==1){ // no blood type, tobacco, vaccination, --- just have mail, pass, name
                        setProfile(json);
                        Operations.moveToPage(currentActivity, CompleteProfileActivity.class,true);
                    }else{ // full profile
                        setProfile(json);
                        String strTobacco = getFromJSON(json,"tobacco");
                        User.me.tobacco= strTobacco.equals("true");
                        String vaccination = getFromJSON(json,"vaccination");
                        String blood = getFromJSON(json,"blood");
                        User.me.setBlood(blood);
                        User.me.setVaccination(vaccination);
                        Operations.getLocations();
                        Operations.moveToPage(currentActivity,HomeActivity.class,true);
                    }

                }
            }
        });

    }
    public static void setProfile(String json){
        String name = getFromJSON(json,"name");
        String gender = getFromJSON(json,"gender");
        int age = Integer.parseInt(getFromJSON(json,"age"));
        User.me.setName(name);
        User.me.setGender(gender);
        User.me.setAge(age);
    }
    public static LatLng createRandomLocation(LatLng point, int radius) {

        List<LatLng> randomPoints = new ArrayList<>();
        List<Float> randomDistances = new ArrayList<>();
        Location myLocation = new Location("");
        myLocation.setLatitude(point.latitude);
        myLocation.setLongitude(point.longitude);

        //This is to generate 10 random points
        for(int i = 0; i<10; i++) {
            double x0 = point.latitude;
            double y0 = point.longitude;

            Random random = new Random();

            // Convert radius from meters to degrees
            double radiusInDegrees = radius / 111000f;

            double u = random.nextDouble();
            double v = random.nextDouble();
            double w = radiusInDegrees * Math.sqrt(u);
            double t = 2 * Math.PI * v;
            double x = w * Math.cos(t);
            double y = w * Math.sin(t);

            // Adjust the x-coordinate for the shrinking of the east-west distances
            double new_x = x / Math.cos(y0);

            double foundLatitude = new_x + x0;
            double foundLongitude = y + y0;
            LatLng randomLatLng = new LatLng(foundLatitude, foundLongitude);
            randomPoints.add(randomLatLng);
            Location l1 = new Location("");
            l1.setLatitude(randomLatLng.latitude);
            l1.setLongitude(randomLatLng.longitude);
            randomDistances.add(l1.distanceTo(myLocation));
        }
        //Get nearest point to the centre
        int indexOfNearestPointToCentre = randomDistances.indexOf(Collections.min(randomDistances));
        return randomPoints.get(indexOfNearestPointToCentre);
    }

}
