package com.example.dima.robodoc.domain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;

public class MedicinesActivity extends AppCompatActivity {
    private TextView warning, medicines;
    private Button button;
    private Patient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);


        warning = findViewById(R.id.warning);
        medicines = findViewById(R.id.medicines);
        button = findViewById(R.id.buttonMapActivity);

        warning.setText(R.string.warning);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicinesActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}
