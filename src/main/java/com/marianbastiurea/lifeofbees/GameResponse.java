package com.marianbastiurea.lifeofbees;

import java.util.Date;

public class GameResponse {

    private int hiveId;
    private int ageOfQueen;
    private int numberOfBees;
    private int eggsFrameSize;
    private int honeyFrameSize;
    private double speedWind;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;
    private String currentDate;

    public int getHiveId() {
        return hiveId;
    }

    public void setHiveId(int hiveId) {
        this.hiveId = hiveId;
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public void setAgeOfQueen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }

    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public int getEggsFrameSize() {
        return eggsFrameSize;
    }

    public void setEggsFrameSize(int eggsFrameSize) {
        this.eggsFrameSize = eggsFrameSize;
    }

    public int getHoneyFrameSize() {
        return honeyFrameSize;
    }

    public void setHoneyFrameSize(int honeyFrameSize) {
        this.honeyFrameSize = honeyFrameSize;
    }

    public double getSpeedWind() {
        return speedWind;
    }

    public void setSpeedWind(double speedWind) {
        this.speedWind = speedWind;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "hiveId=" + hiveId +
                ", ageOfQueen=" + ageOfQueen +
                ", numberOfBees=" + numberOfBees +
                ", eggsFrameSize=" + eggsFrameSize +
                ", honeyFrameSize=" + honeyFrameSize +
                ", speedWind=" + speedWind +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                ", currentDate='" + currentDate + '\'' +
                '}';
    }
}
