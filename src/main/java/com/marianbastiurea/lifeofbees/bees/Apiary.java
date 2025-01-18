package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Apiary {

    private static final Logger logger = LoggerFactory.getLogger(Apiary.class);
    private List<Hive> hives;
    private HarvestHoney totalHarvestedHoney;

    public Apiary(List<Hive> hives) {
        this.hives = hives;
        this.totalHarvestedHoney = new HarvestHoney(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    private Integer randomRemoveAHive() {
        if (hives.isEmpty()) return 0;
        Hive hiveToRemove = hives.remove(new Random().nextInt(hives.size()));
        return hiveToRemove.getId();
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

    public List<Hive> createHive(int numberOfHives) {
        Random random = new Random();
        return IntStream.rangeClosed(1, numberOfHives).mapToObj(i -> new Hive(
                i,
                false,
                false,
                EggFrames.getRandomEggFrames(),
                HoneyFrames.getRandomHoneyFrames(),
                IntStream.range(0, 30)
                        .mapToObj(k -> random.nextInt(600, 700))
                        .collect(Collectors.toCollection(LinkedList::new)),
                new ArrayList<>(),
                new Queen(random.nextInt(1, 6)),
                false
        )).collect(Collectors.toList());
    }

    public void splitHive(Hive hive) {
        if (!hive.getEggFrames().isFullEggFrames() || hive.isItWasSplit()) return;

        hive.setItWasSplit(true);

        Hive newHive = new Hive(
                this.getHives().size() + 1,
                true,
                false,
                hive.getEggFrames().splitEggFrames(),
                hive.getHoneyFrames().splitHoneyFrames(),
                hive.splitBeesBatches(),
                new ArrayList<>(),
                new Queen(0),
                false
        );

        hives.add(newHive);

        logger.info("this is parent hive: {}", hive);
        logger.info("this is child hive: {}", newHive);
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
        return randomRemoveAHive();
    }

    public Integer checkInsectControl(BeeTime currentDate) {
        return currentDate.timeForInsectControl(currentDate)
                ? this.getHives().size()
                : 0;
    }

    public void removeLastTwoBeesBatches() {
        for (Hive hive : hives) {
            hive.getBeesBatches().removeLast();
            hive.getBeesBatches().removeLast();
        }
    }

    public Integer checkFeedBees(BeeTime currentDate) {
        return (currentDate.getMonth() == Month.SEPTEMBER)
                ? this.getHives().size()
                : 0;
    }

    public void honeyHarvestedByHoneyType() {
        Map<HoneyType, Double> honeyHarvested = hives.stream()
                .flatMap(hive -> hive.getHoneyBatches().stream())
                .filter(honeyBatch -> !honeyBatch.isProcessed())
                .peek(honeyBatch -> honeyBatch.setProcessed(true))
                .collect(Collectors.groupingBy(
                        HoneyBatch::getHoneyType,
                        Collectors.summingDouble(HoneyBatch::getKgOfHoney)
                ));

        honeyHarvested.forEach((honeyType, amount) -> {
            double currentAmount = totalHarvestedHoney.getHoneyAmount(honeyType);
            totalHarvestedHoney.setHoneyAmount(honeyType, currentAmount + amount);
        });
    }

    public double getTotalKgHoneyHarvested() {
        return totalHarvestedHoney.getHoneyTypeToAmount().values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public void updateHoneyStock(HarvestHoney soldHoneyData) {
        soldHoneyData.getHoneyTypeToAmount().forEach((honeyType, amountSold) -> {
            double currentAmount = totalHarvestedHoney.getHoneyAmount(honeyType);
            totalHarvestedHoney.setHoneyAmount(honeyType, currentAmount - amountSold);
        });
    }

    public List<List<Integer>> checkIfCanMoveAnEggsFrame() {
        List<Hive> hives = this.getHives();

        return hives.stream()
                .filter(sourceHive -> sourceHive.getEggFrames().checkIfAll6EggsFrameAre80PercentFull()
                        && !sourceHive.itWasSplit
                        && !sourceHive.wasMovedAnEggsFrame)
                .flatMap(sourceHive -> hives.stream()
                        .filter(targetHive -> targetHive.itWasSplit && targetHive.getQueen().getAgeOfQueen() == 0)
                        .map(targetHive -> Arrays.asList(sourceHive.getId(), targetHive.getId()))
                )
                .collect(Collectors.toList());
    }


    public void moveAnEggsFrame(List<List<Integer>> hiveIdPair) {
        hiveIdPair.forEach(hiveIds -> {
            Hive sourceHive = this.getHiveById(hiveIds.get(0));
            Hive destinationHive = this.getHiveById(hiveIds.get(1));
            //TODO mutarea unui egg frame este divizata in urmatoarele 3 linii de cod, nu e ok.
            // Fa totul intro singura metoda void moveAnEggFrame(EggFrames eggFrames) in clasa EggFrames
            List<Integer> eggBatchesToMove = sourceHive.getEggFrames().extractEggBatchesForFrame();
            destinationHive.getEggFrames().addEggBatches(eggBatchesToMove);
            sourceHive.setWasMovedAnEggsFrame(true);
            System.out.println("Acesta e stupul sursa: " + sourceHive.getEggFrames());
            System.out.println("Acestea sunt ramele destinatie: " + destinationHive.getEggFrames());
        });
    }


    public void addHivesToApiary(List<Hive> newHives, LifeOfBees lifeOfBeesgame) {
        List<Hive> existingHives = lifeOfBeesgame.getApiary().getHives();
        for (Hive hive : newHives) {
            hive.setId(existingHives.size() + 1);
            existingHives.add(hive);
        }
    }
}

