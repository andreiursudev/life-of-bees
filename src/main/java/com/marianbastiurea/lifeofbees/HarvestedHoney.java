package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.util.Date;

//todo remove this class
public class HarvestedHoney {
    private int hiveId;
    private String honeyType;
    private double kgOfHoney;

    public HarvestedHoney(int hiveId, String honeyType, double kgOfHoney) {
        this.hiveId = hiveId;
        this.honeyType = honeyType;
        this.kgOfHoney = kgOfHoney;
    }

    public int getHiveId() {
        return hiveId;
    }

    public void setHiveId(int hiveId) {
        this.hiveId = hiveId;
    }

    public String getHoneyType() {
        return honeyType;
    }

    public void setHoneyType(String honeyType) {
        this.honeyType = honeyType;
    }

    public double getKgOfHoney() {
        return kgOfHoney;
    }

    public void setKgOfHoney(double kgOfHoney) {
        this.kgOfHoney = kgOfHoney;
    }

    @Override
    public String toString() {
        return "HarvestedHoney{" +
                "hiveId=" + hiveId +
                ", honeyType='" + honeyType + '\'' +
                ", kgOfHoney=" + kgOfHoney +
                '}';
    }
}