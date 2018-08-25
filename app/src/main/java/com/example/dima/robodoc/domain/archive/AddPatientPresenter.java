package com.example.dima.robodoc.domain.archive;

import android.widget.EditText;

import com.example.dima.robodoc.data.models.Patient;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class AddPatientPresenter implements AddPatientContract.Presenter {

    public Patient createPatient(EditText[] editTexts, int year, int month, int day, String age) {
        Patient newPatient = new Patient();
        newPatient.setName(editTexts[0].getText().toString());
        newPatient.setAddress(editTexts[1].getText().toString());
        newPatient.setHistory(editTexts[2].getText().toString());
        newPatient.setAge(createAge(year, month, day, age));
        return newPatient;
    }


    public int createAge(int patientYear, int patientMonth, int patientDay, String ageStr) {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int year = gregorianCalendar.get(Calendar.YEAR);
        int month = gregorianCalendar.get(Calendar.MONTH);
        int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);

        if (patientYear != 0 && patientMonth != 0 && patientDay != 0) {
            gregorianCalendar.set(patientYear, patientMonth, patientDay);
            int age = year - gregorianCalendar.get(Calendar.YEAR);

            if ((month < gregorianCalendar.get(Calendar.MONTH))
                    || ((month == gregorianCalendar.get(Calendar.MONTH)) &&
                    (day < gregorianCalendar.get(Calendar.DAY_OF_MONTH)))) {
                --age;
            }

            return age;

        } else if (ageStr != null) return Integer.parseInt(ageStr);
        else return 0;
    }
}
