package com.marianbastiurea.lifeofbees;


import java.util.Map;

public class GameRequest {
    private String gameName;
    private String location;
    private String startDate;
    private int numberOfStartingHives;
    private Map<String, Object> allWeatherData;

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

    public Map<String, Object> getAllWeatherData() {
        return allWeatherData;
    }

    public void setAllWeatherData(Map<String, Object> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }

    @Override
    public String toString() {
        return "GameRequest{" +
                "gameName='" + gameName + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", numberOfStartingHives=" + numberOfStartingHives +
                ", allWeatherData=" + allWeatherData +
                '}';
    }
}
