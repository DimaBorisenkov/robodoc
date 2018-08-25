package com.example.dima.robodoc.data.models;

import android.graphics.Color;

import com.example.dima.robodoc.R;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Patient extends RealmObject {

    @PrimaryKey
    private long id;
    @Required
    private String name;
    private String date;
    private String address;
    private String history;

    private int age;

    private boolean gender;
    private boolean state;

    private RealmList<Disease> diseases;
    private RealmList<Blood> blood;

    public Patient(String name, String date, boolean gender, boolean state, RealmList<Disease> diseases) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.state = state;
        this.diseases = diseases;
    }

    public Patient() {
    }

    public Patient(String name, String address, String history, int age) {
        this.name = name;
        this.address = address;
        this.history = history;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<Blood> getBlood() {
        return blood;
    }

    public void setBlood(RealmList<Blood> blood) {
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public RealmList<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(RealmList<Disease> diseases) {
        this.diseases = diseases;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatusColor() {
        if (this.state) return Color.parseColor("#6664DD17");
        else return Color.parseColor("#9DEF0407");
    }

    public int getImageGender() {
        if (this.gender) return R.drawable.man_icon;
        else return R.drawable.woman_icon;
    }

    public int getImageStatus() {
        if (this.state) return R.drawable.healthy;
        else return R.drawable.unhealthy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
