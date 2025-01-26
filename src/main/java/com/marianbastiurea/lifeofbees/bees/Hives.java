package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hives {
    private List<Hive> hives;
    private static final Logger logger = LoggerFactory.getLogger(Apiary.class);
    private static final Random RANDOM = new Random();

    private BeeTime currentDate;

    public Hives() {
    }

    public void setHives(List<Hive> hives) {
        this.hives = hives;
    }

    public Hives(List<Hive> hiveList, BeeTime date) {
        this.hives = hiveList;
        this.currentDate = date;
    }

    public Hives(List<Hive> hives) {
        this.hives = hives;
    }

    public Hives(Hive... hives) {
        this.hives = new ArrayList<>(Arrays.asList(hives));
    }

    public void splitHive(Integer hiveId) {
        Hive hive = getHiveById(hiveId);
        if (!hive.getEggFrames().isFullEggFrames() || hive.isItWasSplit()) return;
        hive.setItWasSplit(true);
        hives.add(new Hive(
                hives.size() + 1,
                true,
                hive.getEggFrames().splitEggFrames(),
                hive.getHoneyFrames().splitHoneyFrames(),
                hive.getBeesBatches().splitBeesBatches(),
                new ArrayList<>(),
                new Queen(0),
                false));
    }

    public Hive getHiveById(Integer hiveId) {
        for (Hive hive : hives) {
            if (hive.getId() == hiveId) {
                return hive;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hives hives1 = (Hives) o;
        return Objects.equals(hives, hives1.hives);
    }

    @Override
    public String toString() {
        return "Hives{" +
                "hives=" + hives +
                '}';
    }

    public List<Hive> getHives() {
        return hives;
    }

    public boolean isEmpty() {
        return hives.isEmpty();
    }


    public Integer randomRemoveAHive() {
        if (hives.isEmpty()) {
            return 0;
        }
        int indexToRemove = RANDOM.nextInt(hives.size());
        Hive hiveToRemove = hives.remove(indexToRemove);
        logger.debug("Hive removed with hiveId: {}. Remaining hives: {}", hiveToRemove.getId(), hives.size());
        return hiveToRemove.getId();
    }

    public Integer checkInsectControl(BeeTime currentDate) {
        return currentDate.timeForInsectControl()
                ? this.getHives().size()
                : 0;
    }

    public Integer checkFeedBees() {
        return (currentDate.getMonth() == Month.SEPTEMBER)
                ? this.getHives().size()
                : 0;
    }

    public List<List<Integer>> checkIfCanMoveAnEggsFrame() {
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


    public void addNewHivesToHives(Hives newHives, LifeOfBees lifeOfBeesGame) {
        logger.debug("Starting addHivesToApiary with list of hives {} and game {} ", newHives, lifeOfBeesGame);
        List<Hive> existingHives = lifeOfBeesGame.getApiary().getHives().getHives();
        Set<Integer> existingHiveIds = existingHives.stream()
                .map(Hive::getId)
                .collect(Collectors.toSet());
        for (Hive hive : newHives.getHives()) {
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


    public Integer hibernate() {
        Integer removedHive = null;
        if (currentDate.isEndOfSeason()) {
            this.getHives().forEach(hive -> {
                hive.getQueen().setAgeOfQueen(hive.getQueen().getAgeOfQueen() + 1);
                hive.setItWasSplit(false);
                hive.getEggFrames().setWasMovedAnEggsFrame(false);
                hive.getHoneyBatches().clear();
                hive.getEggFrames().extractEggBatchesForFrame();
                hive.getHoneyFrames().removeHoneyFrames();
                hive.getBeesBatches().removeBeesBatches();
            });
            logger.debug("Completed hibernate method.");
            currentDate.changeYear();
            removedHive = randomRemoveAHive();
        }
        return removedHive;
    }


    public static Hives createHives(int numberOfHives, BeeTime date) {
        List<Hive> hiveList = IntStream.rangeClosed(1, numberOfHives).mapToObj(i -> {
            logger.debug("Creating hive with id = {}", i);
            BeesBatches beesBatches = new BeesBatches(
                    IntStream.range(0, 30)
                            .mapToObj(k -> RANDOM.nextInt(600, 700))
                            .collect(Collectors.toCollection(LinkedList::new))
            );
            Hive hive = new Hive(
                    i,
                    false,
                    EggFrames.getRandomEggFrames(),
                    HoneyFrames.getRandomHoneyFrames(),
                    beesBatches,
                    new ArrayList<>(),
                    new Queen(RANDOM.nextInt(1, 6)),
                    false
            );

            logger.debug("Hive created: {}", hive);
            return hive;
        }).collect(Collectors.toList());

        logger.debug("Finished creating {} hives", hiveList.size());

        return new Hives(hiveList, date);
    }


    public void addAll(List<Hive> newHives) {
        this.hives.addAll(newHives);
    }

    public void iterateOneDay(double weatherIndex) {
        for (Hive hive : hives) {
            hive.iterateOneDay(currentDate, weatherIndex);
        }
        currentDate.addDay();
    }

    public BeeTime getCurrentDate() {
        return currentDate;
    }
}
