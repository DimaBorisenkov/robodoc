package com.example.dima.robodoc.domain.result;

import com.example.dima.robodoc.data.models.Patient;

public interface ResultContract {
    interface View {
        void getValues();

        void setValues();
    }

    interface Presenter {

        void setView(View view);
        StringBuilder createDiseases(Patient patient);
    }
}
