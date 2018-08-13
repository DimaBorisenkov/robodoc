package com.example.dima.robodoc.domain;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;

public class ResultActivity extends AppCompatActivity {
  //  private Patient patient;
    private TextView name_of_patient;
    private TextView list_of_patient_diseases;
    private TextView data;
    private ImageView patient_diseases_visualization;

   /* public ResultActivity(Patient patient) {
        this.patient = patient;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        name_of_patient = findViewById(R.id.textViewNAME_OF_PATIENT);
        list_of_patient_diseases = findViewById(R.id.textViewLIST_OF_DISEASES);
        data = findViewById(R.id.textViewDATA);
        patient_diseases_visualization = findViewById(R.id.imageViewDISEASES_VISUALIZATION);
       // name_of_patient.setText(patient.getName());
       // list_of_patient_diseases.setText("Можливi хвороби : "+patient.getDiseases());
        //data.setText("Last seen " + patient.getDate().toString());
        //a trouble with picture !!!I can't change it!!!
        //  patient_diseases_visualization = findViewById(patient)
    }
}
