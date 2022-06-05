package com.covid19riskmeter;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.covid19riskmeter.Classes.Operations;

public class RiskmeterActivity extends AppCompatActivity {
    TextView txtRisk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int risk = Operations.calculated_risk;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riskmeter);
        txtRisk = (TextView) findViewById(R.id.txtRisk);
        txtRisk.setText(risk +"%");
        if(risk>70)
            txtRisk.setTextColor(Color.RED);
        else if(risk>50)
            txtRisk.setTextColor(Color.parseColor("#eb8c34"));
        else if(risk>25)
            txtRisk.setTextColor(Color.parseColor("#3483eb"));
        else
            txtRisk.setTextColor(Color.GREEN);
    }
}