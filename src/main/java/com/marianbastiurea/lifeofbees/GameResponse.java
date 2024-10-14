package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;

public class GameResponse {

    private List<HivesView> hives = new ArrayList<>();
    private String action;
    private double temperature;
    private double windSpeed;
    private double precipitation;
    private double moneyInTheBank;
    private String currentDate;
    private double totalKgOfHoney;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<HivesView> getHives() {
        return hives;
    }

    public void setHives(List<HivesView> hives) {
        this.hives = hives;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(double moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getTotalKgOfHoney() {
        return totalKgOfHoney;
    }

    public void setTotalKgOfHoney(double totalKgOfHoney) {
        this.totalKgOfHoney = totalKgOfHoney;
    }
}


class HivesView {

    int id;
    int ageOfQueen;
    int bees;
    String honeyType;
    int eggsFrame;
    int honeyFrame;
    double kgOfHoney;


    public HivesView(int id, int ageOfQueen, int bees, String honeyType, int eggsFrame, int honeyFrame, double kgOfHoney) {
        this.id = id;
        this.ageOfQueen = ageOfQueen;
        this.bees = bees;
        this.honeyType = honeyType;
        this.eggsFrame = eggsFrame;
        this.honeyFrame = honeyFrame;
        this.kgOfHoney=kgOfHoney;
    }

    public int getId() {
        return id;
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public int getBees() {
        return bees;
    }

    public String getHoneyType() {
        return honeyType;
    }

    public int getEggsFrame() {
        return eggsFrame;
    }

    public int getHoneyFrame() {
        return honeyFrame;
    }

    public double getKgOfHoney() {
        return kgOfHoney;
    }
}
