package com.marianbastiurea.lifeofbees;

import java.util.Random;
public  class Hive {
    private int id;
    private int totalFrame;
    private int honeyFrame;
    private int eggsFrame;
    private int ageOfQueen;

    public Hive(int id, int totalFrame, int honeyFrame, int ageOfQueen) {
        this.id = id;
        this.totalFrame = totalFrame;
        this.honeyFrame = honeyFrame;
        this.ageOfQueen = ageOfQueen;
        this.eggsFrame=eggsFrame;
    }

    public int getId() {
        return id;
    }

    public int getTotalFrame() {
        return totalFrame;
    }

    public int getHoneyFrame() {
        return honeyFrame;
    }

    public  int getAgeOfQueen() {
        return ageOfQueen;
    }

    public void setAgeOfQueen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }

    public int getEggsFrame() {
        return eggsFrame;
    }

    public void setEggsFrame(int eggsFrame) {
        this.eggsFrame = eggsFrame;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", totalFrame=" + totalFrame +
                ", honeyFrame=" + honeyFrame +
                ", ageOfQueen=" + ageOfQueen +
                '}';
    }
}

