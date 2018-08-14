package com.example.dima.robodoc.domain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;

public class ResultActivity extends AppCompatActivity {
    TextView textView;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getValues();

        textView = findViewById(R.id.textView);
        textView.setText(patient.getName());
    }

    void getValues(){
        patient = (Patient) getIntent().getSerializableExtra("patient");
    }
}
