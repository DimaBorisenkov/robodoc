package com.example.dima.robodoc.domain.form;


import android.content.Context;
import android.widget.EditText;

import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import java.util.ArrayList;

import io.realm.RealmList;

public interface FormContract {
    interface View {
        void setGender();

        void transmitValues(Patient patient);




    }

    interface Presenter {
        String createName(String hint);

        boolean checkName(String name);

        boolean checkFields(EditText[] editTexts);

        ArrayList<Blood> createBloodArrayList(EditText [] editTexts);

        Patient createPatient(Patient patient, Blood blood, RealmList<Disease> diseases);


    }
}
