package com.marianbastiurea.lifeofbees;


public class GameRequest {
    private String name;
    private String location;
    private String startDate;
    private Integer numberOfStartingHives;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setNumberOfStartingHives(Integer numberOfStartingHives) {
        this.numberOfStartingHives = numberOfStartingHives;
    }
}
