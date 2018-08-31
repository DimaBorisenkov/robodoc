package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Blood;

import java.util.ArrayList;

import io.realm.RealmList;

public class NormaDeterminant {

    private String bloodName = "";
    private double bloodValue = 0;
    private Blood blood = new Blood();
    private RealmList<Boolean> norma = new RealmList<>();

    public Blood check(ArrayList<Blood> bloodArrayList, boolean gender) {

        RealmList<Blood> values = new RealmList<>();
        for (Blood temp : bloodArrayList) {
            bloodName = temp.getName();
            bloodValue = temp.getValue();
            selectNorma(gender);
            values.add(new Blood(bloodName, bloodValue));
        }

        blood.setNorma(norma);
        blood.setBlood(values);
        return blood;

    }

    void selectNorma(boolean gender) {
        switch (bloodName) {
            case "HB":
                if (gender) {
                    if (bloodValue >= 130 && bloodValue <= 160) {
                        blood.setHBNorma(true);
                        norma.add(true);
                    } else norma.add(false);
                    if (bloodValue < 130) blood.setHBDown(true);
                    if (bloodValue > 160) blood.setHBUp(true);

                } else {
                    if (bloodValue >= 120 && bloodValue <= 140) {
                        blood.setHBNorma(true);
                        norma.add(true);
                    } else norma.add(false);
                    if (bloodValue < 120) blood.setHBDown(true);
                    if (bloodValue > 140) blood.setHBUp(true);

                }
                break;
            case "RBC":
                if (gender) {
                    if (bloodValue >= 4 && bloodValue <= 5.1) {
                        blood.setRBCNorma(true);
                        norma.add(true);
                    } else norma.add(false);
                    if (bloodValue < 4) blood.setRBCDown(true);
                    if (bloodValue > 5.1) blood.setRBCUp(true);

                } else {
                    if (bloodValue >= 3.7 && bloodValue <= 4.7) {
                        blood.setRBCNorma(true);
                        norma.add(true);
                    } else norma.add(false);
                    if (bloodValue < 3.7) blood.setRBCDown(true);
                    if (bloodValue > 4.7) blood.setRBCUp(true);

                }
                break;
            case "MCHC":
                if (bloodValue >= 0.85 && bloodValue <= 1.15) {
                    blood.setMCHCNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 0.85) blood.setMCHCDown(true);
                if (bloodValue > 1.15) blood.setMCHCUp(true);
                break;
            case "RTC":
                if (bloodValue >= 0.2 && bloodValue <= 1.2) {
                    blood.setRTCNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 0.1) blood.setRTCDown(true);
                if (bloodValue > 1.2) blood.setRTCUp(true);
                break;
            case "PLT":
                if (bloodValue >= 180 && bloodValue <= 320) {
                    blood.setPLTNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 180) blood.setPLTDown(true);
                if (bloodValue > 320) blood.setPLTUp(true);
                break;

            case "ESR":
                if (gender) {
                    if (bloodValue >= 1 && bloodValue <= 10) {
                        blood.setESRNorma(true);
                        norma.add(true);
                    } else norma.add(false);
                    if (bloodValue < 1) blood.setESRDown(true);
                    if (bloodValue > 10) blood.setESRUp(true);

                } else {
                    if (bloodValue >= 2 && bloodValue <= 15) {
                        blood.setESRNorma(true);
                        norma.add(true);
                    } else norma.add(false);
                    if (bloodValue < 2) blood.setESRDown(true);
                    if (bloodValue > 15) blood.setESRUp(true);

                }
                break;

            case "WBC":
                if (bloodValue >= 4 && bloodValue <= 9) {
                    blood.setWBCNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 4) blood.setWBCDown(true);
                if (bloodValue > 9) blood.setWBCUp(true);
                break;

            case "EOS":
                if (bloodValue >= 0 && bloodValue <= 5) {
                    blood.setEOSNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 0) blood.setEOSDown(true);
                if (bloodValue > 5) blood.setEOSUp(true);
                break;

            case "BAS":
                if (bloodValue >= 0 && bloodValue <= 1) {
                    blood.setBASNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 0) blood.setBASDown(true);
                if (bloodValue > 1) blood.setBASUp(true);
                break;

            case "LYM":
                if (bloodValue >= 18 && bloodValue <= 40) {
                    blood.setLYMNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 18) blood.setLYMDown(true);
                if (bloodValue > 40) blood.setLYMUp(true);
                break;

            case "MON":
                if (bloodValue >= 2 && bloodValue <= 9) {
                    blood.setMONNorma(true);
                    norma.add(true);
                } else norma.add(false);
                if (bloodValue < 2) blood.setMONDown(true);
                if (bloodValue > 9) blood.setMONUp(true);
                break;
        }

    }

}
