package com.example.dima.robodoc.data.models;

import android.media.Image;

import java.io.Serializable;

public class Disease implements Serializable{
    private String name;
    private Image image;

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}