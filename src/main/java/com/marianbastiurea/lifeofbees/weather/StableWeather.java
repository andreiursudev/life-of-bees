package com.marianbastiurea.lifeofbees.weather;

public class StableWeather implements Weather {
    private int temperature;
    private int precipitation;

    public StableWeather(int temperature, int precipitation) {
        this.temperature = temperature;
        this.precipitation = precipitation;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    @Override
    public int getWindSpeed() {
        return 0;
    }
}