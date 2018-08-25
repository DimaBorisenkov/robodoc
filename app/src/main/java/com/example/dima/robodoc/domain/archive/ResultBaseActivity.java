package com.example.dima.robodoc.domain.archive;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.utils.CustomScrollView;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ResultBaseActivity extends Activity {
    private TextView name, age, address, history;
    private long id;
    private Realm realm;
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

        name.setText(patient.getName());
        age.setText(String.valueOf(patient.getAge()));
        address.setText(patient.getAddress());
        history.setText(patient.getHistory());



    }

}
