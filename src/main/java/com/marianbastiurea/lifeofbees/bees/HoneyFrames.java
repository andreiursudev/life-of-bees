package com.marianbastiurea.lifeofbees.bees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HoneyFrames {

    private List<HoneyFrame> honeyFrame;

    public HoneyFrames() {
    }

    public HoneyFrames(List<HoneyFrame> honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    public List<HoneyFrame> getHoneyFrame() {
        return honeyFrame;
    }

    public void setHoneyFrame(List<HoneyFrame> honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    @Override
    public String toString() {
        return "HoneyFrames{" +
                "honeyFrame=" + honeyFrame +
                '}';
    }

    public boolean canAddANewHoneyFrameInHive() {
        long honeyFrameFull = getHoneyFrame().stream()
                .filter(HoneyFrame::isHarvestable)
                .count();
        return getHoneyFrame().size() < 6 && honeyFrameFull == getHoneyFrame().size();
    }


    public void addNewHoneyFrameInHive() {
        if (honeyFrame.size() < 6) {
            honeyFrame.add(new HoneyFrame(0));
        }
    }

    public double harvestHoneyFromHoneyFrames() {
        double totalKgOfHoney = 0;
        for (HoneyFrame honeyFrame : this.getHoneyFrame()) {
            if (honeyFrame.isHarvestable()) {
                totalKgOfHoney += honeyFrame.harvest();
            }
        }
        return totalKgOfHoney;
    }

    public int getNumberOfFullHoneyFrame() {
        int honeyFrameFull = 0;
        for (int i = 0; i < this.getHoneyFrame().size(); i++) {
            if (this.getHoneyFrame().get(i).isFull() && honeyFrameFull < this.getHoneyFrame().size()) {
                honeyFrameFull += 1;
            }
        }
        return honeyFrameFull;
    }

    public static HoneyFrames getRandomHoneyFrames() {
        Random random = new Random();
        HoneyFrames honeyFrames = new HoneyFrames(new ArrayList<>());
        for (int k = 0; k < random.nextInt(3, 5); k++) {
            honeyFrames.getHoneyFrame().add(new HoneyFrame(random.nextDouble(2.5, 3)));
        }
        return honeyFrames;
    }

    public HoneyFrames splitHoneyFrames() {
        HoneyFrames newHiveHoneyFrames = new HoneyFrames(new ArrayList<>());
        for (int i = 0; i < 2; i++) {
            HoneyFrame frameToMove = getHoneyFrame().removeLast();
            newHiveHoneyFrames.getHoneyFrame().add(frameToMove);
        }
        return newHiveHoneyFrames;
    }

    public void removeHoneyFrames() {
        List<HoneyFrame> honeyFrames = getHoneyFrame();
        for (int i = 0; i < 2 && !honeyFrames.isEmpty(); i++) {
            honeyFrames.removeLast();
        }
    }

    public void fillUpAHoneyFrame(double kgOfHoneyToAdd) {
        int numberOfHoneyFrameNotFull = getHoneyFrame().size() - getNumberOfFullHoneyFrame();
        for (HoneyFrame honeyFrame : getHoneyFrame()) {
            honeyFrame.fill(kgOfHoneyToAdd / numberOfHoneyFrameNotFull);
        }
    }
}


