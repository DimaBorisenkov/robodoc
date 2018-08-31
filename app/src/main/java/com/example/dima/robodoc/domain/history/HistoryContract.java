package com.example.dima.robodoc.domain.history;


import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.data.realm.RealmHelper;

import io.realm.RealmResults;

public interface HistoryContract {
    interface View {
        void refresh();

    }

    interface Presenter {
        void setView(HistoryContract.View view);

       // RealmResults<Patient> sort (boolean comparatorBool, RealmHelper realmHelper);


    }
}
