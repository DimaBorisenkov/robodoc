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
import java.util.GregorianCalendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AddPatientActivity extends AppCompatActivity {
    private Realm realmFirst, realmSecond;
    private EditText name, address, history;
    private TextView dataBirth;
    private Button btnConfirm, btnClear;
    private String type;
    private Patient patient;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int patientDay, patientMonth, patientYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        RealmConfiguration configurationFirst = new RealmConfiguration.Builder().name("firstrealm.realm").build();
        realmFirst = Realm.getInstance(configurationFirst);


        name = findViewById(R.id.nameEdit);
        dataBirth = findViewById(R.id.dataBirthEdit);
        address = findViewById(R.id.addressEdit);
        history = findViewById(R.id.historyEdit);
        btnClear = findViewById(R.id.clearBtn);
        btnConfirm = findViewById(R.id.addBtn);

        final EditText[] editTexts = {name, address, history};

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
                createPatient(editTexts);
                finish();
            }
        });

    }

    void createPatient(EditText... editTexts) {
        RealmConfiguration configurationSecond = new RealmConfiguration.Builder().name("secondrealm.realm").build();
        realmSecond = Realm.getInstance(configurationSecond);
        patient = new Patient();

        patient.setName(editTexts[0].getText().toString());
        patient.setAddress(editTexts[1].getText().toString());
        patient.setHistory(editTexts[2].getText().toString());

        patient.setAge(createAge());

        realmSecond.beginTransaction();
        Number current = realmSecond.where(Patient.class).max("id");
        long nextId;
        if (current == null) nextId = 1;
        else nextId = current.intValue() + 1;
        patient.setId(nextId);

        realmSecond.copyToRealmOrUpdate(patient);
        realmSecond.commitTransaction();

    }


    int createAge() {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int year = gregorianCalendar.get(Calendar.YEAR);
        int month = gregorianCalendar.get(Calendar.MONTH);
        int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);

        if (patientYear != 0 && patientMonth != 0 && patientDay != 0) {
            gregorianCalendar.set(patientYear, patientMonth, patientDay);
            int age = year - gregorianCalendar.get(Calendar.YEAR);

            if ((month < gregorianCalendar.get(Calendar.MONTH))
                    || ((month == gregorianCalendar.get(Calendar.MONTH)) &&
                    (day < gregorianCalendar.get(Calendar.DAY_OF_MONTH)))) {
                --age;
            }

            return age;

        } else return 0;
    }
}
