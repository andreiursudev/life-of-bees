package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.time.Month;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hive {
    public boolean itWasSplit;
    public List<HoneyBatch> honeyBatches;
    public boolean itWasHarvested;

    //TODO creaza o clasa BeesBatches in care sa encapsulezi campul beesBatches
    LinkedList<Integer> beesBatches = new LinkedList<>();
    private int id;
    private EggFrames eggFrames;
    private Queen queen;
    private HoneyFrames honeyFrames;

    public Hive() {
    }

    public Hive(EggFrames eggFrames, LinkedList<Integer> beesBatches, HoneyFrames honeyFrames, List<HoneyBatch> honeyBatches) {
        this.eggFrames = eggFrames;
        this.beesBatches = beesBatches;
        this.honeyFrames = honeyFrames;
        this.honeyBatches = honeyBatches;
    }

    public Hive(int id, boolean itWasSplit, Queen queen) {
        this.id = id;
        this.itWasSplit = itWasSplit;
        this.queen = queen;
    }



    public Hive(
            int hiveIdCounter,
            boolean itWasSplit,
            boolean wasMovedAnEggsFrame,
            EggFrames eggFrames,
            HoneyFrames honeyFrames,
            LinkedList<Integer> beesBatches,
            List<HoneyBatch> honeyBatches,
            Queen queen,
            boolean itWasHarvested) {
        this.id = hiveIdCounter;
        this.itWasSplit = itWasSplit;
        this.eggFrames = eggFrames;
        this.honeyFrames = honeyFrames;
        this.beesBatches = beesBatches;
        this.honeyBatches = honeyBatches;
        this.queen = queen;
        this.itWasHarvested = itWasHarvested;
    }

    public boolean isItWasSplit() {
        return itWasSplit;
    }

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }


    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", itWasSplit=" + this.itWasSplit +
                ", numberOfHoneyFrame=" + this.honeyFrames.getHoneyFrame().size() +
                ", numberOfEggsFrame=" + this.eggFrames.getNumberOfEggFrames() + "\n" +
                ", eggFrames=" + this.eggFrames + "\n" +
                ", age of queen=" + this.queen.getAgeOfQueen() +
                ", beesBatches=" + this.beesBatches +
                ", honeyFrames=" + this.honeyFrames +
                ", itWasHarvested=" + this.itWasHarvested +
                ", honeyBatches=" + this.honeyBatches +
                '}';
    }

    public EggFrames getEggFrames() {
        return eggFrames;
    }

    public void setEggFrames(EggFrames eggFrames) {
        this.eggFrames = eggFrames;
    }

    public Queen getQueen() {
        return queen;
    }

    public void setQueen(Queen queen) {
        this.queen = queen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgeOfQueen() {
        return getQueen().getAgeOfQueen();
    }

    public List<HoneyBatch> getHoneyBatches() {
        return honeyBatches;
    }

    public void setHoneyBatches(List<HoneyBatch> honeyBatches) {
        this.honeyBatches = honeyBatches;
    }

    public HoneyFrames getHoneyFrames() {
        return honeyFrames;
    }

    public void setHoneyFrames(HoneyFrames honeyFrames1) {
        this.honeyFrames = honeyFrames1;
    }

    public LinkedList<Integer> getBeesBatches() {
        return beesBatches;
    }

    public void setBeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public boolean isItWasHarvested() {
        return itWasHarvested;
    }

    public void setItWasHarvested(boolean itWasHarvested) {
        this.itWasHarvested = itWasHarvested;
    }

    public boolean checkIfHiveCouldBeSplit(BeeTime currentDate) {
        return !this.itWasSplit &&
                currentDate.timeToSplitHive() &&
                this.eggFrames.isFullEggFrames() &&
                this.eggFrames.is80PercentFull();
    }


    public void addNewEggsFrameInHive() {
        if (this.eggFrames != null) {
            this.eggFrames.incrementNumberOfEggFrames();
        }
    }

    public void addHoneyBatches
            (List<HoneyBatch> honeyBatches) {
        if (honeyBatches != null && !honeyBatches.isEmpty())
            this.honeyBatches.addAll(honeyBatches);
    }

    public void maybeChangeQueen(BeeTime currentDate) {
        double numberRandom = Math.random();
        Month month = currentDate.getMonth();
        int dayOfMonth = currentDate.getDayOfMonth();
        if ((numberRandom < 0.3 && month == Month.MAY && dayOfMonth == 1) || queen.getAgeOfQueen() == 5) {
            queen = new Queen(0);
        }
    }

    public void iterateOneDay(BeeTime currentDate, double weatherIndex) {
        maybeChangeQueen(currentDate);
        int numberOfEggs = queen.iterateOneDay(currentDate, weatherIndex);
        int bees = eggFrames.iterateOneDay(numberOfEggs);
        getBeesBatches().add(bees);
        fillUpExistingHoneyFramesFromHive(currentDate);
        getBeesBatches().removeFirst();
        List<HoneyBatch> harvestedHoneyBatches = harvestHoney(currentDate);
        addHoneyBatches(harvestedHoneyBatches);
    }

    public List<HoneyBatch> harvestHoney(BeeTime currentDate) {
        if (!currentDate.timeToHarvestHive() || isItWasSplit()) {
            return Collections.emptyList();
        }
        double harvestedHoney = getHoneyFrames().harvestHoneyFromHoneyFrames();
        if (harvestedHoney <= 0) {
            return Collections.emptyList();
        }

        setItWasHarvested(true);
        HoneyBatch honeyBatch = new HoneyBatch(
                getId(),
                harvestedHoney,
                currentDate.honeyType(),
                false
        );

        return List.of(honeyBatch);
    }

    //TODO cred ca ceva e gresit aici, fa un unit test sa verifici daca totul e ok
    public LinkedList<Integer> splitBeesBatches() {
        LinkedList<Integer> newHiveBeesBatches = new LinkedList<>(getBeesBatches());
        for (int i = 0; i < getBeesBatches().size(); i++) {
            int bees = getBeesBatches().get(i);
            int beesToTransfer = bees / 2;
            getBeesBatches().set(i, bees - beesToTransfer);
            newHiveBeesBatches.add(beesToTransfer);
        }
        return newHiveBeesBatches;
    }

    public void removeBeesBatches() {
        LinkedList<Integer> beesBatches = getBeesBatches();
        beesBatches.subList(Math.max(0, beesBatches.size() - 2), beesBatches.size()).clear();
    }


    public void fillUpExistingHoneyFramesFromHive(BeeTime currentDate) {
        Random random = new Random();
        int numberOfFlight = random.nextInt(3, 6);
        HoneyType honeyType = currentDate.honeyType();
        double productivity = honeyType.getProductivity();
        double totalBeesBatches = this.getBeesBatches().stream()
                .mapToInt(Integer::intValue)
                .sum();
        double kgOfHoneyToAdd = totalBeesBatches * numberOfFlight * 0.00002 * productivity;

        honeyFrames.fillUpAHoneyFrame(kgOfHoneyToAdd);
    }

}
