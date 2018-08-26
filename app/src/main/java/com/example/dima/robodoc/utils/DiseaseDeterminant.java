package com.example.dima.robodoc.utils;


import android.content.Context;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;

import io.realm.RealmList;
import io.realm.com_example_dima_robodoc_data_models_DiseaseRealmProxy;

public class DiseaseDeterminant {
    Context context;

    public RealmList<Disease> selectDisease(Blood blood, Context context) {
        this.context = context;

        RealmList<Disease> diseases = new RealmList<>();

        if (blood.isHBUp())
            diseases.add(new Disease("Зневоднення", R.drawable.dehydration));
        if (blood.isHBUp() && blood.isRBCUp())
            diseases.add(new Disease("Згущення крові", R.drawable.blood_clotting));
        if (blood.isMCHCUp()) {
            diseases.add(new Disease("Недостаток вітамінів", R.drawable.vitamins));
            diseases.add(new Disease("Проблеми з шлунком", R.drawable.stomach));
        }
        if (blood.isMCHCDown() && blood.isRTCDown() && blood.isPLTDown()
                && blood.isWBCDown() && blood.isLYMDown() && blood.isMONDown()
                && blood.isHBDown() && blood.isESRUp()) {
            diseases.add(new Disease("Анемія", R.drawable.anemia));
        }
        if(blood.isWBCUp() && blood.isMCHCUp() && blood.isRBCUp())
            diseases.add(new Disease("Новоутворення", R.drawable.moles));
        if(blood.isRTCDown() && blood.isRBCUp() && blood.isESRUp())
            diseases.add(new Disease("Захворювання нирок", R.drawable.kidneys));
        if(blood.isRTCUp() && blood.isRBCUp())
            diseases.add(new Disease("Крововтрата", R.drawable.hemorrhage));
        if (blood.isMONDown() || blood.isMONUp()){
            if(blood.isPLTUp() && blood.isLYMUp() && blood.isEOSUp() && blood.isWBCDown()){
                diseases.add(new Disease("Лейкоз", R.drawable.leukemia));
            }
        }

        if(blood.isLYMUp() && blood.isMONUp() && blood.isWBCDown() && blood.isBASDown())
            diseases.add(new Disease("Вірусне захворювання", R.drawable.infection));
        if(blood.isESRUp() && blood.isMONUp() && blood.isEOSUp() && blood.isPLTDown() && blood.isLYMDown())
            diseases.add(new Disease("Ауто-імунні захворювання", R.drawable.autoimmune));
        if(blood.isESRUp())
            diseases.add(new Disease("Захворювання печінки", R.drawable.liver));
        if(blood.isESRUp() && blood.isPLTUp() && blood.isWBCUp() && blood.isMONUp()
                && blood.isLYMDown() && blood.isEOSDown() && blood.isBASDown()){
            diseases.add(new Disease("Інфекційне захворювання", R.drawable.infection));
        }
        if(blood.isWBCDown())
            diseases.add(new Disease("Тиф", R.drawable.typhus));
        return diseases;
    }
}
