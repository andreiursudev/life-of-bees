package com.marianbastiurea.lifeofbees;

class HoneyFrame {
    private double kgOfHoney;

    public HoneyFrame(double kgOfHoney) {
        this.kgOfHoney = kgOfHoney;
    }

    public double getKgOfHoney() {
        return kgOfHoney;
    }

    public void setKgOfHoney(double kgOfHoney) {
        this.kgOfHoney = kgOfHoney;
    }

    @Override
    public String toString() {
        return "{" +
                "kgOfHoney=" + kgOfHoney +
                '}';
    }
}
