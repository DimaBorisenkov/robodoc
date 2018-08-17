package com.example.dima.robodoc.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;

import java.util.ArrayList;

public class DiseaseDeterminant {
    Context context;

    public ArrayList<Disease> selectDisease(Blood blood, Context context){
        this.context = context;

        ArrayList<Disease> diseases = new ArrayList<>();

        if(blood.isHBUp()){
            diseases.add(new Disease("Зневоднення",
                    R.drawable.dehydration));
        }

        if(blood.isHBUp() && blood.isRBCUp()){
            diseases.add(new Disease("Згущення крові",
                    R.drawable.blood_clotting));
        }

        return diseases;
    }
}
