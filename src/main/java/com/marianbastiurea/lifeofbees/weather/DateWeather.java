package com.marianbastiurea.lifeofbees.weather;

public class DateWeather implements Weather{
    private int iteration = 0;

    @Override
    public int getTemperature() {
        //depending on interation starting from 1st of March
        return 0;
    }

    @Override
    public int getPrecipitation() {
        return 0;
    }

    @Override
    public int getWindSpeed() {
        return 0;
    }
}
