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
import com.example.dima.robodoc.domain.history.HistoryPresenter;
import com.example.dima.robodoc.domain.history.PatientsAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class PatientBaseActivity extends AppCompatActivity {
    private Realm realm;
    private RecyclerView recyclerView;
    private PatientBaseAdapter patientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_patient_activity);


        RealmConfiguration configSecond = new RealmConfiguration.Builder().name("secondrealm.realm").build();
        realm = Realm.getInstance(configSecond);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Patient> arrayList = new ArrayList<>();
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));
        arrayList.add(new Patient("dfs", "dfsdf", "dfs", 5));

        patientsAdapter = new PatientBaseAdapter(arrayList, this, recyclerView);
        recyclerView.setAdapter(patientsAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientBaseActivity.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });
    }


}
