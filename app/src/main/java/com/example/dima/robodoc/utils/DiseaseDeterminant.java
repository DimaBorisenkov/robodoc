package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;

import java.util.ArrayList;

public class DiseaseDeterminant {

    public ArrayList<Disease> selectDisease(Blood blood){
        ArrayList<Disease> diseases = new ArrayList<>();

        if(blood.isHBUp()){
            diseases.add(new Disease("Зневоднення"));
        }

        if(blood.isHBUp() && blood.isRBCUp()){
            diseases.add(new Disease("Згущення крові"));
        }

        return diseases;
    }
}
