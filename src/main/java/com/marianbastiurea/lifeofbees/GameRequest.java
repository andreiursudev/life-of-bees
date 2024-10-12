package com.marianbastiurea.lifeofbees;


public class GameRequest {
    private String gameName;
    private String location;
    private String startDate;
    private int numberOfStartingHives;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getNumberOfStartingHives() {
        return numberOfStartingHives;
    }

    public void setNumberOfStartingHives(int numberOfStartingHives) {
        this.numberOfStartingHives = numberOfStartingHives;
    }
}
