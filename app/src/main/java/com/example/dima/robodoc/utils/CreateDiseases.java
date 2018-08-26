package com.example.dima.robodoc.utils;

import com.example.dima.robodoc.data.models.Disease;

import java.util.Random;

public class CreateDiseases {


    public Disease createDiseases(boolean gender) {
        Disease generatedDisease = new Disease();
        Random random = new Random();
        int number = random.nextInt(2 - 0) + 0;
        CreateValues createValues = new CreateValues();

        switch (number) {
            case 0:
                generatedDisease.setName("Зневоднення");
                generatedDisease.setNumber(0);
                generatedDisease.setBloodRealmList(createValues.createValues("Зневоднення", gender));
                break;
            case 1:
                generatedDisease.setName("Згущення крові");
                generatedDisease.setNumber(1);
                generatedDisease.setBloodRealmList(createValues.createValues("Згущення крові", gender));
                break;
        }

        return generatedDisease;
    }

}
