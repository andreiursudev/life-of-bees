package com.marianbastiurea.lifeofbees;


public class GameRequest {
    private String name;
    private String location;
    private String startDate;
    private Integer hives;

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

    public Integer getHives() {
        return hives;
    }

    public void setHives(Integer hives) {
        this.hives = hives;
    }
}
