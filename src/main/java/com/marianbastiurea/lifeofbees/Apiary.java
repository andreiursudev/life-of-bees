package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.eggframe.EggFrame;

import java.time.LocalDate;
import java.util.*;


public class Apiary {
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
        return null;
    }

    public Map<String, Object> getTotalHarvestedHoney() {
        return totalHarvestedHoney;
    }

    public void splitHive(Hive hive) {
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

    public List<ActionOfTheWeek> hibernate(LifeOfBees lifeOfBeesGame, List<ActionOfTheWeek> actionsOfTheWeek) {
        LocalDate date = lifeOfBeesGame.getCurrentDate();
        Apiary apiary = lifeOfBeesGame.getApiary();
        List<Hive> hives = lifeOfBeesGame.getApiary().getHives();
        for (Hive hive : hives) {
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
        Random random = new Random();
        int indexToRemove = random.nextInt(hives.size());
        Hive hiveToRemove = hives.get(indexToRemove);
        int hiveIdRemoved = hiveToRemove.getId();
        hives.remove(hiveToRemove);
        Map<String, Object> data = new HashMap<>();
        data.put("totalHives", apiary.getHives().size());
        data.put("hibernateStartDate", date.toString());
        data.put("hiveIds", List.of(hiveIdRemoved));
        ActionOfTheWeek actionInstance = new ActionOfTheWeek();
        actionInstance.addOrUpdateAction("HIBERNATE", hiveIdRemoved, data, actionsOfTheWeek);

        return actionsOfTheWeek;
    }

    public List<ActionOfTheWeek> checkInsectControl(HarvestingMonths month, int dayOfMonth, List<ActionOfTheWeek> actionsOfTheWeek) {
        if ((month.equals(HarvestingMonths.APRIL) || month.equals(HarvestingMonths.MAY) ||
                month.equals(HarvestingMonths.JUNE) || month.equals(HarvestingMonths.JULY)) &&
                (dayOfMonth == 11 || dayOfMonth == 21)) {
            Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("INSECT_CONTROL", actionsOfTheWeek).getData();
            ActionOfTheWeek actionInstance = new ActionOfTheWeek();
            actionInstance.addOrUpdateAction("INSECT_CONTROL", this.getNumberOfHives(), data, actionsOfTheWeek);
        }
        return actionsOfTheWeek;
    }

    public void doInsectControl(String answer, LifeOfBees lifeOfBeesGame) {
        if ("yes".equals(answer)) {
            lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - (numberOfHives * 10));
        } else {
            for (Hive hive : hives) {
                hive.setNumberOfBees((int) (hive.getNumberOfBees() * 0.09));
            }
        }
    }

    public List<ActionOfTheWeek> checkFeedBees(HarvestingMonths month, int dayOfMonth, List<ActionOfTheWeek> actionsOfTheWeek) {
        if (month.equals(HarvestingMonths.SEPTEMBER) &&
                (dayOfMonth == 1)) {
            Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("FEED_BEES", actionsOfTheWeek).getData();
            ActionOfTheWeek actionInstance = new ActionOfTheWeek();
            actionInstance.addOrUpdateAction("FEED_BEES", this.getNumberOfHives(), data, actionsOfTheWeek);

        }
        return actionsOfTheWeek;
    }

    public void doFeedBees(String answer, LifeOfBees lifeOfBeesGame) {
        if ("yes".equals(answer)) {
            lifeOfBeesGame.setMoneyInTheBank(lifeOfBeesGame.getMoneyInTheBank() - numberOfHives * 7);
        } else {
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

    public double getTotalKgHoneyHarvested() {
        return getTotalHarvestedHoney().values().stream()
                .filter(Objects::nonNull)
                .mapToDouble(value -> value instanceof Double ? (Double) value : 0.0)
                .sum();
    }

    public void updateHoneyStock(Map<String, Double> soldHoneyData) {
        for (Map.Entry<String, Double> entry : soldHoneyData.entrySet()) {
            String honeyType = entry.getKey();
            double soldQuantity = entry.getValue();
            double currentQuantity = (double) totalHarvestedHoney.getOrDefault(honeyType, 0.0);
            totalHarvestedHoney.put(honeyType, currentQuantity - soldQuantity);
        }
    }

    public List<Hive> createHive(int numberOfHives, LocalDate date) {
        int day = date.getDayOfMonth();
        Honey honey = new Honey();
        HarvestingMonths month = honey.getHarvestingMonth(date);
        String honeyType = honey.honeyType(month, day);
        double kgOfHoney = 0;
        Random random = new Random();
        List<Hive> newHives = new ArrayList<>();
        for (int i = 1; i <= numberOfHives; i++) {
            int ageOfQueen = random.nextInt(1, 6);
            List<EggFrame> eggFrames = new ArrayList<>();
            for (int j = 0; j < random.nextInt(3, 4); j++) {
                eggFrames.add(new EggFrame());
            }
            List<HoneyFrame> honeyFrames = new ArrayList<>();
            for (int k = 0; k < random.nextInt(3, 5); k++) {
                honeyFrames.add(new HoneyFrame(random.nextDouble(2.5, 3), honeyType));
            }
            int numberOfBees = random.nextInt(2000, 2500) * (honeyFrames.size() + eggFrames.size());
            Hive hive = new Hive(
                    null,  // Setăm apiary-ul la null pentru a nu-l atașa momentan
                    newHives.size() + 1,  // ID temporar
                    false,
                    false,
                    false,
                    eggFrames,
                    honeyFrames,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Honey(honeyType),
                    new Queen(ageOfQueen),
                    numberOfBees,
                    kgOfHoney
            );
            newHives.add(hive);
        }
        return newHives;
    }
}

