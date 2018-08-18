package com.example.dima.robodoc.data.models;


import android.graphics.Color;

import com.example.dima.robodoc.R;

import java.util.ArrayList;

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
    private boolean gender;
    private boolean state;
    private RealmList<Disease> diseases;
    private String date;

    public Patient(String name, String date, boolean gender,
                   boolean state, RealmList<Disease> diseases) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.state = state;
        this.diseases = diseases;
    }

    public Patient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        int color = Color.parseColor("#FFFFFFFF");
        if (this.state) {
            color = Color.parseColor("#6664DD17");
        } else {
            color = Color.parseColor("#9DEF0407");
        }
        return color;
    }

    public int getImageGender() {
        int gender = 0;
        if (this.gender) {
            gender = R.drawable.man_icon;
        } else {
            gender = R.drawable.woman_icon;
        }

        return gender;
    }

    public int getImageStatus() {
        int status = 0;
        if (this.state) {
            status = R.drawable.healthy;
        } else {
            status = R.drawable.unhealthy;
        }

        return status;
    }
}
