package com.covid19riskmeter.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.covid19riskmeter.CreateProfileActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Operations {

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

    public static void toastNotification(Context context,CharSequence text){
        //shows toast notification below the screen
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    public static double calculateRisk(String age, String vaccination, boolean tobacco, String gender){
        int age_int = Integer.parseInt(age);
        int ageVal,vacVal,tobaccoVal,genderVal;
        if (age_int>=64)
            ageVal=4;
        else if (age_int>=54)
            ageVal=3;
        else if(age_int>=34)
            ageVal=2;
        else if(age_int>=25)
            ageVal=1;
        else
            ageVal=0;
        if(vaccination=="Not vaccinated")
            vacVal=2;
        else if(vaccination=="1 dose")
            vacVal=1;
        else
            vacVal=0;
        if(tobacco==true)
            tobaccoVal=1;
        else
            tobaccoVal=0;
        if(gender=="Male")
            genderVal=0;
        else
            genderVal=1;
        return (0.15 *ageVal) + (0.37*vacVal) + (0.09*tobaccoVal) - (0.17*genderVal);

    }
    public static void moveToPage(Activity current,Class to,boolean removeHistory){
        Intent intent = new Intent(current, to);
        if (removeHistory)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        current.startActivity(intent);

        if (removeHistory)
            current.finish();
    }

}
