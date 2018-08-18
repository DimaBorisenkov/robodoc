package com.example.dima.robodoc.data.realm;

import com.example.dima.robodoc.data.models.Patient;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;
    RealmResults<Patient> patients;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void retrieveFromDB(){
        patients = realm.where(Patient.class).findAll();
    }

    public ArrayList<Patient> refresh(){
        ArrayList<Patient> latest = new ArrayList<>();
        for(Patient temp : patients){
            latest.add(temp);
        }
        return latest;
    }
}
