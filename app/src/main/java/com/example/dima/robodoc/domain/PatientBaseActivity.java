package com.example.dima.robodoc.domain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dima.robodoc.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class PatientBaseActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_patient_activity);

        /*RealmConfiguration configSecond = new RealmConfiguration.Builder().name("secondrealm.realm").build();
        realm = Realm.getInstance()*/
    }


}
