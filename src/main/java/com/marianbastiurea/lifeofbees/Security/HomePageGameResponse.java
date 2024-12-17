package com.marianbastiurea.lifeofbees.Security;

public class HomePageGameResponse {
    private String gameName;
    private String location;
    private int hivesNumber;
    private double moneyInTheBank;
    private double totalKgOfHoneyHarvested;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHivesNumber() {
        return hivesNumber;
    }

    public void setHivesNumber(int hivesNumber) {
        this.hivesNumber = hivesNumber;
    }

    public double getTotalKgOfHoneyHarvested() {
        return totalKgOfHoneyHarvested;
    }

    public void setTotalKgOfHoneyHarvested(double totalKgOfHoneyHarvested) {
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

    public double getMoneyInTheBank() {
        return moneyInTheBank;
    }

    public void setMoneyInTheBank(double moneyInTheBank) {
        this.moneyInTheBank = moneyInTheBank;
    }

    public HomePageGameResponse(String gameName, String location, int hivesNumber, double totalKgOfHoneyHarvested, double moneyInTheBank) {
        this.gameName = gameName;
        this.location = location;
        this.hivesNumber = hivesNumber;
       this.moneyInTheBank=moneyInTheBank;
        this.totalKgOfHoneyHarvested = totalKgOfHoneyHarvested;
    }

}
