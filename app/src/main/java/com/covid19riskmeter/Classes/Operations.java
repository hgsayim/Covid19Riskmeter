package com.covid19riskmeter.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.covid19riskmeter.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Operations {
    public static int calculated_risk;
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
        if(tobacco==true)
            tobaccoVal=1;
        else
            tobaccoVal=0;
        if(gender.equals("Male"))
            genderVal=0;
        else
            genderVal=1;
        double result = (0.15 *ageVal) + (0.37*vacVal) + (0.09*tobaccoVal) - (0.17*genderVal);
        double _result = result*100*0.7;
        if(_result<0)
            _result=0;
        return (int) _result;

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
