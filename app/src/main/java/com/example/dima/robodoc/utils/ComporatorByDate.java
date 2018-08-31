package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Patient;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ComporatorByDate implements Comparator<Patient> {
    @Override
    public int compare(Patient patientFirst , Patient patientSecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
        try {
            Date dateOne = simpleDateFormat.parse(patientFirst.getDate());
            Date dateTwo = simpleDateFormat.parse(patientSecond.getDate());
            return dateTwo.compareTo(dateOne);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
