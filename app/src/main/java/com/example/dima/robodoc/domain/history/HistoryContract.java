package com.example.dima.robodoc.domain.history;

import com.example.dima.robodoc.data.models.Patient;

import java.util.List;

public interface HistoryContract {
    interface View {

    }

    interface Presenter {
        void setView(HistoryContract.View view);


    }
}
