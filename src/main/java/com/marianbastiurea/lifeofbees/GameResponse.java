package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;

public class GameResponse {

    private List<HivesView> hives = new ArrayList<>();
    private String action;
    private String temperature;
    private String windSpeed;
    private String precipitation;
    private double moneyInTheBank;
    private String currentDate;

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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(double moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }
}


class HivesView {

    int id;
    int ageOfQueen;
    int bees;
    String honeyType;
    int eggsFrame;
    int honeyFrame;
    double totalHoney;


    public HivesView(int id, int ageOfQueen, int bees, String honeyType, int eggsFrame, int honeyFrame, double totalHoney) {
        this.id = id;
        this.ageOfQueen = ageOfQueen;
        this.bees = bees;
        this.honeyType = honeyType;
        this.eggsFrame = eggsFrame;
        this.honeyFrame = honeyFrame;
        this.totalHoney = totalHoney;
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

    public double getTotalHoney() {
        return totalHoney;
    }
}
