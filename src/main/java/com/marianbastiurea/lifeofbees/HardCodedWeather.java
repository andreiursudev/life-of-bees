package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.weather.Weather;

public class HardCodedWeather implements Weather {

    private int[] temperature = {6,5,4,6,7,8,7,6,5,5,5,6,7,8};
    private int iteration = 0;
    @Override
    public int getTemperature() {
        int temp = temperature[iteration];
        iteration++;
        return temp;
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
