package com.example.dima.robodoc.domain.history;

import com.example.dima.robodoc.data.models.Patient;
import com.example.dima.robodoc.domain.form.FormContract;

import java.util.List;

public interface HistoryContract {
    interface View {
        void setHistory(List<Patient> patients);
    }

    interface Presenter {
        void setView(HistoryContract.View view);


    }
}
