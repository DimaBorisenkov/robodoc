package com.example.dima.robodoc.domain.archive;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.utils.CustomScrollView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ResultBaseActivity extends Activity {
    private TextView name, age, address, history;
    private long id;
    private Realm realm;
    private Button edit, delete;
    private Patient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_base);

        CustomScrollView myScrollView = findViewById(R.id.myScroll);
        /*myScrollView.setEnableScrolling(false); // disable scrolling*/
        myScrollView.setEnableScrolling(true); // enable scrolling

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("secondrealm.realm").build();
        realm = Realm.getInstance(realmConfiguration);

        id = getIntent().getLongExtra("id", 0);
        patient = realm.where(Patient.class).equalTo("id", id).findFirst();

        name = findViewById(R.id.nameTextView);
        age = findViewById(R.id.ageTextView);
        address = findViewById(R.id.addressTextView);
        history = findViewById(R.id.historyTextView);

        name.setText("Ім'я: " + patient.getName());
        age.setText("Вік: " + String.valueOf(patient.getAge()));
        address.setText("Адреса: " + patient.getAddress());
        history.setText("Історія хвороби:" + "\n" + patient.getHistory());

        delete = findViewById(R.id.deleteBtn);
        edit = findViewById(R.id.editBtn);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                patient.deleteFromRealm();
                realm.commitTransaction();
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultBaseActivity.this, AddPatientActivity.class);
                intent.putExtra("type", "item");
                intent.putExtra("id", patient.getId());
                startActivity(intent);
            }
        });

    }

}
