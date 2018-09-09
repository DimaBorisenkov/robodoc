package com.example.dima.robodoc.utils;


import android.content.Context;

import com.example.dima.robodoc.R;
import com.example.dima.robodoc.data.models.Blood;
import com.example.dima.robodoc.data.models.Disease;

import io.realm.RealmList;

public class DiseaseDeterminant {
    Context context;

    public RealmList<Disease> selectDisease(Blood blood, Context context) {
        this.context = context;

        RealmList<Disease> diseases = new RealmList<>();
        RealmList<String> medicines = new RealmList<>();

        if (blood.isHBUp()) {
            medicines.add("Регідрон");
            diseases.add(new Disease("Зневоднення", R.drawable.dehydration, medicines));
        }
        if (blood.isHBUp() && blood.isRBCUp()) {
            medicines.add("Гепарін");
            diseases.add(new Disease("Згущення крові", R.drawable.blood_clotting, medicines));
        }
        if (blood.isMCHCUp()) {
            medicines.add("Вітаміни");
            medicines.add("Белластезин");
            medicines.add("Еспумізан");
            diseases.add(new Disease("Недостаток вітамінів", R.drawable.vitamins, medicines));
            diseases.add(new Disease("Проблеми з шлунком", R.drawable.stomach, medicines));
        }
        if (blood.isMCHCDown() && blood.isRTCDown() && blood.isPLTDown()
                && blood.isWBCDown() && blood.isLYMDown() && blood.isMONDown()
                && blood.isHBDown() && blood.isESRUp()) {
            medicines.add("Актиферрин");
            diseases.add(new Disease("Анемія", R.drawable.anemia, medicines));
        }
        if (blood.isWBCUp() && blood.isMCHCUp() && blood.isRBCUp())
            diseases.add(new Disease("Новоутворення", R.drawable.moles, medicines));
        if (blood.isRTCDown() && blood.isRBCUp() && blood.isESRUp()) {
            medicines.add("Уролесан");
            medicines.add("Канефрон");
            diseases.add(new Disease("Захворювання нирок", R.drawable.kidneys, medicines));
        }
        if (blood.isRTCUp() && blood.isRBCUp())
            diseases.add(new Disease("Крововтрата", R.drawable.hemorrhage, medicines));
        if (blood.isMONDown() || blood.isMONUp()) {
            if (blood.isPLTUp() && blood.isLYMUp() && blood.isEOSUp() && blood.isWBCDown()) {
                diseases.add(new Disease("Лейкоз", R.drawable.leukemia, medicines));
            }
        }

        if (blood.isLYMUp() && blood.isMONUp() && blood.isWBCDown()) {
            medicines.add("Афлубін");
            diseases.add(new Disease("Вірусне захворювання", R.drawable.infection, medicines));
        }
        if (blood.isESRUp() && blood.isMONUp() && blood.isEOSUp() && blood.isPLTDown() && blood.isLYMDown())
            diseases.add(new Disease("Ауто-імунні захворювання", R.drawable.autoimmune, medicines));
        if (blood.isESRUp()) {
            medicines.add("Галстена");
            medicines.add("Карсил");
            medicines.add("Енсеале");
            diseases.add(new Disease("Захворювання печінки", R.drawable.liver, medicines));
        }
        if (blood.isESRUp() && blood.isPLTUp() && blood.isWBCUp() && blood.isMONUp()
                && blood.isLYMDown() && blood.isEOSDown()) {
            medicines.add("Антибіотики");
            diseases.add(new Disease("Інфекційне захворювання", R.drawable.infection, medicines));
        }
        if (blood.isWBCDown()) {
            medicines.add("Бисептазол");
            diseases.add(new Disease("Тиф", R.drawable.typhus, medicines));
        }

        return diseases;
    }
}
