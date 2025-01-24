package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hives {
    private List<Hive> hives;

    public Hives(){
        this.hives = new ArrayList<>();
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
                hive.splitBeesBatches(),
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
        if (hives.isEmpty()) return 0;
        Hive hiveToRemove = hives.remove(new Random().nextInt(hives.size()));
        logger.debug("Hive removed with hiveId: {}", hiveToRemove.getId());
        return hiveToRemove.getId();
    }

    public Integer checkInsectControl(BeeTime currentDate) {
        return currentDate.timeForInsectControl()
                ? this.getHives().size()
                : 0;
    }

    public Integer checkFeedBees(BeeTime currentDate) {
        return (currentDate.getMonth() == Month.SEPTEMBER)
                ? this.getHives().size()
                : 0;
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


    public List<Hive> createHive(int numberOfHives) {
        logger.debug("Starting createHive method with numberOfHives = {}", numberOfHives);
        Random random = new Random();

        List<Hive> hives = IntStream.rangeClosed(1, numberOfHives).mapToObj(i -> {
            logger.debug("Creating hive with id = {}", i);
            Hive hive = new Hive(
                    i,
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

}
