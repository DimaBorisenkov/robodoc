package com.example.dima.robodoc.domain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

public class ResultActivity extends AppCompatActivity {
    private TextView patientName, patientState, patientDiseases;
    private Patient patient;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getValues();

        patientName = findViewById(R.id.patientName);
        patientState = findViewById(R.id.patientState);
        patientDiseases = findViewById(R.id.patientDiseases);

        patientName.setText(patient.getName());
        patientState.setText(String.valueOf(patient.isState()));

        if(patient.getDiseases() != null){
            String diseases = "";
            for(Disease temp : patient.getDiseases()){
                diseases += temp.getName() + " ";
            }
            patientDiseases.setText(diseases);
        }



        /*textView = findViewById(R.id.textView);
        textView.setText(patient.getName());*/


    }

    void getValues(){
        type = getIntent().getStringExtra("type");
        patient = (Patient) getIntent().getSerializableExtra("patient");
    }
}
