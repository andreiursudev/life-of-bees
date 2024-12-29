package com.marianbastiurea.lifeofbees.bees;


import java.util.List;

public class HoneyFrames {
    private int numberOfHoneyFrames;
    private List<HoneyFrame> honeyFrame;

    public HoneyFrames() {
    }

    public HoneyFrames(int numberOfHoneyFrames, List<HoneyFrame> honeyFrame) {
        this.numberOfHoneyFrames = numberOfHoneyFrames;
        this.honeyFrame = honeyFrame;
    }

    public List<HoneyFrame> getHoneyFrame() {
        return honeyFrame;
    }

    public void setHoneyFrame(List<HoneyFrame> honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    public int getNumberOfHoneyFrames() {
        return numberOfHoneyFrames;
    }

    public void setNumberOfHoneyFrames(int numberOfHoneyFrames) {
        this.numberOfHoneyFrames = numberOfHoneyFrames;
    }

}


