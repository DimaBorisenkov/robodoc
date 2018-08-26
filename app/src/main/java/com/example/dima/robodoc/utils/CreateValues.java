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
                }
                break;

            case "Недостаток вітамінів та проблеми з шлунком":
                bloodRealmList.add(new Blood("MCHC", randomDouble(1.16, 5)));
                break;

            case "Анемія":
                if (gender) {
                    bloodRealmList.add(new Blood("HB", randomDouble(161, 220)));
                    bloodRealmList.add(new Blood("ESR", randomDouble(11, 20)));
                } else {
                    bloodRealmList.add(new Blood("HB", randomDouble(141, 200)));
                    bloodRealmList.add(new Blood("ESR", randomDouble(16, 25)));
                }
                bloodRealmList.add(new Blood("MCHC", randomDouble(0.1, 0.84)));
                bloodRealmList.add(new Blood("RTC", randomDouble(0.1, 0.2)));
                bloodRealmList.add(new Blood("PLT", randomDouble(100, 175)));
                bloodRealmList.add(new Blood("LYM", randomDouble(5, 17)));
                bloodRealmList.add(new Blood("MON", randomDouble(0.1, 1.8)));
                bloodRealmList.add(new Blood("WBC", randomDouble(1, 3.8)));
                break;

            case "Новоутворення":
                if (gender) bloodRealmList.add(new Blood("RBC", randomDouble(5.2, 8)));
                else bloodRealmList.add(new Blood("RBC", randomDouble(4.8, 7)));
                bloodRealmList.add(new Blood("WBC", randomDouble(10, 20)));
                bloodRealmList.add(new Blood("MCHC", randomDouble(1.2, 3)));
                break;

            case "Захворювання нирок":
                if (gender) {
                    bloodRealmList.add(new Blood("RBC", randomDouble(5.2, 8)));
                    bloodRealmList.add(new Blood("ESR", randomDouble(11, 20)));
                } else {
                    bloodRealmList.add(new Blood("RBC", randomDouble(4.8, 7)));
                    bloodRealmList.add(new Blood("ESR", randomDouble(16, 25)));
                }
                bloodRealmList.add(new Blood("RTC", randomDouble(0.05, 0.19)));
                break;

            case "Крововтрата":
                if (gender) bloodRealmList.add(new Blood("RBC", randomDouble(5.2, 8)));
                else bloodRealmList.add(new Blood("RBC", randomDouble(4.8, 7)));
                bloodRealmList.add(new Blood("RTC", randomDouble(1.3, 3)));
                break;
            case "Лейкоз":
                bloodRealmList.add(new Blood("MON", randomDouble(9, 15)));
                bloodRealmList.add(new Blood("PLT", randomDouble(320, 420)));
                bloodRealmList.add(new Blood("LYM", randomDouble(40, 70)));
                bloodRealmList.add(new Blood("EOS", randomDouble(5, 10)));
                bloodRealmList.add(new Blood("WBC", randomDouble(1, 3.8)));
                break;

            case "Вірусне захворювання":
                bloodRealmList.add(new Blood("LYM", randomDouble(40, 70)));
                bloodRealmList.add(new Blood("MON", randomDouble(9, 15)));
                bloodRealmList.add(new Blood("WBC", randomDouble(1, 3.8)));
                break;

            case "Ауто-імунні захворювання":
                if(gender) bloodRealmList.add(new Blood("ESR", randomDouble(11, 20)));
                else bloodRealmList.add(new Blood("ESR", randomDouble(16, 25)));
                bloodRealmList.add(new Blood("MON", randomDouble(9, 15)));
                bloodRealmList.add(new Blood("EOS", randomDouble(5, 10)));
                bloodRealmList.add(new Blood("PLT", randomDouble(100, 170)));
                bloodRealmList.add(new Blood("LYM", randomDouble(5, 17)));
                break;

            case "Захворювання печінки":
                if(gender) bloodRealmList.add(new Blood("ESR", randomDouble(11, 20)));
                else bloodRealmList.add(new Blood("ESR", randomDouble(16, 25)));
                break;

            case "Тиф":
                bloodRealmList.add(new Blood("WBC", randomDouble(1, 3.8)));
                break;

        }

        return bloodRealmList;
    }

    private double randomDouble(double min, double max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random random = new Random();
        String str = String.valueOf(min + (max - min) * random.nextDouble());
        if (str.length() > 5) {
            char[] symbols = str.toCharArray();
            str = "";
            for (int i = 0; i < 5; i++) str += symbols[i];
        }
        return Double.parseDouble(str);
    }

}
