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
    private String type;
    private StringBuilder diseases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getValues();
        patientName = findViewById(R.id.textViewPatientName);
        patientState = findViewById(R.id.textViewPatientState);
        patientDiseases = findViewById(R.id.textViewPatientDiseases);
        patientSex = findViewById(R.id.imageViewPatientSex);

        diseases = new StringBuilder();

        patientName.setText(patient.getName());

        if (patient.isState()) {
            patientState.setText("Здоровий");
            patientState.setBackgroundColor(GREEN);
            patientName.setBackgroundColor(GREEN);
        } else {
            patientState.setText("Хворий");
            patientName.setBackgroundColor(RED);
            patientState.setBackgroundColor(RED);
        }

        if (patient.getDiseases() != null) {
            diseases.append("Є підозри на такі хвороби:");
            diseases.append("\n");
            for (Disease temp : patient.getDiseases()) {
                diseases.append("• " + temp.getName());
                diseases.append("\n");
            }
        } else {
            diseases.append("Хвороб немає");
        }
        patientDiseases.setText(diseases);

        if (patient.isGender()) {
            patientSex.setImageResource(R.drawable.man_icon);
        } else {
            patientSex.setImageResource(R.drawable.woman_icon);
        }


    }

    void getValues() {
        type = getIntent().getStringExtra("type");
        patient = (Patient) getIntent().getSerializableExtra("patient");
    }
}
