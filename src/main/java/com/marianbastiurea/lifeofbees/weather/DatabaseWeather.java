package com.marianbastiurea.lifeofbees.weather;

public class DatabaseWeather implements Weather{
    @Override
    public int getTemperature() {

        //connect to database to retrieve weather
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
