package com.example.dima.robodoc.domain.archive;

import android.widget.EditText;

import com.example.dima.robodoc.data.models.Patient;

public interface AddPatientContract {
    interface View {
        void setValues();

        void savePatient(EditText... editTexts);
    }

    interface Presenter{
        Patient createPatient(EditText[] editTexts, int year, int month, int day, String age);

        int createAge(int year, int month, int day, String age);
    }
}
