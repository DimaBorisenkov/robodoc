package com.example.dima.robodoc.data.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Blood extends RealmObject{

    private String name;
    private double value;

    private boolean HBNorma, HBUp, HBDown;
    private boolean RBCNorma, RBCUp, RBCDown;

    private RealmList<Boolean> norma;
    private RealmList<Blood> blood;

    public Blood() {
    }

    public Blood(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public RealmList<Blood> getBlood() {
        return blood;
    }

    public void setBlood(RealmList<Blood> blood) {
        this.blood = blood;
    }

    public RealmList<Boolean> getNorma() {
        return norma;
    }

    public void setNorma(RealmList<Boolean> norma) {
        this.norma = norma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isHBNorma() {
        return HBNorma;
    }

    public void setHBNorma(boolean HBNorma) {
        this.HBNorma = HBNorma;
    }

    public boolean isHBUp() {
        return HBUp;
    }

    public void setHBUp(boolean HBUp) {
        this.HBUp = HBUp;
    }

    public boolean isHBDown() {
        return HBDown;
    }

    public void setHBDown(boolean HBDown) {
        this.HBDown = HBDown;
    }

    public boolean isRBCNorma() {
        return RBCNorma;
    }

    public void setRBCNorma(boolean RBCNorma) {
        this.RBCNorma = RBCNorma;
    }

    public boolean isRBCUp() {
        return RBCUp;
    }

    public void setRBCUp(boolean RBCUp) {
        this.RBCUp = RBCUp;
    }

    public boolean isRBCDown() {
        return RBCDown;
    }

    public void setRBCDown(boolean RBCDown) {
        this.RBCDown = RBCDown;
    }
}
