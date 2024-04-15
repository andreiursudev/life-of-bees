package com.marianbastiurea.lifeofbees;

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
