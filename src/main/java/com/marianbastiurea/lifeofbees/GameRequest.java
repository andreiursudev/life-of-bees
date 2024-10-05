package com.marianbastiurea.lifeofbees;


public class GameRequest {
    private String gameName;
    private String location;
    private String startDate;
    private String hives;

    // Getters și Setters
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

    public String getHives() {
        return hives;
    }

    public void setHives(String hives) {
        this.hives = hives;
    }
}
