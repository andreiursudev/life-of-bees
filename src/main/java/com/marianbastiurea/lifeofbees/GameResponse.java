package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;

public class GameResponse {

    /*
    {
    hives: [
        {
            id: 1,
            ageOfQueen: "0",
            bees: "12345",
            rapeseedHoney: "136.6",
            eggsFrame: "2",
            honeyFrame: "3",
            totalHoney: "316.6",
        },
        {
            id: 2,
            ageOfQueen: "0",
            bees: "12345",
            rapeseedHoney: "136.6",
            eggsFrame: "2",
            honeyFrame: "3",
            totalHoney: "316.6",
        }],
    action: {
        name: "Insect control"
    },
    date: "1-Apr-2024",
    temp: "22",
    windSpeed: "3",
    moneyInTheBank: "3000",
    flower: "rapeseed"


}
     */

    private List<HivesView> hives = new ArrayList<>();
    private String action;
    private double temp;
    private double windSpeed;
    private double moneyInTheBank;

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

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
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
