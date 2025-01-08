package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Apiary {

    private List<Hive> hives;
    private HarvestHoney totalHarvestedHoney;

    public Apiary(List<Hive> hives) {
        this.hives = hives;
        this.totalHarvestedHoney = new HarvestHoney(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    private static Integer randomRemoveAHive(List<Hive> hives) {
        Integer hiveIdToRemove = 0;
        if (!hives.isEmpty()) {
            Random random = new Random();
            Hive hiveToRemove = hives.remove(random.nextInt(hives.size()));
            hiveIdToRemove = hiveToRemove.getId();
            System.out.println("hive removed: " + hiveToRemove.getId());
        }
        return hiveIdToRemove;
    }

    public HarvestHoney getTotalHarvestedHoney() {
        return totalHarvestedHoney;
    }

    public List<Hive> getHives() {
        return hives;
    }

    @Override
    public String toString() {
        return "Apiary{" +
                "hives=" + hives +
                ", totalHarvestedHoney=" + totalHarvestedHoney +
                '}';
    }

    public Hive getHiveById(Integer hiveId) {
        for (Hive hive : hives) {
            if (hive.getId() == hiveId) {
                return hive;
            }
        }
        return null;
    }

    public List<Hive> createHive(int numberOfHives, LocalDate date) {
        Random random = new Random();
        List<Hive> newHives = new ArrayList<>();
        for (int i = 1; i <= numberOfHives; i++) {
            int ageOfQueen = random.nextInt(1, 6);
            EggFrames eggFrames = EggFrames.getRandomEggFrames();
            LinkedList<Integer> beesBatches = new LinkedList<>();
            for (int k = 0; k < 30; k++) {
                beesBatches.add(random.nextInt(600, 700));
            }
            HoneyFrames honeyFrames = HoneyFrames.getRandomHoneyFrames();

            Hive hive = new Hive(
                    newHives.size() + 1,
                    false,
                    false,
                    eggFrames,
                    honeyFrames,
                    beesBatches,
                    new ArrayList<>(),
                    new Queen(ageOfQueen),
                    false
            );
            newHives.add(hive);
        }
        return newHives;

    }

    public void splitHive(Hive hive) {
        List<Hive> newHives = new ArrayList<>();
        EggFrames eggFrames = hive.getEggFrames();
        if (eggFrames.isFullEggFrames() && !hive.isItWasSplit()) {
            hive.setItWasSplit(true);
            Hive newHive = new Hive(this.getHives().size() + 1, true, new Queen(0));
            newHive.setWasMovedAnEggsFrame(false);
            EggFrames newHiveEggFrames = hive.getEggFrames().splitEggFrames();
            newHive.setEggFrames(newHiveEggFrames);
            HoneyFrames newHiveHoneyFrames = hive.getHoneyFrames().splitHoneyFrames();
            newHive.setHoneyFrames(newHiveHoneyFrames);
            LinkedList<Integer> newHiveBeesBatches = hive.splitBeesBatches();
            newHive.setBeesBatches(newHiveBeesBatches);
            newHive.setHoneyBatches(new ArrayList<>());
            newHives.add(newHive);
            System.out.println("this is parent hive: " + hive);
            System.out.println("this is child hive: " + newHive);
        }
        hives.addAll(newHives);
    }

    public Integer hibernate() {

        this.getHives().forEach(hive -> {
            hive.getQueen().setAgeOfQueen(hive.getQueen().getAgeOfQueen() + 1);
            hive.setItWasSplit(false);
            hive.setWasMovedAnEggsFrame(false);
            hive.getHoneyBatches().clear();
            hive.getEggFrames().extractEggBatchesForFrame();
            hive.getHoneyFrames().removeHoneyFrames();
            hive.removeBeesBatches();
        });
        return randomRemoveAHive(this.getHives());
    }

    public Integer checkInsectControl(LocalDate currentDate) {
        int dayOfMonth = currentDate.getDayOfMonth();
        return ((currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 8)
                && (dayOfMonth >= 10 && dayOfMonth <= 15) ||
                (dayOfMonth >= 20 && dayOfMonth <= 25))
                ? this.getHives().size()
                : 0;
    }

    public void doInsectControl() {
        for (Hive hive : hives) {
            hive.getBeesBatches().removeLast();
            hive.getBeesBatches().removeLast();
        }
    }

    public Integer checkFeedBees(LocalDate currentDate) {
        return (currentDate.getMonth() == Month.SEPTEMBER)
                ? this.getHives().size()
                : 0;
    }

    public void doFeedBees() {
        for (Hive hive : hives) {
            hive.getBeesBatches().removeLast();
            hive.getBeesBatches().removeLast();

        }
    }

    public void honeyHarvestedByHoneyType() {
        Map<HoneyType, Double> honeyHarvested = new HashMap<>();
        for (Hive hive : hives) {
            for (HoneyBatch honeyBatch : hive.getHoneyBatches()) {
                if (!honeyBatch.isProcessed()) {
                    HoneyType honeyType = honeyBatch.getHoneyType();
                    double kgOfHoney = honeyBatch.getKgOfHoney();
                    honeyHarvested.put(honeyType, honeyHarvested.getOrDefault(honeyType, 0.0) + kgOfHoney);
                    honeyBatch.setProcessed(true);
                }
            }
        }
        totalHarvestedHoney.setAcacia(totalHarvestedHoney.getAcacia() + honeyHarvested.getOrDefault(HoneyType.Acacia, 0.0));
        totalHarvestedHoney.setRapeseed(totalHarvestedHoney.getRapeseed() + honeyHarvested.getOrDefault(HoneyType.Rapeseed, 0.0));
        totalHarvestedHoney.setWildFlower(totalHarvestedHoney.getWildFlower() + honeyHarvested.getOrDefault(HoneyType.WildFlower, 0.0));
        totalHarvestedHoney.setLinden(totalHarvestedHoney.getLinden() + honeyHarvested.getOrDefault(HoneyType.Linden, 0.0));
        totalHarvestedHoney.setSunFlower(totalHarvestedHoney.getSunFlower() + honeyHarvested.getOrDefault(HoneyType.SunFlower, 0.0));
        totalHarvestedHoney.setFalseIndigo(totalHarvestedHoney.getFalseIndigo() + honeyHarvested.getOrDefault(HoneyType.FalseIndigo, 0.0));
    }

    public double getTotalKgHoneyHarvested() {
        return totalHarvestedHoney.getAcacia()
                + totalHarvestedHoney.getRapeseed()
                + totalHarvestedHoney.getWildFlower()
                + totalHarvestedHoney.getLinden()
                + totalHarvestedHoney.getSunFlower()
                + totalHarvestedHoney.getFalseIndigo();
    }

    public void updateHoneyStock(HarvestHoney soldHoneyData) {
        totalHarvestedHoney.setAcacia(totalHarvestedHoney.getAcacia() - soldHoneyData.Acacia);
        totalHarvestedHoney.setRapeseed(totalHarvestedHoney.getRapeseed() - soldHoneyData.Rapeseed);
        totalHarvestedHoney.setWildFlower(totalHarvestedHoney.getWildFlower() - soldHoneyData.WildFlower);
        totalHarvestedHoney.setLinden(totalHarvestedHoney.getLinden() - soldHoneyData.Linden);
        totalHarvestedHoney.setSunFlower(totalHarvestedHoney.getSunFlower() - soldHoneyData.SunFlower);
        totalHarvestedHoney.setFalseIndigo(totalHarvestedHoney.getFalseIndigo() - soldHoneyData.FalseIndigo);
    }

    public List<List<Integer>> checkIfCanMoveAnEggsFrame() {
        List<List<Integer>> hiveIdPairs = new ArrayList<>();
        List<Hive> hives = this.getHives();
        for (Hive sourceHive : hives) {
            if (sourceHive.getEggFrames().checkIfAll6EggsFrameAre80PercentFull()
                    && !sourceHive.itWasSplit
                    && !sourceHive.wasMovedAnEggsFrame) {

                for (Hive targetHive : hives) {
                    if (targetHive.itWasSplit && targetHive.getQueen().getAgeOfQueen() == 0) {
                        hiveIdPairs.add(Arrays.asList(sourceHive.getId(), targetHive.getId()));
                    }
                }
            }
        }
        return hiveIdPairs;
    }

    public void moveAnEggsFrame(List<List<Integer>> hiveIdPair) {
        for (List<Integer> hiveIds : hiveIdPair) {
            int sourceHiveId = hiveIds.get(0);
            int destinationHiveId = hiveIds.get(1);
            Hive sourceHive = this.getHiveById(sourceHiveId);
            Hive destinationHive = this.getHiveById(destinationHiveId);
            EggFrames sourceEggFrames = sourceHive.getEggFrames();
            EggFrames destinationEggFrames = destinationHive.getEggFrames();
            List<Integer> eggBatchesToMove = sourceEggFrames.extractEggBatchesForFrame();
            destinationEggFrames.addEggBatches(eggBatchesToMove);
            sourceHive.setWasMovedAnEggsFrame(true);
            System.out.println("acesta e stupul sursa " + sourceEggFrames);
            System.out.println("acestea sunt ramele destinatie" + destinationEggFrames);
        }
    }

    public void addHivesToApiary(List<Hive> newHives, LifeOfBees lifeOfBeesgame) {
        List<Hive> existingHives = lifeOfBeesgame.getApiary().getHives();
        for (Hive hive : newHives) {
            hive.setId(existingHives.size() + 1);
            existingHives.add(hive);
        }
    }
}

