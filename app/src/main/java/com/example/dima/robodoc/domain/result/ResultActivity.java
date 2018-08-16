package com.example.dima.robodoc.domain.result;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class ResultActivity extends AppCompatActivity {
    private TextView patientName, patientState, patientDiseases;
    private ImageView patientSex;
    private Patient patient;
    private String type, diseases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getValues();

        patientName = findViewById(R.id.textViewPatientName);
        patientState = findViewById(R.id.textViewPatientState);
        patientDiseases = findViewById(R.id.textViewPatientDiseases);
        patientSex = findViewById(R.id.imageViewPatientSex);

        patientName.setText(patient.getName());
        if(patient.isState()){
        patientState.setText("здоровий");
        patientState.setBackgroundColor(GREEN);
        patientName.setBackgroundColor(GREEN);
        }else{
            patientState.setText("хворий");
            patientName.setBackgroundColor(RED);
            patientState.setBackgroundColor(RED);
        }

        if(patient.getDiseases() != null){
            diseases = "Хвороби : ";
            for(Disease temp : patient.getDiseases()){
                diseases += temp.getName() + "; ";
            }

        }else{
            diseases = "Хвороб немає!";
        }
        patientDiseases.setText(diseases);

        if(patient.isGender()){
            patientSex.setImageResource(R.drawable.man_icon);
        }


    }

    void getValues(){
        type = getIntent().getStringExtra("type");
        patient = (Patient) getIntent().getSerializableExtra("patient");
    }
}
