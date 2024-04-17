package com.marianbastiurea.lifeofbees;

public class Weather {
    private int temperature;
    private int precipitation;

    public Weather(int temperature, int precipitation) {
        this.temperature = temperature;
        this.precipitation = precipitation;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getPrecipitation() {
        return precipitation;
    }
}