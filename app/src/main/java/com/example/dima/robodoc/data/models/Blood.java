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
    private boolean MCHCNorma, MCHCUp, MCHCDown;
    private boolean RTCNorma, RTCUp, RTCDown;
    private boolean PLTNorma, PLTUp, PLTDown;
    private boolean ESRNorma, ESRUp, ESRDown;
    private boolean WBCNorma, WBCUp, WBCDown;
    private boolean EOSNorma, EOSUp, EOSDown;
    private boolean BASNorma, BASUp, BASDown;
    private boolean LYMNorma, LYMUp, LYMDown;
    private boolean MONNorma, MONUp, MONDown;

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

    public boolean isMCHCNorma() {
        return MCHCNorma;
    }

    public void setMCHCNorma(boolean MCHCNorma) {
        this.MCHCNorma = MCHCNorma;
    }

    public boolean isMCHCUp() {
        return MCHCUp;
    }

    public void setMCHCUp(boolean MCHCUp) {
        this.MCHCUp = MCHCUp;
    }

    public boolean isMCHCDown() {
        return MCHCDown;
    }

    public void setMCHCDown(boolean MCHCDown) {
        this.MCHCDown = MCHCDown;
    }

    public boolean isRTCNorma() {
        return RTCNorma;
    }

    public void setRTCNorma(boolean RTCNorma) {
        this.RTCNorma = RTCNorma;
    }

    public boolean isRTCUp() {
        return RTCUp;
    }

    public void setRTCUp(boolean RTCUp) {
        this.RTCUp = RTCUp;
    }

    public boolean isRTCDown() {
        return RTCDown;
    }

    public void setRTCDown(boolean RTCDown) {
        this.RTCDown = RTCDown;
    }

    public boolean isPLTNorma() {
        return PLTNorma;
    }

    public void setPLTNorma(boolean PLTNorma) {
        this.PLTNorma = PLTNorma;
    }

    public boolean isPLTUp() {
        return PLTUp;
    }

    public void setPLTUp(boolean PLTUp) {
        this.PLTUp = PLTUp;
    }

    public boolean isPLTDown() {
        return PLTDown;
    }

    public void setPLTDown(boolean PLTDown) {
        this.PLTDown = PLTDown;
    }

    public boolean isESRNorma() {
        return ESRNorma;
    }

    public void setESRNorma(boolean ESRNorma) {
        this.ESRNorma = ESRNorma;
    }

    public boolean isESRUp() {
        return ESRUp;
    }

    public void setESRUp(boolean ESRUp) {
        this.ESRUp = ESRUp;
    }

    public boolean isESRDown() {
        return ESRDown;
    }

    public void setESRDown(boolean ESRDown) {
        this.ESRDown = ESRDown;
    }

    public boolean isWBCNorma() {
        return WBCNorma;
    }

    public void setWBCNorma(boolean WBCNorma) {
        this.WBCNorma = WBCNorma;
    }

    public boolean isWBCUp() {
        return WBCUp;
    }

    public void setWBCUp(boolean WBCUp) {
        this.WBCUp = WBCUp;
    }

    public boolean isWBCDown() {
        return WBCDown;
    }

    public void setWBCDown(boolean WBCDown) {
        this.WBCDown = WBCDown;
    }

    public boolean isEOSNorma() {
        return EOSNorma;
    }

    public void setEOSNorma(boolean EOSNorma) {
        this.EOSNorma = EOSNorma;
    }

    public boolean isEOSUp() {
        return EOSUp;
    }

    public void setEOSUp(boolean EOSUp) {
        this.EOSUp = EOSUp;
    }

    public boolean isEOSDown() {
        return EOSDown;
    }

    public void setEOSDown(boolean EOSDown) {
        this.EOSDown = EOSDown;
    }

    public boolean isBASNorma() {
        return BASNorma;
    }

    public void setBASNorma(boolean BASNorma) {
        this.BASNorma = BASNorma;
    }

    public boolean isBASUp() {
        return BASUp;
    }

    public void setBASUp(boolean BASUp) {
        this.BASUp = BASUp;
    }

    public boolean isBASDown() {
        return BASDown;
    }

    public void setBASDown(boolean BASDown) {
        this.BASDown = BASDown;
    }

    public boolean isLYMNorma() {
        return LYMNorma;
    }

    public void setLYMNorma(boolean LYMNorma) {
        this.LYMNorma = LYMNorma;
    }

    public boolean isLYMUp() {
        return LYMUp;
    }

    public void setLYMUp(boolean LYMUp) {
        this.LYMUp = LYMUp;
    }

    public boolean isLYMDown() {
        return LYMDown;
    }

    public void setLYMDown(boolean LYMDown) {
        this.LYMDown = LYMDown;
    }

    public boolean isMONNorma() {
        return MONNorma;
    }

    public void setMONNorma(boolean MONNorma) {
        this.MONNorma = MONNorma;
    }

    public boolean isMONUp() {
        return MONUp;
    }

    public void setMONUp(boolean MONUp) {
        this.MONUp = MONUp;
    }

    public boolean isMONDown() {
        return MONDown;
    }

    public void setMONDown(boolean MONDown) {
        this.MONDown = MONDown;
    }
}
