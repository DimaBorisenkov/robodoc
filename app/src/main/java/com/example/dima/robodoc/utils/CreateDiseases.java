package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Disease;

import java.util.Random;

public class CreateDiseases {


    public Disease createDiseases(boolean gender) {
        Disease generatedDisease = new Disease();
        Random random = new Random();
        int number = random.nextInt(11);
        generatedDisease.setNumber(number);
        CreateValues createValues = new CreateValues();

        switch (number) {
            case 0:
                generatedDisease.setName("Зневоднення");
                generatedDisease.setBloodRealmList(createValues.createValues("Зневоднення", gender));
                break;
            case 1:
                generatedDisease.setName("Згущення крові");
                generatedDisease.setBloodRealmList(createValues.createValues("Згущення крові", gender));
                break;
            case 2:
                generatedDisease.setName("Недостаток вітамінів та проблеми з шлунком");
                generatedDisease.setBloodRealmList(
                        createValues.createValues("Недостаток вітамінів та проблеми з шлунком", gender));
                break;
            case 3:
                generatedDisease.setName("Анемія");
                generatedDisease.setBloodRealmList(createValues.createValues("Анемія", gender));
                break;
            case 4:
                generatedDisease.setName("Новоутворення");
                generatedDisease.setBloodRealmList(createValues.createValues("Новоутворення", gender));
                break;
            case 5:
                generatedDisease.setName("Захворювання нирок");
                generatedDisease.setBloodRealmList(createValues.createValues("Захворювання нирок", gender));
                break;
            case 6:
                generatedDisease.setName("Крововтрата");
                generatedDisease.setBloodRealmList(createValues.createValues("Крововтрата", gender));
                break;
            case 7:
                generatedDisease.setName("Лейкоз");
                generatedDisease.setBloodRealmList(createValues.createValues("Лейкоз", gender));
                break;
            case 8:
                generatedDisease.setName("Вірусне захворювання");
                generatedDisease.setBloodRealmList(createValues.createValues("Вірусне захворювання", gender));
                break;
            case 9:
                generatedDisease.setName("Ауто-імунні захворювання");
                generatedDisease.setBloodRealmList(createValues.createValues("Ауто-імунні захворювання", gender));
                break;
            case 10:
                generatedDisease.setName("Захворювання печінки");
                generatedDisease.setBloodRealmList(createValues.createValues("Захворювання печінки", gender));
                break;
            case 11:
                generatedDisease.setName("Тиф");
                generatedDisease.setBloodRealmList(createValues.createValues("Тиф", gender));
                break;
        }

        return generatedDisease;
    }

}
