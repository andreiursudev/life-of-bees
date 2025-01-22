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

    public Integer randomRemoveAHive() {
        if (hives.isEmpty()) return 0;
        Hive hiveToRemove = hives.remove(new Random().nextInt(hives.size()));
        logger.debug("Hive removed with hiveId: {}", hiveToRemove.getId());
        return hiveToRemove.getId();
    }

    public HarvestHoney getTotalHarvestedHoney() {
        return totalHarvestedHoney;
    }

    public void setTotalHarvestedHoney(HarvestHoney totalHarvestedHoney) {
        this.totalHarvestedHoney = totalHarvestedHoney;
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
        logger.debug("Starting createHive method with numberOfHives = {}", numberOfHives);
        Random random = new Random();

        List<Hive> hives = IntStream.rangeClosed(1, numberOfHives).mapToObj(i -> {
            logger.debug("Creating hive with id = {}", i);
            Hive hive = new Hive(
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
            );
            logger.debug("Hive created: {}", hive);
            return hive;
        }).collect(Collectors.toList());

        logger.debug("Finished creating {} hives", hives.size());
        return hives;
    }


    public void splitHive(Hive hive) {
        logger.debug("this is hive for splitting: {}", hive);
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

        logger.debug("this is parent hive: {}", hive);
        logger.debug("this is child hive: {}", newHive);
    }

    public Integer hibernate() {
        this.getHives().forEach(hive -> {
            hive.getQueen().setAgeOfQueen(hive.getQueen().getAgeOfQueen() + 1);
            hive.setItWasSplit(false);
            hive.getEggFrames().setWasMovedAnEggsFrame(false);
            hive.getHoneyBatches().clear();
            hive.getEggFrames().extractEggBatchesForFrame();
            hive.getHoneyFrames().removeHoneyFrames();
            hive.removeBeesBatches();
        });
        logger.debug("Completed hibernate method.");
        return randomRemoveAHive();
    }

    public Integer checkInsectControl(BeeTime currentDate) {
        return currentDate.timeForInsectControl()
                ? this.getHives().size()
                : 0;
    }

    public void removeLastTwoBeesBatches() {
        logger.debug("Starting removeLastTwoBeesBatches method.");
        for (Hive hive : hives) {
            logger.debug("Processing hive: {}", hive);
            hive.getBeesBatches().removeLast();
            logger.debug("Removed the last batch of bees from hive: {}", hive);
            hive.getBeesBatches().removeLast();
            logger.debug("Removed the second last batch of bees from hive: {}", hive);
        }
        logger.debug("Completed removeLastTwoBeesBatches method.");
    }

    public Integer checkFeedBees(BeeTime currentDate) {
        return (currentDate.getMonth() == Month.SEPTEMBER)
                ? this.getHives().size()
                : 0;
    }

    public void honeyHarvestedByHoneyType() {
        logger.debug("Starting honeyHarvestedByHoneyType method.");
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
        logger.debug("Completed honeyHarvestedByHoneyType method.");
    }


    public double getTotalKgHoneyHarvested() {
        double totalKg = totalHarvestedHoney.getHoneyTypeToAmount().values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        logger.debug("Finished getTotalKgHoneyHarvested. Total kg of honey harvested: {}", totalKg);
        return totalKg;
    }


    public void updateHoneyStock(HarvestHoney soldHoneyData) {
        logger.debug("Starting updateHoneyStock method with soldHoneyData = {}", soldHoneyData);

        soldHoneyData.getHoneyTypeToAmount().forEach((honeyType, amountSold) -> {
            double currentAmount = totalHarvestedHoney.getHoneyAmount(honeyType);
            totalHarvestedHoney.setHoneyAmount(honeyType, currentAmount - amountSold);
            logger.debug("Updated honey stock for {}: current amount = {}, amount sold = {}", honeyType, currentAmount, amountSold);
        });

        logger.debug("Finished updateHoneyStock. Updated totalHarvestedHoney = {}", totalHarvestedHoney);
    }


    public List<List<Integer>> checkIfCanMoveAnEggsFrame() {
        List<Hive> hives = this.getHives();

        return hives.stream()
                .filter(sourceHive -> sourceHive.getEggFrames().checkIfAll6EggsFrameAre80PercentFull()
                        && !sourceHive.itWasSplit
                        && !sourceHive.getEggFrames().wasMovedAnEggsFrame)
                .flatMap(sourceHive -> hives.stream()
                        .filter(targetHive -> targetHive.itWasSplit && targetHive.getQueen().getAgeOfQueen() == 0)
                        .map(targetHive -> Arrays.asList(sourceHive.getId(), targetHive.getId()))
                )
                .collect(Collectors.toList());
    }


    public void moveAnEggsFrame(List<List<Integer>> hiveIdPair) {
        logger.debug("Starting moveAnEggsFrame method with hiveIdPair: {}", hiveIdPair);
        hiveIdPair.forEach(hiveIds -> {
            Hive sourceHive = this.getHiveById(hiveIds.get(0));
            Hive destinationHive = this.getHiveById(hiveIds.get(1));
            EggFrames sourceEggFrames = sourceHive.getEggFrames();
            EggFrames destinationEggFrame = destinationHive.getEggFrames();
            sourceEggFrames.moveAnEggFrame(destinationEggFrame);
        });
        logger.debug("Finished moveAnEggsFrame method.");
    }


    public void addHivesToApiary(List<Hive> newHives, LifeOfBees lifeOfBeesGame) {
        logger.debug("Starting addHivesToApiary with list of hives {} and game {} ", newHives, lifeOfBeesGame);
        List<Hive> existingHives = lifeOfBeesGame.getApiary().getHives();
        Set<Integer> existingHiveIds = existingHives.stream()
                .map(Hive::getId)
                .collect(Collectors.toSet());
        for (Hive hive : newHives) {
            int newHiveId = 1;
            while (existingHiveIds.contains(newHiveId)) {
                newHiveId++;
            }
            hive.setId(newHiveId);
            existingHiveIds.add(newHiveId);
            existingHives.add(hive);
        }
        logger.debug("Finished addHivesToApiary method");
    }
}
