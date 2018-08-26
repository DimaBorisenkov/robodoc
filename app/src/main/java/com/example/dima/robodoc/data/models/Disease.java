package com.example.dima.robodoc.data.models;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Disease extends RealmObject {
    private String name;
    private int imageId;
    private RealmList<Blood> bloodRealmList;
    private int number;

    public Disease(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public Disease() {
    }

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public RealmList<Blood> getBloodRealmList() {
        return bloodRealmList;
    }

    public void setBloodRealmList(RealmList<Blood> bloodRealmList) {
        this.bloodRealmList = bloodRealmList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
