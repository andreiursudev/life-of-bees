package com.marianbastiurea.lifeofbees;

public class Queen {

    public double makeEggs(Hive hive, Environment environment) {
        return hive.getHoneyFrames() * environment.getWeather().getTemperature()* 0.15 ;
    }
}
