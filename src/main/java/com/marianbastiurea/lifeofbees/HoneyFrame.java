package com.marianbastiurea.lifeofbees;

public class HoneyFrame {
    private double kgOfHoney;
    private String honeyType;

    public HoneyFrame(double kgOfHoney, String honeyType) {
        this.kgOfHoney = kgOfHoney;
        this.honeyType = honeyType;
    }

    public double getKgOfHoney() {
        return kgOfHoney;
    }

    public void setKgOfHoney(double kgOfHoney) {
        this.kgOfHoney = kgOfHoney;
    }

    public String getHoneyType() {
        return honeyType;
    }

    public void setHoneyType(String honeyType) {
        this.honeyType = honeyType;
    }
}
