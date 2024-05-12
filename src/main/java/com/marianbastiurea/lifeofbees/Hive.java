package com.marianbastiurea.lifeofbees;

import java.util.Date;
import java.util.*;



public class Hive {
    private int id;
    private boolean itWasSplit;
    private int numberOfHoneyFrame;
    private int numberOfEggsFrame;
    private List<EggsFrame> eggsFrames;
    private int numberOfBees;
    private Queen queen;
    private List<EggsBatch> eggsBatches;
    private Honey honey;
    private List<BeesBatch> beesBatches;
    private List<HoneyFrame> honeyFrames;
    private Apiary apiary; // Add an Apiary field to store the associated apiary

    public Hive(Apiary apiary, List<EggsBatch> eggsBatches, List<EggsFrame> eggsFrames, List<BeesBatch> beesBatches, List<HoneyFrame> honeyFrames) {
        this.apiary = apiary;
        this.eggsBatches = new ArrayList<>(eggsBatches);
        this.eggsFrames = new ArrayList<>(eggsFrames);
        this.beesBatches = new ArrayList<>(beesBatches);
        this.honeyFrames = new ArrayList<>(honeyFrames);
    }

    public boolean isItWasSplit() {
        return itWasSplit;
    }

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }

    public Apiary getApiary() {
        return apiary;
    }

    public void setApiary(Apiary apiary) {
        this.apiary = apiary;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", itWasSplit=" + this.itWasSplit +
                ", numberOfHoneyFrame=" + this.numberOfHoneyFrame +
                ", numberOfEggsFrame=" + this.numberOfEggsFrame +
                ", eggsFrames=" + this.eggsFrames +
                ", numberOfBees=" + this.numberOfBees +
                ", age of queen=" + this.queen.getAgeOfQueen() +
                ", honey="+this.honey.getHoneyType()+
                ", eggsBatches=" + this.eggsBatches +
                ", beesBatches=" + this.beesBatches +
                ", honeyFrames=" + this.honeyFrames +
                '}';
    }

    public void setEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches = eggsBatches;
    }

    public void setEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames = eggsFrames;
    }

    public void setBeesBatches(List<BeesBatch> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public void setHoneyFrames(List<HoneyFrame> honeyFrames) {
        this.honeyFrames = honeyFrames;
    }

    public Queen getQueen() {

        return queen;
    }

    public void setQueen(Queen queen) {

        this.queen = queen;
    }

    public Hive() {
    }

    public Honey getHoney() {
        return honey;
    }

    public void setHoney(Honey honey) {
        this.honey = honey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfHoneyFrame() {
        return numberOfHoneyFrame;
    }

    public void setNumberOfHoneyFrame(int numberOfHoneyFrame) {
        this.numberOfHoneyFrame = numberOfHoneyFrame;
    }

    public int getNumberOfEggsFrame() {
        return numberOfEggsFrame;
    }

    public void setNumberOfEggsFrame(int numberOfEggsFrame) {
        this.numberOfEggsFrame = numberOfEggsFrame;
    }


    public int getAgeOfQueen() {
        return getQueen().getAgeOfQueen();
    }

    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }


    public void addEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches.addAll(eggsBatches);
    }

    public void addBeesBatches(List<BeesBatch> beesBatches) {
        this.beesBatches.addAll(beesBatches);
    }

    public void addHoneyFrames(List<HoneyFrame> honeyFrames) {
        this.honeyFrames.addAll(honeyFrames);
    }

    public void addEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames.addAll(eggsFrames);
    }

    public List<EggsBatch> getEggsBatches() {
        return eggsBatches;
    }

    public List<HoneyFrame> getHoneyFrames() {
        return honeyFrames;
    }

    public List<EggsFrame> getEggsFrames() {
        return eggsFrames;
    }

    public List<BeesBatch> getBeesBatches() {
        return beesBatches;
    }

    public double ageOfQueenIndex(int dayOfMonth, HarvestingMonths month) {
        /* a queen lives 3-5 years. When will build first 10 hives in apiary will use random to generate ageOfQueen
         between 1  and 5 years old for our queen. At age 5, will have to replace this queen with new one.
         Depending on age of queen will choose an fertility index between 0.25 and 1. When index is 0.25, queen is too old
         to lay eggs and she have to be replaced. When index is 1,fertility of queen is at maximum and she can lay
         upon 2000 eggs daily
         */

        int ageOfQueen = this.getQueen().getAgeOfQueen();
        String honeyType = this.getHoney().honeyTypes(month, dayOfMonth);
        double numberRandom = Math.random();
        switch (ageOfQueen) {
            case 0, 1, 2, 3:
                return 1;
            case 4:
               if (numberRandom < 0.5 && honeyType.equals("Acacia")) {
                    this.getQueen().setAgeOfQueen(0);
                    return 1;}
               else
                    return 0.75;
            case 5:
                this.getQueen().setAgeOfQueen(0);
            default:
                break;
        }
        return 0;
    }


    public void checkAndAddEggsToBees(Date currentDate) {
/* this method check creation date of each eggs batch and difference between current date and creation date is more
        than 20 days, eggs will hatch into bees. Eggs batch will be removed from list and number of eggs will be add to
        number of bees from hive

 */

        Date date = currentDate;
        List<EggsBatch> eggsBatches = this.getEggsBatches();
        List<EggsFrame> eggsFrames = this.getEggsFrames();
        List<BeesBatch> beesBatches = new ArrayList<>();

        Iterator<EggsBatch> iterator = eggsBatches.iterator();
        while (iterator.hasNext()) {
            EggsBatch eggsBatch = iterator.next();
            Date creationDate = eggsBatch.getCreationDate();
            long differenceInMillisecond = Math.abs(currentDate.getTime() - creationDate.getTime());
            long differenceInDays = differenceInMillisecond / (24 * 60 * 60 * 1000);
            if (differenceInDays > 20) {
                this.numberOfBees += eggsBatch.getNumberOfEggs(); // Add eggs to bees
                for (EggsFrame eggsFrame : eggsFrames) {

                    eggsFrame.setNumberOfEggs(eggsFrame.getNumberOfEggs() - (eggsBatch.getNumberOfEggs() / eggsFrames.size()));
                }
                BeesBatch beesBatch = new BeesBatch(eggsBatch.getNumberOfEggs(), currentDate);
                beesBatches.add(beesBatch);
                addBeesBatches(beesBatches);
                iterator.remove();
            }

        }
    }


    public void fillUpExistingEggsFrameFromHive(Date currentDate) {

        /*
        this method will fill up with eggs eggs frame from hive after it was created. when all first eggs frame was full
        will be add another eggs frame in hive. maximum number of eggs frame is 6.
         */


        int maxEggPerFrame = 6400; // a frame have around 8500 cells. 75% more or less are used by the queen to lay eggs.
        // Remaining cells are fill up with honey or are damaged

        System.out.println();
        Date getCreationDate = null;
        List<EggsBatch> eggsBatches = this.getEggsBatches();
        List<EggsFrame> eggsFrames = this.getEggsFrames();
        int maximumNumberOfFramesToAdd = 6 - eggsFrames.size();
        for (EggsBatch eggsBatch : eggsBatches) {
            if (currentDate.equals(eggsBatch.getCreationDate())) {
                for (EggsFrame eggsFrame : eggsFrames) {
                    if (eggsFrame.getNumberOfEggs() < maxEggPerFrame) {
                        if (maximumNumberOfFramesToAdd != 0) {
                            eggsFrame.setNumberOfEggs(Math.min(maxEggPerFrame, eggsFrame.getNumberOfEggs() + (eggsBatch.getNumberOfEggs() / eggsFrames.size())));
                        } else {
                            eggsFrame.setNumberOfEggs(Math.min(eggsFrame.getNumberOfEggs() + eggsBatch.getNumberOfEggs(), maxEggPerFrame));
                        }
                    }
                }
            }
        }
        System.out.println("Hive ID: " + this.getId());
        System.out.println("Eggs Frame: " + this.getEggsFrames());
        System.out.println(" date is " + currentDate);
        System.out.println(" your hive is :" + this);
    }


    public void addNewEggsFrameInHive(Date currentDate) {

        /*
        this method will add new empty eggs frame in hive. If total number of eggs frame full in hive is equal with
        6, will call method to split hive in two hives.
         */

        int maxEggPerFrame = 6400;
        // a frame have around 8500 cells. 75% more or less are used by the queen to lay eggs.
        // Remaining cells are fill up with honey or are damaged

        Date date = currentDate;
//        Calendar calendar = Calendar.getInstance();
//        List<EggsBatch> eggsBatches = this.getEggsBatches();



        switch (this.eggsFrames.size()) {
            case 3, 4, 5:
                System.out.println("In hive " + this.getId() + " is add another 1 eggsFrame");
                break;
            case 6: {
                boolean allFramesAreFull = true;
                for (EggsFrame eggsFrame : this.eggsFrames) {
                    if (eggsFrame.getNumberOfEggs() < maxEggPerFrame) {
                        allFramesAreFull = false;
                        break;
                    }
                }
                if (allFramesAreFull) {
                    System.out.println("Now old and new frames are full. Hive will be split in two hives.");
                    apiary.splitHive();
                }
                break;
            }
            default:
                break;
        }
            /*for a hive, maximum of eggsFrame for spring time is 6 frame.
             After queen fill this number of frame, we can choose to split the hive or add
            another 1/2 hive on top with frame just for honey and bottom hive numberOfEggsFrame will be 10
            and remaining 2 honeyFrame will be not harvested
             */


        int eggsFrameFull = 0;
        EggsFrame eggsFrame = this.eggsFrames.get(eggsFrameFull);
        for (int i = 1; i < eggsFrames.size() + 1; i++) {
            if (eggsFrame.getNumberOfEggs() == maxEggPerFrame && eggsFrameFull < eggsFrames.size()) {
                eggsFrameFull += 1;
            }
        }
        if (eggsFrameFull == eggsFrames.size()) {
            int maximumNumberOfFramesToAdd = 6 - eggsFrameFull;
            for (int i = 1; i < maximumNumberOfFramesToAdd + 1; i++) {
                this.setNumberOfEggsFrame(this.getNumberOfEggsFrame() + 1);
                this.addEggsFrames(createNewEggsFrame(this.getNumberOfEggsFrame()));
            }
        }

        System.out.println("Hive ID: " + this.getId());
        System.out.println("Eggs Frame: " + this.getEggsFrames());
        System.out.println(" date is " + date);
        System.out.println(" your hive is :" + this);
    }

    public List<EggsFrame> createNewEggsFrame(int numberOfEggsFrame) {
        // this method will create a new empty eggs frame

        List<EggsFrame> eggsFrames = new ArrayList<>();
        EggsFrame eggsFrame = new EggsFrame(numberOfEggsFrame, 0);
        eggsFrames.add(eggsFrame);
        return eggsFrames;
    }


    public void beesDie(Date currentDate) {
/*
this method will check the date when bees hatched and if difference between hatched date and current date is more than 31 days
they will die. bees number from each batch will be subtract from total number of bees from hive
 */

        Date date = currentDate;
        List<EggsFrame> eggsFrames = this.getEggsFrames();
        List<BeesBatch> beesBatches = this.getBeesBatches();

        Iterator<BeesBatch> iterator = beesBatches.iterator();
        while (iterator.hasNext()) {
            BeesBatch beesBatch = iterator.next();
            Date creationDate = beesBatch.getCreationDate();
            long differenceInMillisecond = Math.abs(currentDate.getTime() - creationDate.getTime());
            long differenceInDays = differenceInMillisecond / (24 * 60 * 60 * 1000);
            if (differenceInDays > 2) {
                this.numberOfBees -= beesBatch.getNumberOfBees(); // Subtract number of bees from each beesBatch from total number
                iterator.remove();
            }

        }
    }

}