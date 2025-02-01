package com.marianbastiurea.lifeofbees.bees;

import java.util.*;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.*;

public class HoneyFrames {

    public List<HoneyFrame> honeyFrame;

    public HoneyFrames() {
    }

    public HoneyFrames(List<HoneyFrame> honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    public HoneyFrames(int numberOfHoneyFrames, int honeyPerFrame) {
        this.honeyFrame = new ArrayList<>(Collections.nCopies(numberOfHoneyFrames, new HoneyFrame(honeyPerFrame)));
    }

    public static HoneyFrames getRandomHoneyFrames() {
        Random random = new Random();
        HoneyFrames honeyFrames = new HoneyFrames(new ArrayList<>());
        for (int k = 0; k < random.nextInt(3, 5); k++) {
            honeyFrames.getHoneyFrame().add(new HoneyFrame(random.nextDouble(2.5, 3)));
        }
        return honeyFrames;
    }

    public List<HoneyFrame> getHoneyFrame() {
        return honeyFrame;
    }

    public void setHoneyFrame(List<HoneyFrame> honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    public boolean canAddANewHoneyFrameInHive() {

        long honeyFrameFull = getHoneyFrame().stream()
                .filter(HoneyFrame::isHarvestable)
                .count();
        return getHoneyFrame().size() < maxNumberOfHoneyFrames && honeyFrameFull == getHoneyFrame().size();
    }

    public void addNewHoneyFrameInHive() {
        if (honeyFrame.size() < 6) {
            honeyFrame.add(new HoneyFrame(0));
        }
    }

    public double harvestHoneyFromHoneyFrames() {
        return this.getHoneyFrame().stream()
                .filter(HoneyFrame::isHarvestable)
                .mapToDouble(HoneyFrame::harvest)
                .sum();
    }


    public int getNumberOfFullHoneyFrame() {
        return (int) this.getHoneyFrame().stream()
                .filter(HoneyFrame::isFull)
                .count();
    }


    public HoneyFrames splitHoneyFrames() {
        HoneyFrames newHiveHoneyFrames = new HoneyFrames(new ArrayList<>());
        for (int i = 0; i < 2; i++) {
            HoneyFrame frameToMove = getHoneyFrame().removeLast();
            newHiveHoneyFrames.getHoneyFrame().add(frameToMove);
        }
        return newHiveHoneyFrames;
    }

    public void removeLastTwoHoneyFrames() {
        for (int i = 0; i < 2 && !getHoneyFrame().isEmpty(); i++) {
            getHoneyFrame().removeLast();
        }
    }


    public void fillUpAHoneyFrame(double kgOfHoneyToAdd) {
        int numberOfHoneyFrameNotFull = getHoneyFrame().size() - getNumberOfFullHoneyFrame();
        for (HoneyFrame honeyFrame : getHoneyFrame()) {
            honeyFrame.fill(kgOfHoneyToAdd / numberOfHoneyFrameNotFull);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoneyFrames that = (HoneyFrames) o;
        return Objects.equals(honeyFrame, that.honeyFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(honeyFrame);
    }

    @Override
    public String toString() {
        return "HoneyFrames{" +
                "honeyFrame=" + honeyFrame +
                '}';
    }



}


