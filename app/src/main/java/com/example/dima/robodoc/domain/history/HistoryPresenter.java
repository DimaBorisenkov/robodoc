package com.example.dima.robodoc.domain.history;


/*import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.data.realm.RealmHelper;
import com.example.dima.robodoc.utils.ComporatorByDate;
import com.example.dima.robodoc.utils.ComporatorByState;

import java.util.Collections;

import io.realm.RealmResults;*/

public class HistoryPresenter implements HistoryContract.Presenter {
    HistoryContract.View view;


    @Override
    public void setView(HistoryContract.View view) {
        this.view = view;
    }
    /*@Override
    public RealmResults<Patient> sort(boolean b, RealmHelper realmHelper) {
        realmHelper.retrieveFromDB();
        RealmResults<Patient> patients = realmHelper.patients;
        if (b) {
            Collections.sort(patients, new ComporatorByDate());
        } else {
            Collections.sort(patients, new ComporatorByState());
        }
        return patients;
    }*/
}

