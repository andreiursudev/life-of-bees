package com.marianbastiurea.lifeofbees;

public class Environment {
    private Weather weather;

    private Square[][] terrain = new Square[100][100];

    public Environment(Square square) {
        terrain[square.getX()][square.getY()] = square;
    }


    public Square[][] getTerrain() {
        return terrain;
    }

    public Weather getWeather() {
        return weather;
    }
}
