package com.marianbastiurea.lifeofbees;

public  class Hive {
    private int id;
    private int numberOfHoneyFrame;
    private int numberOfEggsFrame;
    private Queen queen;

    public Hive(int id, Queen queen) {
        this.id = id;
        this.queen = queen;
    }

    public Hive(int id) {
        this.id = id;
    }

    public Hive(int id, int numberOfHoneyFrame, int numberOfEggsFrame, Queen queen) {
        this.id = id;
        this.numberOfHoneyFrame = numberOfHoneyFrame;
        this.numberOfEggsFrame = numberOfEggsFrame;
        this.queen = queen;
    }

    public Hive() {
    }

    public int getId() {
        return id;
    }


    public int getHoneyFrame() {
        return numberOfHoneyFrame;
    }

    public int getNumberOfEggsFrame() {
        return numberOfEggsFrame;
    }
    public Queen getQueen() {
        return queen;
    }

    public void setNumberOfEggsFrame(int numberOfEggsFrame) {
        this.numberOfEggsFrame = numberOfEggsFrame;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", numberOfHoneyFrame=" + numberOfHoneyFrame +
                ", numberOfEggsFrame=" + numberOfEggsFrame +
                ", queen=" + queen +
                '}';
    }
}

