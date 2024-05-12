package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;

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
        this.hives = new ArrayList<>();
        this.harvestedHoneys = new ArrayList<>();
        this.numberOfHives = numberOfHives;
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

//    public Apiary(List<Hive> hives) {
//        this.hives = hives;
//    }

    public List<Hive> getHives() {
        return hives;
    }

    public void setHives(List<Hive> hives) {
        this.hives = hives;
    }


//    // Add a hive to the apiary
//    public void addHive(Hive hive) {
//        hives.add(hive);
//    }


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
            if (hive.getNumberOfEggsFrame() == 6 && hive.isItWasSplit() == false) {
                Hive newHive = new Hive();// changes  Hive new Hive=new Hive
                newHive.setId(this.getNumberOfHives() + 1);
                newHive.setItWasSplit(true);
                newHive.setNumberOfEggsFrame(hive.getNumberOfEggsFrame());
                newHive.setNumberOfHoneyFrame(hive.getNumberOfHoneyFrame());
                newHive.setNumberOfBees(hive.getNumberOfBees() / 2);
                hive.setNumberOfBees(hive.getNumberOfBees() / 2);
                hive.setItWasSplit(true);
                Queen queen = new Queen();
                Honey honey = new Honey();
                newHive.setQueen(queen);
                newHive.setHoney(honey);
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
                    double honeyToTransfer = honeyFrame.getKgOfHoney() / 2;
                    HoneyFrame newHiveFrame = new HoneyFrame(honeyToTransfer, honeyFrame.getHoneyType());
                    newHiveHoneyFrames.add(newHiveFrame);
                    honeyFrame.setKgOfHoney(honeyFrame.getKgOfHoney() - honeyToTransfer);
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

                newHives.add(newHive);
                this.setNumberOfHives(this.getNumberOfHives() + 1);

            }
        }
        hives.addAll(newHives);
        System.out.println("your apiary contains " + this.getNumberOfHives() + " hives");
        System.out.println(" your apiary is number " + this);
    }

    public void collectHoneyFromHives() {
        List<HarvestedHoney> harvestedHoney = new ArrayList<>();
        int hiveCounter = 0;
        double totalKgOfHoneyPerHive = 0;
        for (Hive hive : hives) {
            List<HoneyFrame> hiveHoneyFrames = hive.getHoneyFrames();
            for (HoneyFrame honeyFrame : hiveHoneyFrames) {
                if (honeyFrame.getKgOfHoney() > 3.5) {
                    hiveCounter++;
                    totalKgOfHoneyPerHive = honeyFrame.getKgOfHoney();
                }

                    HarvestedHoney harvestedHoneys = new HarvestedHoney(this,
                            hive,
                            hive.getId(),
                            hive.getHoney(),
                            hiveCounter,
                            honeyFrame.getHoneyType(),
                            totalKgOfHoneyPerHive,
                            currentDate);
                    harvestedHoney.add(harvestedHoneys);
                    honeyFrame.setKgOfHoney(0);
                }
                this.harvestedHoneys.addAll(harvestedHoney);


            }
        }
    }