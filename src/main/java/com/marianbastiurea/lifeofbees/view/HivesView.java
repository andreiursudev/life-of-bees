package com.marianbastiurea.lifeofbees.view;

import com.marianbastiurea.lifeofbees.bees.HoneyBatch;

import java.util.List;

public class HivesView {

    int id;
    int ageOfQueen;
    String honeyType;
    int eggsFrame;
    int honeyFrame;
    private boolean itWasSplit;
    private boolean itWasHarvested;

    public HivesView(int id, int ageOfQueen, int eggsFrame, int honeyFrame, boolean itWasSplit, boolean itWasHarvested) {
        this.id = id;
        this.ageOfQueen = ageOfQueen;
        this.eggsFrame = eggsFrame;
        this.honeyFrame = honeyFrame;
        this.itWasSplit = itWasSplit;
        this.itWasHarvested = itWasHarvested;
    }

    public int getId() {
        return id;
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public String getHoneyType() {
        return honeyType;
    }

    public int getEggsFrame() {
        return eggsFrame;
    }

    public int getHoneyFrame() {
        return honeyFrame;
    }

    public boolean isItWasSplit() {
        return itWasSplit;
    }

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }

    public boolean isItWasHarvested() {
        return itWasHarvested;
    }

    public void setItWasHarvested(boolean itWasHarvested) {
        this.itWasHarvested = itWasHarvested;
    }

    @Override
    public String toString() {
        return "HivesView{" +
                "id=" + id +
                ", ageOfQueen=" + ageOfQueen +
                ", honeyType='" + honeyType + '\'' +
                ", eggsFrame=" + eggsFrame +
                ", honeyFrame=" + honeyFrame +
                '}';
    }
}
