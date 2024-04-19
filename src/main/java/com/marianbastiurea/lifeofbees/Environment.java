package com.marianbastiurea.lifeofbees;

/*
>10Celsius degree
wind speed<5m/
no rain

 */

import com.marianbastiurea.lifeofbees.weather.Weather;

public class Environment {
    private Weather weather;


    public Environment( Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}