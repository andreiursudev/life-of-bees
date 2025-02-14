package com.marianbastiurea.lifeofbees.bees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.maxNumberOfHoneyFrames;

public class HoneyFrames {

    public List<HoneyFrame> honeyFrame;

    public static RandomParameters randomParameters = new RandomParameters();

    public HoneyFrames() {
    }

    public HoneyFrames(List<HoneyFrame> honeyFrame) {
        this.honeyFrame = new ArrayList<>(honeyFrame);
    }


    public HoneyFrames(int numberOfHoneyFrames, int honeyPerFrame) {
        this.honeyFrame = new ArrayList<>();
        for (int i = 0; i < numberOfHoneyFrames; i++) {
            this.honeyFrame.add(new HoneyFrame(honeyPerFrame));
        }
    }

    public static HoneyFrames getRandomHoneyFrames() {
        Random random = new Random();
        HoneyFrames honeyFrames = new HoneyFrames(new ArrayList<>());
        for (int k = 0; k < randomParameters.numberOfHoneyFrames(); k++) {
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
            HoneyFrame frameToMove = getHoneyFrame().remove(getHoneyFrame().size() - 1);
            newHiveHoneyFrames.getHoneyFrame().add(frameToMove);
        }
        return newHiveHoneyFrames;
    }

    public void hibernateHoneyFrames() {
        if (honeyFrame.size() > 1) {
            honeyFrame.remove(honeyFrame.size() - 1);
            honeyFrame.remove(honeyFrame.size() - 1);
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


