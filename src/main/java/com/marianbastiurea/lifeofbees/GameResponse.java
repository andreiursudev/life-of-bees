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
    private String windSpeed;
    private String moneyInTheBank;

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

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(String moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }
}

class HivesView {

    public HivesView(int id, int ageOfQueen, int bees, int rapeseedHoney, int eggsFrame, int honeyFrame, int totalHoney) {
        this.id = id;
        this.ageOfQueen = ageOfQueen;
        this.bees = bees;
        this.rapeseedHoney = rapeseedHoney;
        this.eggsFrame = eggsFrame;
        this.honeyFrame = honeyFrame;
        this.totalHoney = totalHoney;
    }

    int id;
    int ageOfQueen;
    int bees;
    int rapeseedHoney;
    int eggsFrame;
    int honeyFrame;
    int totalHoney;
}
