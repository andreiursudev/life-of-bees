package com.marianbastiurea.lifeofbees;

import java.util.Date;

public class HarvestedHoney {
    private Hive hive;
    private int hiveId;
    private int numberOfFramesHarvested;
    private String honeyType;
    private double kgOfHoney;
    private final Date date;

    public HarvestedHoney( Hive hive, int hiveId, int numberOfFramesHarvested,
                          String honeyType, double kgOfHoney, Date date) {

        this.hiveId = this.hive.getId();
        this.numberOfFramesHarvested = numberOfFramesHarvested;
        this.honeyType = this.hive.getHoney().getHoneyType();
        this.kgOfHoney = kgOfHoney;
        this.date=date;
    }

    public HarvestedHoney(int hiveId, int numberOfFramesHarvested, String honeyType, double kgOfHoney, Date date) {
        this.hiveId = hiveId;
        this.numberOfFramesHarvested = numberOfFramesHarvested;
        this.honeyType = honeyType;
        this.kgOfHoney = kgOfHoney;
        this.date = date;
    }

    public int getHiveId() {
        return hiveId;
    }

    public void setHiveId(int hiveId) {
        this.hiveId = hiveId;
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
                "hiveID=" + hiveId +
                ", numberOfFramesHarvested=" + numberOfFramesHarvested +
                ", honeyType='" + honeyType + '\'' +
                ", kgOfHoney=" + kgOfHoney +
                ", date=" + date +
                '}';
    }

}

