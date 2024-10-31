package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.eggframe.EggFrame;

import java.util.*;

public class Apiary {
    /* An apiary is a location where beehives of honey bees are kept.
    Our apiary will start with 1 beehive and we limit this number at 100.
    Our apiary will be in it's first year of existence and we will stop our iteration at year 100.
    At few years acacia or linden forest because of harsh weather will not have flower for bees
    and no honey from this two forest.

     */
    private int numberOfHives;
    private List<Hive> hives;
    private List<HarvestedHoney> harvestedHoneys;
    private LifeOfBees lifeOfBees;
    private Map<String, Object> totalHarvestedHoney = new HashMap<>();

    public Apiary(List<Hive> hives, List<HarvestedHoney> harvestedHoneys) {
        this.hives = hives;
        this.harvestedHoneys = harvestedHoneys;
    }

    public Apiary() {
        totalHarvestedHoney.put("Acacia", 0.0);
        totalHarvestedHoney.put("Rapeseed", 0.0);
        totalHarvestedHoney.put("WildFlower", 0.0);
        totalHarvestedHoney.put("Linden", 0.0);
        totalHarvestedHoney.put("SunFlower", 0.0);
        totalHarvestedHoney.put("FalseIndigo", 0.0);
    }


    public int getNumberOfHives() {
        return numberOfHives;
    }

    public void setNumberOfHives(int numberOfHives) {
        this.numberOfHives = numberOfHives;
    }

    public List<HarvestedHoney> getHarvestedHoneys() {
        return harvestedHoneys;
    }

    public void setHarvestedHoneys(List<HarvestedHoney> harvestedHoneys) {
        this.harvestedHoneys = harvestedHoneys;
    }

    public List<Hive> getHives() {
        return hives;
    }

    public void addHoneyHarvested(List<HarvestedHoney> harvestedHoneys) {
        this.harvestedHoneys.addAll(harvestedHoneys);
    }

    @Override
    public String toString() {
        return "{" +
                "numberOfHives=" + this.getHives().size() +
                ", hives=" + this.hives +
                ", honeys harvested=" + this.harvestedHoneys +
                '}';
    }


    public Hive getHiveById(Integer hiveId) {
        for (Hive hive : hives) {
            if (hive.getId() == hiveId) {
                return hive;
            }
        }
        return null;  // Dacă nu găsește stupul, returnează null
    }

    public Map<String, Object> getTotalHarvestedHoney() {
        return totalHarvestedHoney;
    }

    public void setTotalHarvestedHoney(Map<String, Object> totalHarvestedHoney) {
        this.totalHarvestedHoney = totalHarvestedHoney;
    }

    public void splitHive(Hive hive) {

        /*
        this method will split a hive in two equal half. First will check boolean itWasSplit. If this is false
        hive will be split. A hive could be split only once in a year.
         */

        List<Hive> newHives = new ArrayList<>();

        if (hive.getEggsFrames().size() == 6 && !hive.isItWasSplit()) {
            hive.setNumberOfBees(hive.getNumberOfBees() / 2);
            hive.setItWasSplit(true);
            hive.setAnswerIfWantToSplit(true);


            Hive newHive = new Hive(this, this.getHives().size() + 1, true, true, hive.getNumberOfBees() / 2, new Queen());
            newHive.getQueen().setAgeOfQueen(0);
            newHive.setHoney(hive.getHoney());
            newHive.setApiary(this);
            newHive.setWasMovedAnEggsFrame(false);
            newHive.setBeesBatches(hive.getBeesBatches().subList(0, 0));

            List<EggFrame> newHiveEggFrames = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                EggFrame frameToMove = hive.getEggsFrames().remove(hive.getEggsFrames().size() - 1);
                newHiveEggFrames.add(frameToMove);

            }
            newHive.setEggsFrames(newHiveEggFrames);

            List<HoneyFrame> newHiveHoneyFrames = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                HoneyFrame frameToMove = hive.getHoneyFrames().remove(hive.getHoneyFrames().size() - 1);
                newHiveHoneyFrames.add(frameToMove);
            }
            newHive.setHoneyFrames(newHiveHoneyFrames);


            List<BeesBatch> hiveBeesBatches = hive.getBeesBatches();
            List<BeesBatch> newHiveBeesBatches = new ArrayList<>();
            for (BeesBatch beesBatch : hiveBeesBatches) {
                int beesToTransfer = beesBatch.getNumberOfBees() / 2;
                BeesBatch newHiveBatch = new BeesBatch(beesToTransfer, beesBatch.getCreationDate());
                newHiveBeesBatches.add(newHiveBatch);
                beesBatch.setNumberOfBees(beesBatch.getNumberOfBees() - beesToTransfer);
            }
            newHive.setBeesBatches(newHiveBeesBatches);

