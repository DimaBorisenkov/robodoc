package com.example.dima.robodoc.domain;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.form.FormContract;
import com.example.dima.robodoc.domain.form.FormPresenter;
import com.example.dima.robodoc.utils.DiseaseDeterminant;
import com.example.dima.robodoc.utils.NormaDeterminant;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;

public class EditActivity extends AppCompatActivity {

    private RadioGroup gender;
    private TextView text;
    private Button buttonConfirm, buttonClear;
    private ImageView user;
    private boolean genderBoolean, checkGender = true;
    private EditText name, hb, rbc;
    private long patientId;
    private int selectedId;
    private Realm realm;
    private Patient patient;
    private FormContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_form);

        RealmConfiguration configFirst = new RealmConfiguration.Builder().name("firstrealm.realm").build();
        setGender();

        patientId = getIntent().getLongExtra("id", 0);
        realm = Realm.getInstance(configFirst);
        patient = realm.where(Patient.class).equalTo("id", patientId).findFirst();

        name = findViewById(R.id.name);
        hb = findViewById(R.id.hb);
        rbc = findViewById(R.id.rbc);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonClear = findViewById(R.id.buttonClear);

        presenter = new FormPresenter();

        name.setText(patient.getName().trim());

        if (patient.isGender()) {
            text.setText("Обрана стать - чоловік");
            user.setImageResource(R.drawable.man_icon_big);
            genderBoolean = true;
        } else {
            text.setText("Обрана стать - жінка");
            user.setImageResource(R.drawable.woman_icon_big);
            genderBoolean = false;
        }

        for (Blood temp : patient.getBlood()) {
            switch (temp.getName()) {
                case "HB":
                    hb.setText("" + temp.getValue());
                    break;
                case "RBC":
                    rbc.setText("" + temp.getValue());
                    break;
            }
        }

        final EditText[] editTexts = {hb, rbc};

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkName = presenter.checkName(name.getText().toString().trim());

                if (!checkGender)
                    Toast.makeText(EditActivity.this, "Будь ласка, оберіть стать", Toast.LENGTH_SHORT).show();
                if (!checkName)
                    Toast.makeText(EditActivity.this, "Будь ласка, введіть ім'я", Toast.LENGTH_SHORT).show();

                if (checkName && checkGender) {

                    Patient newPatient = new Patient();
                    newPatient.setId(patientId);
                    newPatient.setDate(patient.getDate());
                    newPatient.setName(name.getText().toString().trim());
                    newPatient.setGender(genderBoolean);
                    try {
                        Blood blood = new NormaDeterminant().check(presenter.createBloodArrayList(editTexts), genderBoolean);
                        RealmList<Disease> diseases = new DiseaseDeterminant().selectDisease(blood, EditActivity.this);
                        newPatient = presenter.createPatient(newPatient, blood, diseases);
                        realm.beginTransaction();
                        patient = realm.copyToRealmOrUpdate(newPatient);
                        realm.commitTransaction();
                        finish();
                    } catch (Exception e){
                        Toast.makeText(EditActivity.this, "Поле не повинно містити лише крапку", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setImageResource(R.color.back);
                checkGender = false;
                name.setText("");
                text.setText("Будь ласка, оберіть стать");
                for (EditText temp : editTexts) temp.setText("");
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
                        checkGender = true;
                        break;
                    case R.id.radioFemale:
                        text.setText("Обрана стать - жінка");
                        user.setImageResource(R.drawable.woman_icon_big);
                        genderBoolean = false;
                        checkGender = true;
                        break;

                }
            }
        });

    }
}