package com.example.dima.robodoc.utils;


import android.content.Context;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;

import java.util.ArrayList;

import io.realm.RealmList;

public class DiseaseDeterminant {
    Context context;

    public RealmList<Disease> selectDisease(Blood blood, Context context){
        this.context = context;

        RealmList<Disease> diseases = new RealmList<>();

        if(blood.isHBUp()) diseases.add(new Disease("Зневоднення", R.drawable.dehydration));
        if(blood.isHBUp() && blood.isRBCUp()) diseases.add(new Disease("Згущення крові", R.drawable.blood_clotting));

        return diseases;
    }
}
