package com.example.dima.robodoc.domain.archive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.data.realm.RealmHelper;
import com.example.dima.robodoc.domain.history.HistoryPresenter;
import com.example.dima.robodoc.domain.history.PatientsAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;


public class PatientBaseActivity extends AppCompatActivity {
    private Realm realm;
    private RecyclerView recyclerView;
    private PatientBaseAdapter patientsAdapter;
    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_patient_activity);


        RealmConfiguration configSecond = new RealmConfiguration.Builder().name("secondrealm.realm").build();
        realm = Realm.getInstance(configSecond);

        realmHelper = new RealmHelper(realm);
        realmHelper.retrieveFromDB();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* ArrayList<Patient> arrayList = new ArrayList<>();
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));*/

        patientsAdapter = new PatientBaseAdapter(realmHelper.refresh(), this, recyclerView);
        recyclerView.setAdapter(patientsAdapter);

        RealmChangeListener realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                refresh();
            }
        };

        realm.addChangeListener(realmChangeListener);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientBaseActivity.this, AddPatientActivity.class);
                intent.putExtra("type", "base");
                startActivity(intent);
            }
        });
    }


    public void refresh(){
        patientsAdapter = new PatientBaseAdapter(realmHelper.refresh(), this, recyclerView);
        recyclerView.setAdapter(patientsAdapter);
    }


}
