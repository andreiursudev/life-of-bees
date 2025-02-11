package com.marianbastiurea.lifeofbees.bees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Apiary {

    private static final Logger logger = LoggerFactory.getLogger(Apiary.class);
    private Hives hives;
    private HarvestHoney totalHarvestedHoney;

    public Apiary(Hives hives) {
        this.hives = hives;
        this.totalHarvestedHoney = new HarvestHoney(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    /* use only for test */
    public Apiary(HarvestHoney harvestHoney) {
        this.totalHarvestedHoney = harvestHoney;
    }


    public HarvestHoney getTotalHarvestedHoney() {
        return totalHarvestedHoney;
    }


    public Hives getHives() {
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
        List<Hive> hiveList = hives.getHives();
        Map<HoneyType, Double> honeyHarvested = hiveList.stream()
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


}
