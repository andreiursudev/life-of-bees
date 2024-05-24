package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EggsFrame {

    private int numberOfEggs;
    private int numberOfEggsFrame;

    public EggsFrame(int numberOfEggsFrame,int numberOfEggs) {
        this.numberOfEggsFrame = numberOfEggsFrame;
        this.numberOfEggs = numberOfEggs;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public int getNumberOfEggsFrame() {
        return numberOfEggsFrame;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public void setNumberOfEggsFrame(int numberOfEggsFrame) {
        this.numberOfEggsFrame = numberOfEggsFrame;
    }

    @Override
    public String toString() {
        return "{" +
                "numberOfEggs=" + numberOfEggs +
                ", numberOfEggsFrame=" + numberOfEggsFrame +
                '}';
    }
}



