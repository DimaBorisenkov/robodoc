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

public class ResultActivity extends AppCompatActivity implements ResultContract.View{
    private TextView patientName, patientState, patientDiseases;
    private ImageView patientSex;
    private Patient patient;
    private String type;
    private StringBuilder diseases;
    private ResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getValues();
        patientName = findViewById(R.id.textViewPatientName);
        patientState = findViewById(R.id.textViewPatientState);
        patientDiseases = findViewById(R.id.textViewPatientDiseases);
        patientSex = findViewById(R.id.imageViewPatientSex);

        presenter = new ResultPresenter();
        presenter.setView(this);
        diseases = presenter.createDiseases(patient);

        setValues();


    }

    @Override
    public void getValues() {
        type = getIntent().getStringExtra("type");
        patient = (Patient) getIntent().getSerializableExtra("patient");
    }

    @Override
    public void setValues() {
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

        if (patient.isGender()) {
            patientSex.setImageResource(R.drawable.man_icon);
        } else {
            patientSex.setImageResource(R.drawable.woman_icon);
        }

        patientDiseases.setText(diseases);


    }
}
