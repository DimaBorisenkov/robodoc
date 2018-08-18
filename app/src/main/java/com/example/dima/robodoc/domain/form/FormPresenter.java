package com.example.dima.robodoc.domain.form;


import android.widget.EditText;

import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;
import com.example.dima.robodoc.data.models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;

public class FormPresenter implements FormContract.Presenter{

    @Override
    public boolean checkName(String name) {
        if (name.trim().length() == 0) return false;
        else return true;
    }

    @Override
    public boolean checkFields(EditText[] editTexts) {
        boolean check = true;
        for (EditText temp : editTexts) {
            if (temp.getText().toString().trim().length() != 0) {
                check = false;
            }
        }
        return check;
    }

    @Override
    public ArrayList<Blood> createBloodArrayList(EditText [] editTexts) {
        ArrayList<Blood> bloodArrayList = new ArrayList<>();
        for (EditText temp : editTexts) {
            if (temp.getText().length() > 0) {
                String name = createName(temp.getHint().toString());
                bloodArrayList.add(new Blood(name.trim(), Double.valueOf(temp.getText().toString())));
            }
        }
        return bloodArrayList;

    }

    @Override
    public Patient createPatient(Patient patient, Blood blood, RealmList<Disease> diseases) {
        patient.setState(true);

        ArrayList<String> values = blood.getBlood();
        StringBuilder stringBuilder = new StringBuilder();
        for(String temp : values){
            stringBuilder.append(temp);
            stringBuilder.append("\n");
        }
        patient.setBlood(stringBuilder.toString());


        for(Boolean temp : blood.getNorma()){
            if(!temp){
                patient.setState(false);
            }
        }

        if (!patient.isState()) {
            if (diseases.size() != 0) {
                patient.setDiseases(diseases);
            } else {
                patient.setDiseases(new RealmList<Disease>());
            }
        }
        return patient;

    }
    @Override
    public String createName(String hint) {
        String name = "";
        char[] nameSymbols = hint.toCharArray();
        for (int i = 0; i < 4; i++) {
            name += nameSymbols[i];
        }
        return name;
    }
}
