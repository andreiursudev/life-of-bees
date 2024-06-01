package com.marianbastiurea.lifeofbees;


public class EggsFrame {

    private int numberOfEggs;
    //private int numberOfEggsFrame;

    public EggsFrame(int numberOfEggs) {

        this.numberOfEggs = numberOfEggs;
    }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }


    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }



    @Override
    public String toString() {
        return "{" +
                "numberOfEggs=" + numberOfEggs +
                '}';
    }
}



