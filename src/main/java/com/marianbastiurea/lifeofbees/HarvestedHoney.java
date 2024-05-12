package com.marianbastiurea.lifeofbees;

import java.util.Date;

public class HarvestedHoney {
    private Apiary apiary;
    private Hive hive;
    private int hiveNumber;
    private int numberOfFramesHarvested;
    private String honeyType;
    private double kgOfHoney;
    private final Date date;

    public HarvestedHoney(Apiary apiary, Hive hive, int hiveNumber, int numberOfFramesHarvested,
                          String honeyType, double kgOfHoney, Date date) {
        this.apiary = apiary;
       // this.hive = hive;
       // this.honey = honey;
        this.hiveNumber = this.hive.getId();
        this.numberOfFramesHarvested = numberOfFramesHarvested;
        this.honeyType = this.hive.getHoneyFrames().g;
        this.kgOfHoney = kgOfHoney;
        this.date=date;
    }


    public Apiary getApiary() {
        return apiary;
    }

    public void setApiary(Apiary apiary) {
        this.apiary = apiary;
    }

    public Hive getHive() {
        return hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

//    public Honey getHoney() {
//        return honey;
//    }
//
//    public void setHoney(Honey honey) {
//        this.honey = honey;
//    }

    public int getHiveNumber() {
        return hiveNumber;
    }

    public void setHiveNumber(int hiveNumber) {
        this.hiveNumber = hiveNumber;
    }

    public int getNumberOfFramesHarvested() {
        return numberOfFramesHarvested;
    }

    public void setNumberOfFramesHarvested(int numberOfFramesHarvested) {
        this.numberOfFramesHarvested = numberOfFramesHarvested;
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

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "HarvestedHoney{" +
                "apiary=" + apiary +
                ", hive=" + hive +
               // ", honey=" + honey +
                ", hiveNumber=" + hiveNumber +
                ", numberOfFramesHarvested=" + numberOfFramesHarvested +
                ", honeyType='" + honeyType + '\'' +
                ", kgOfHoney=" + kgOfHoney +
                ", date=" + date +
                '}';
    }

}

