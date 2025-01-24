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

    //TODO inlocuieste List<Hive> hives cu  Hives hives.
    private List<Hive> hives;
    private HarvestHoney totalHarvestedHoney;

    public Apiary(List<Hive> hives) {
        this.hives = hives;
        this.totalHarvestedHoney = new HarvestHoney(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
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





}
