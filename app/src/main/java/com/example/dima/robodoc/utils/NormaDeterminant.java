package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Blood;

import java.util.ArrayList;

public class NormaDeterminant {
    private String bloodName = "";
    private double bloodValue = 0;
    private Blood blood = new Blood();

    public Blood check(ArrayList<Blood> bloodArrayList, boolean gender){

        for(Blood temp : bloodArrayList) {
            bloodName = temp.getName();
            bloodValue = temp.getValue();
            selectNorma(gender);
        }

        return blood;

    }

    void selectNorma(boolean gender){
        switch (bloodName){
            case "HB":
                if(gender){
                    if(bloodValue >= 130 && bloodValue <= 160) blood.setHBNorma(true);
                    if(bloodValue < 130) blood.setHBDown(true);
                    if(bloodValue > 160) blood.setHBUp(true);

                } else {
                    if(bloodValue >= 120 && bloodValue <= 140) blood.setHBNorma(true);
                    if(bloodValue < 120) blood.setHBDown(true);
                    if(bloodValue > 140) blood.setHBUp(true);

                }
                break;
            case "RBC":
                if(gender){
                    if (bloodValue >= 4 && bloodValue <= 5.1) blood.setRBCNorma(true);
                    if (bloodValue < 4) blood.setRBCDown(true);
                    if (bloodValue > 5.1) blood.setRBCUp(true);

                } else {
                    if (bloodValue >= 3.7 && bloodValue <= 4.7) blood.setRBCNorma(true);
                    if (bloodValue < 3.7) blood.setRBCDown(true);
                    if (bloodValue > 4.7) blood.setRBCUp(true);

                }
                break;
        }


    }
}
