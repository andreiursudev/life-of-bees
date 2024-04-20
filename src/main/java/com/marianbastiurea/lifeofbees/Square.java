package com.marianbastiurea.lifeofbees;

/*
Honey production:
  rapeseed 40-50 kg/ha
  acacia 1000-1600 kg/ha
 linden 800-1200 kg/ha
 sunflower 30-60 kg/ha
 white clover 100-120 kg/ha
 */

public class Square {
    private final int x;
    private final int y;


    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}