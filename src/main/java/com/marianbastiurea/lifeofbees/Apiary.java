package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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


    public Apiary() {
        hives = new ArrayList<>();
        harvestedHoneys = new ArrayList<>();
        numberOfHives = numberOfHives;
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

    public void setHives(List<Hive> hives) {
        this.hives = hives;
    }

    public void addHoneyHarvested(List<HarvestedHoney> harvestedHoneys) {
        this.harvestedHoneys.addAll(harvestedHoneys);
    }

    @Override
    public String toString() {
        return "{" +
                "numberOfHives=" + this.numberOfHives +
                ", hives=" + this.hives +
                ", honeys harvested=" + this.harvestedHoneys +
                '}';
    }

    public void splitHive() {

        /*
        this method will split a hive in two equal half. First will check boolean itWasSplit. If this is false
        hive will be split. A hive could be split only once in a year.
         */

        List<Hive> newHives = new ArrayList<>();
        for (Hive hive : hives) {
            if (hive.getNumberOfEggsFrame() == 6 && !hive.isItWasSplit()) {
                System.out.println("Now old and new frames are full. Hive will be split in two hives.");
                Hive newHive = new Hive(this.getNumberOfHives() + 1,
                        true, hive.getNumberOfHoneyFrame(), hive.getNumberOfEggsFrame(),
                        (hive.getNumberOfBees() / 2), new Queen(), new Honey()
                );
                hive.setNumberOfBees(hive.getNumberOfBees() / 2);
                hive.setItWasSplit(true);

                newHive.getQueen().setAgeOfQueen(0);
                newHive.getHoney().setHoneyType("Acacia");

                newHive.setApiary(this);
                newHive.setBeesBatches(hive.getBeesBatches().subList(0, 0));

                List<EggsFrame> hiveEggsFrames = hive.getEggsFrames();
                List<EggsFrame> newHiveEggsFrames = new ArrayList<>();
                for (EggsFrame eggsFrame : hiveEggsFrames) {
                    int eggsToTransfer = eggsFrame.getNumberOfEggs() / 2;
                    EggsFrame newHiveFrame = new EggsFrame(eggsFrame.getNumberOfEggsFrame(), eggsToTransfer);
                    newHiveEggsFrames.add(newHiveFrame);
                    eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() - eggsToTransfer);
                }
                newHive.setEggsFrames(newHiveEggsFrames);

                List<HoneyFrame> hiveHoneyFrames = hive.getHoneyFrames();
                List<HoneyFrame> newHiveHoneyFrames = new ArrayList<>();
                for (HoneyFrame honeyFrame : hiveHoneyFrames) {
                    HoneyFrame newHiveFrame = new HoneyFrame(honeyFrame.getKgOfHoney() / 2, honeyFrame.getHoneyType());
                    newHiveHoneyFrames.add(newHiveFrame);
                    honeyFrame.setKgOfHoney(honeyFrame.getKgOfHoney() / 2);
                }
                newHive.setHoneyFrames(newHiveHoneyFrames);

                List<EggsBatch> hiveEggsBatches = hive.getEggsBatches();
                List<EggsBatch> newHiveEggsBatches = new ArrayList<>();
                for (EggsBatch eggsBatch : hiveEggsBatches) {
                    int eggsToTransfer = eggsBatch.getNumberOfEggs() / 2;
                    EggsBatch newHiveBatch = new EggsBatch(eggsToTransfer, eggsBatch.getCreationDate());
                    newHiveEggsBatches.add(newHiveBatch);
                    eggsBatch.setNumberOfEggs(eggsBatch.getNumberOfEggs() - eggsToTransfer);
                }
                newHive.setEggsBatches(newHiveEggsBatches);

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
        }
        hives.addAll(newHives);
        System.out.println("your apiary contains " + this.getNumberOfHives() + " hives");
        System.out.println(" your apiary is number " + this);
    }

    public void honeyHarvestedByHoneyType(Date currentDate) {
        List<HarvestedHoney> rapeseedHoney = new ArrayList<>();
        List<HarvestedHoney> acaciaHoney = new ArrayList<>();
        List<HarvestedHoney> wildFlowerHoney = new ArrayList<>();
        List<HarvestedHoney> lindenHoney = new ArrayList<>();
        List<HarvestedHoney> sunflowerHoney = new ArrayList<>();
        List<HarvestedHoney> falseIndigoHoney = new ArrayList<>();


        for (Hive hive : hives) {
            List<HoneyBatch> hiveHoneyBatches = hive.getHoneyBatches();
            for (HoneyBatch honeyBatch : hiveHoneyBatches) {
                HarvestedHoney harvestedHoney = new HarvestedHoney(
                        hive.getId(),
                        honeyBatch.getHoneyType(),
                        honeyBatch.getKgOfHoney(),
                        honeyBatch.getCreationDate()
                );

                switch (honeyBatch.getHoneyType()) {
                    case "Acacia":
                        acaciaHoney.add(harvestedHoney);
                        break;
                    case "Rapeseed":
                        rapeseedHoney.add(harvestedHoney);
                        break;
                    case "WildFlower":
                        wildFlowerHoney.add(harvestedHoney);
                        break;
                    case "Linden":
                        lindenHoney.add(harvestedHoney);
                        break;
                    case "SunFlower":
                        sunflowerHoney.add(harvestedHoney);
                        break;
                    case "FalseIndigo":
                        falseIndigoHoney.add(harvestedHoney);
                        break;
                    default:
                        // Handle unknown honey types if necessary
                        break;
                }
            }
        }
    }
}