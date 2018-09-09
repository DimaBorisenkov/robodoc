package com.example.dima.robodoc.domain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class MedicinesActivity extends AppCompatActivity {
    private TextView warning, medicines;
    private Button button;
    private Patient patient;
    private Realm realm;
    private long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);


        warning = findViewById(R.id.warning);
        medicines = findViewById(R.id.medicines);
        button = findViewById(R.id.buttonMapActivity);

        id = getIntent().getLongExtra("id", 0);
        RealmConfiguration configFirst = new RealmConfiguration.Builder().name("firstrealm.realm").build();
        realm = Realm.getInstance(configFirst);

        try {
            patient = realm.where(Patient.class).equalTo("id", id).findFirst();
        } catch (Exception e) {
            Toast.makeText(this, "Помилка", Toast.LENGTH_SHORT).show();
        }

        StringBuilder stringBuilder = new StringBuilder();
        RealmList<Disease> diseases = patient.getDiseases();
        for (Disease temp : diseases) {
            RealmList<String> medicinesRealmList = temp.getMedicinesRealmList();
            StringBuilder medicinesStringBuilder = new StringBuilder();
            for (String medicinesTemp : medicinesRealmList)
                medicinesStringBuilder.append(medicinesTemp.toLowerCase() + ", ");
            char[] medicinesChars = medicinesStringBuilder.toString().toCharArray();
            String medicinesFinal = "";
            for (int i = 0; i < medicinesChars.length - 2; i++) {
                medicinesFinal += medicinesChars[i];
            }
            if (temp.getMedicinesRealmList().size() > 0) {
                stringBuilder.append(temp.getName() + ": " + medicinesFinal);
                stringBuilder.append("\n");
            }
        }

        medicines.setText(stringBuilder.toString());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicinesActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}