            newHive.setHoneyBatches(new ArrayList<>());

            newHives.add(newHive);
            this.setNumberOfHives(this.getNumberOfHives() + 1);

        }

        hives.addAll(newHives);
    }


    public void hibernate(Hive hive) {
        hive.getQueen().setAgeOfQueen(hive.getQueen().getAgeOfQueen() + 1);
        hive.setItWasSplit(false);
        hive.setAnswerIfWantToSplit(false);
        hive.setWasMovedAnEggsFrame(false);
        hive.getHoneyBatches().clear();
        hive.getEggsFrames().remove(hive.getEggsFrames().size() - 1);
        hive.getEggsFrames().remove(hive.getEggsFrames().size() - 1);
        hive.getHoneyFrames().remove(hive.getHoneyFrames().size() - 1);
        hive.getHoneyFrames().remove(hive.getHoneyFrames().size() - 1);
    }

    public boolean checkInsectControl(HarvestingMonths month, int dayOfMonth) {
        if ((month.equals(HarvestingMonths.APRIL) || month.equals(HarvestingMonths.MAY) ||
                month.equals(HarvestingMonths.JUNE) || month.equals(HarvestingMonths.JULY)) &&
                (dayOfMonth == 11 || dayOfMonth == 21)) {
            return true;
        }
        return false;
    }

    public void doInsectControl(String answer, LifeOfBees lifeOfBeesGame) {
        if ("yes".equals(answer)) {
            // Scade costul de control al insectelor din bani
            lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - (numberOfHives * 10));
        } else {
            // Redu numărul de albine din fiecare stup
            for (Hive hive : hives) {
                hive.setNumberOfBees((int) (hive.getNumberOfBees() * 0.09));
            }
        }
    }

    public boolean checkFeedBees(HarvestingMonths month, int dayOfMonth) {
        if (month.equals(HarvestingMonths.SEPTEMBER) &&
                (dayOfMonth == 1)) {
            return true;
        }
        return false;
    }

    public void doFeedBees(String answer, LifeOfBees lifeOfBeesGame) {
        if ("yes".equals(answer)) {
            // Scade costul hranire al insectelor din bani. costul e 1$/zi/stup
            lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - numberOfHives * 7);
        } else {
            // Redu numărul de albine din fiecare stup
            for (Hive hive : hives) {
                for (int day = 0; day < 7; day++) {
                    hive.setNumberOfBees((int) (hive.getNumberOfBees() * 0.95));
                }
            }
        }
    }


    public void moveAnEggsFrame(List<List<Integer>> hiveIdPair) {

        for (List<Integer> hiveIds : hiveIdPair) {
            int sourceHiveId = hiveIds.get(0);
            int destinationHiveId = hiveIds.get(1);

            Hive sourceHive = this.getHiveById(sourceHiveId);
            Hive destinationHive = this.getHiveById(destinationHiveId);
            EggFrame frameToMove = sourceHive.getEggsFrames().remove(sourceHive.getEggsFrames().size() - 1);
            destinationHive.getEggsFrames().add(frameToMove);
            sourceHive.setWasMovedAnEggsFrame(true);
        }
    }

    public void honeyHarvestedByHoneyType() {
        for (Hive hive : hives) {
            for (HoneyBatch honeyBatch : hive.getHoneyBatches()) {
                if (!honeyBatch.isProcessed()) {
                    String honeyType = honeyBatch.getHoneyType();
                    double kgOfHoney = honeyBatch.getKgOfHoney();
                    getTotalHarvestedHoney().merge(honeyType, kgOfHoney, (oldValue, newValue) ->
                            Double.valueOf(oldValue.toString()) + (Double) newValue
                    );
                    honeyBatch.setProcessed(true);
                }
            }
        }
    }


    public void updateHoneyStock(Map<String, Object> soldHoneyData) {
        for (Map.Entry<String, Object> entry : soldHoneyData.entrySet()) {
            String honeyType = entry.getKey();
            double soldQuantity = ((Number) entry.getValue()).doubleValue();
            totalHarvestedHoney.merge(honeyType, soldQuantity, (currentQuantity, quantitySold) ->
                    Double.valueOf(currentQuantity.toString()) - (Double) quantitySold
            );
        }
    }


}

