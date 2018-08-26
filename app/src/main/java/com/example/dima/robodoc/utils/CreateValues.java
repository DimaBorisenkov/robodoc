package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Blood;

import java.util.Random;

import io.realm.RealmList;

public class CreateValues {
    private RealmList<Blood> bloodRealmList;

    RealmList<Blood> createValues(String diseasesName, boolean gender) {
        bloodRealmList = new RealmList<>();

        switch (diseasesName) {
            case "Зневоднення":
                if (gender) bloodRealmList.add(new Blood("HB", randomDouble(161, 220)));
                else bloodRealmList.add(new Blood("HB", randomDouble(141, 200)));
                break;
            case "Згущення крові":
                if (gender) {
                    bloodRealmList.add(new Blood("HB", randomDouble(161, 220)));
                    bloodRealmList.add(new Blood("RBC", randomDouble(5.2, 8)));
                } else {
                    bloodRealmList.add(new Blood("HB", randomDouble(141, 200)));
                    bloodRealmList.add(new Blood("RBC", randomDouble(4.8, 7)));
                } break;
        }

        return bloodRealmList;
    }

    private double randomDouble(double min, double max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random random = new Random();
        String str = String.valueOf(min + (max - min) * random.nextDouble());
        if(str.length() > 5) {
            char [] symbols = str.toCharArray();
            str = "";
            for(int i = 0; i < 5; i++) str += symbols[i];
        }
        return Double.parseDouble(str);
    }

}
