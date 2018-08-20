package com.example.dima.robodoc.domain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Patient;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class EditActivity extends AppCompatActivity {

    private RadioGroup gender;
    private TextView text;
    private Button button;
    private ImageView user;
    private boolean genderBoolean;
    private EditText name, hb, rbc;
    private long patientId;
    private int selectedId;
    private Realm realm;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_form);

        setGender();

        patientId = getIntent().getLongExtra("id", 0);
        realm = Realm.getDefaultInstance();
        patient = realm.where(Patient.class).equalTo("id", patientId).findFirst();

        name = findViewById(R.id.name);
        hb = findViewById(R.id.hb);
        rbc = findViewById(R.id.rbc);
        button = findViewById(R.id.buttonConfirm);

        name.setText(patient.getName().trim());

        if(patient.isGender()) {
            text.setText("Обрана стать - чоловік");
            user.setImageResource(R.drawable.man_icon_big);
            genderBoolean = true;
        } else {
            text.setText("Обрана стать - жінка");
            user.setImageResource(R.drawable.woman_icon_big);
            genderBoolean = false;
        }



        for(Blood temp : patient.getBlood()){
            switch (temp.getName()){
                case "HB":
                    hb.setText("" + temp.getValue());
                    break;
                case "RBC":
                    rbc.setText("" + temp.getValue());
                    break;
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                patient.setName(name.getText().toString().trim());
                patient.setGender(genderBoolean);


                realm.commitTransaction();
                finish();
            }
        });

    }
















    private void setGender() {
        gender = findViewById(R.id.radioSex);
        user = findViewById(R.id.user);
        text = findViewById(R.id.selectGender);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = gender.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radioMale:
                        text.setText("Обрана стать - чоловік");
                        user.setImageResource(R.drawable.man_icon_big);
                        genderBoolean = true;
                        break;
                    case R.id.radioFemale:
                        text.setText("Обрана стать - жінка");
                        user.setImageResource(R.drawable.woman_icon_big);
                        genderBoolean = false;
                        break;

                }
            }
        });

    }
}