package com.example.dima.robodoc.domain.archive;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AddPatientActivity extends AppCompatActivity implements AddPatientContract.View {
    private Realm realmFirst, realmSecond;
    private EditText name, address, history;
    private TextView dataBirth;
    private Button btnConfirm, btnClear;
    private String type, age;
    private Patient patient;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int patientDay, patientMonth, patientYear;
    private AddPatientContract.Presenter presenter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        RealmConfiguration configurationFirst = new RealmConfiguration.Builder().name("firstrealm.realm").build();
        realmFirst = Realm.getInstance(configurationFirst);
        RealmConfiguration configurationSecond = new RealmConfiguration.Builder().name("secondrealm.realm").build();
        realmSecond = Realm.getInstance(configurationSecond);

        name = findViewById(R.id.nameEdit);
        dataBirth = findViewById(R.id.dataBirthEdit);
        address = findViewById(R.id.addressEdit);
        history = findViewById(R.id.historyEdit);
        btnClear = findViewById(R.id.clearBtn);
        btnConfirm = findViewById(R.id.addBtn);
        presenter = new AddPatientPresenter();

        final EditText[] editTexts = {name, address, history};

        dataBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddPatientActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                patientMonth = month;
                patientDay = day;
                patientYear = year;
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                dataBirth.setText(date);
            }
        };


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (EditText temp : editTexts) temp.setText("");
                dataBirth.setText("Дата нарождення");
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePatient(editTexts);
                finish();
            }
        });

    }

    @Override
    public void setValues() {
        type = getIntent().getStringExtra("type");
        if (type.equals("result")) {
            patient = realmFirst.where(Patient.class).equalTo("id", getIntent().getLongExtra("id", 0)).findFirst();
            name.setText(patient.getName());
            StringBuilder stringBuilder = new StringBuilder();
            if (patient.getDiseases() != null && patient.getDiseases().size() > 0) {
                for (Disease temp : patient.getDiseases()) {
                    stringBuilder.append(temp.getName());
                    stringBuilder.append("\n");
                }
            }
            if (patient.getBlood() != null && patient.getBlood().size() > 0) {
                for (Blood temp : patient.getBlood()) {
                    stringBuilder.append(temp.getName() + ": " + temp.getValue());
                    stringBuilder.append("\n");
                }
            }
            history.setText(stringBuilder);
        }

        if (type.equals("item")) {
            long id = getIntent().getLongExtra("id", 0);
            patient = realmSecond.where(Patient.class).equalTo("id", id).findFirst();
            name.setText(patient.getName());
            age = String.valueOf(patient.getAge());
            dataBirth.setText(age);
            address.setText(patient.getAddress());
            history.setText(patient.getHistory());
        }

    }

    @Override
    public void savePatient(EditText... editTexts) {
        Patient newPatient = presenter.createPatient(editTexts, patientYear, patientMonth, patientDay, age);

        if (!type.equals("item")) {
            Number current = realmSecond.where(Patient.class).max("id");
            long nextId;
            if (current == null) nextId = 1;
            else nextId = current.intValue() + 1;
            newPatient.setId(nextId);
        } else newPatient.setId(patient.getId());


        realmSecond.beginTransaction();
        patient = realmSecond.copyToRealmOrUpdate(newPatient);
        realmSecond.commitTransaction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setValues();
    }
}

