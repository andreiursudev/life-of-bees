package com.marianbastiurea.lifeofbees;

/*
>10Celsius degree
wind speed<5m/
no rain

 */

import com.marianbastiurea.lifeofbees.weather.Weather;

public class Environment {
    private Weather weather;

    private Square[][] terrain = new Square[100][100];

    public Environment(Square square, Weather weather) {
        this.weather = weather;
        terrain[square.getX()][square.getY()] = square;
    }


    public Square[][] getTerrain() {
        return terrain;
    }

    public Weather getWeather() {
        return weather;
    }
}