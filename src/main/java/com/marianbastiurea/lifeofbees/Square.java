package com.marianbastiurea.lifeofbees;

/*
Honey production:
  rapeseed 40-50 kg/ha
  acacia 1000-1600 kg/ha
 linden 800-1200 kg/ha
 sunflower 30-60 kg/ha
 white clover 100-120 kg/ha
 */

import java.util.List;

public class Square {
    private final int x;
    private final int y;
    private Queen queen;
    private List<Bee> bees;
    private Hive hive;

    public Square(int x, int y, Hive hive, Queen queen, List<Bee> bees) {
        this.x = x;
        this.y = y;
        this.hive = hive;
        this.queen = queen;
        this.bees = bees;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Queen getQueen() {
        return queen;
    }

    public List<Bee> getBees() {
        return bees;
    }

    public Hive getHive() {
        return hive;
    }
}