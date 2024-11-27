package com.marianbastiurea.lifeofbees;


import org.springframework.data.annotation.Id;

import java.util.Map;

public class GameRequest {
    private String userId;
    private boolean isPublic;
    private String gameName;
    private String location;
    private String startDate;
    private int numberOfStartingHives;
    private Map<String, WeatherData> allWeatherData;


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

    public Map<String, WeatherData> getAllWeatherData() {
        return allWeatherData;
    }

    public void setAllWeatherData(Map<String, WeatherData> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "GameRequest{" +
                ", userId='" + userId + '\'' +
                ", isPublic=" + isPublic +
                ", gameName='" + gameName + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", numberOfStartingHives=" + numberOfStartingHives +
                ", allWeatherData=" + allWeatherData +
                '}';
    }
}
