package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.covid19riskmeter.Classes.Operations;

public class CalculateActivity extends AppCompatActivity {

    Spinner spnGender,spnVac;
    Switch swTobacco;
    EditText txtAge;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        spnGender = (Spinner) findViewById(R.id.spnGender2);
        spnVac = (Spinner) findViewById(R.id.spnVaccination2);
        swTobacco = (Switch) findViewById(R.id.switchTobacco2);
        txtAge = (EditText) findViewById(R.id.txtAge2);
        txtInfo  = findViewById(R.id.txtCalculateInfo);

    }
    public void calculateRisk(View view){

        if(txtAge.getText().toString().equals(""))
            Operations.errorLabel(txtInfo,"Age can not be empty. Please fill all the blank",3);
        else{
            int age = Integer.parseInt(txtAge.getText().toString());
            String gender = spnGender.getSelectedItem().toString();
            String vac = spnVac.getSelectedItem().toString();
            boolean tobacco = swTobacco.isChecked();
            Operations.calculated_risk = Operations.calculateRisk(age,vac,tobacco,gender);
            Operations.moveToPage(this,RiskmeterActivity.class,false);
        }

    }
}