package com.marianbastiurea.lifeofbees;


import com.marianbastiurea.lifeofbees.eggframe.EggFrame;

import java.util.Date;
import java.util.*;


public class Hive {
    private int id;
    private boolean itWasSplit;
    private boolean answerIfWantToSplit;
    private boolean wasMovedAnEggsFrame;
    private List<EggFrame> eggFrames;
    private int numberOfBees;
    private Queen queen;
    private Honey honey;
    private List<BeesBatch> beesBatches;
    private List<HoneyFrame> honeyFrames;
    private List<HoneyBatch> honeyBatches;
    private Apiary apiary; // Add an Apiary field to store the associated apiary

    public Hive(Apiary apiary, List<EggFrame> eggFrames, List<BeesBatch> beesBatches,
                List<HoneyFrame> honeyFrames, List<HoneyBatch> honeyBatches) {
        this.apiary = apiary;
        this.eggFrames = new ArrayList<>(eggFrames);
        this.beesBatches = new ArrayList<>(beesBatches);
        this.honeyFrames = new ArrayList<>(honeyFrames);
        this.honeyBatches = new ArrayList<>(honeyBatches);
    }

    public Hive(Apiary apiary, int id, boolean itWasSplit, boolean answerIfWantToSplit, int numberOfBees, Queen queen) {
        this.apiary = apiary;
        this.id = id;
        this.itWasSplit = itWasSplit;
        this.answerIfWantToSplit = answerIfWantToSplit;
        this.numberOfBees = numberOfBees;
        this.queen = queen;
    }

    public Hive(Honey honey) {
        this.honey = honey;
    }

    public Hive(boolean wasMovedAnEggsFrame) {
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }

    public Hive(
            Apiary apiary,
            int hiveIdCounter,
            boolean itWasSplit,
            boolean wasMovedAnEggsFrame,
            boolean answerIfWantToSplit,
            List<EggFrame> eggFrames,
            List<HoneyFrame> honeyFrames,
            List<BeesBatch> beesBatches,
            List<HoneyBatch> honeyBatches,
            Honey honey,
            Queen queen,
            int numberOfBees) {
        this.apiary = apiary;
        this.id = hiveIdCounter;
        this.itWasSplit = itWasSplit;
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
        this.answerIfWantToSplit = answerIfWantToSplit;
        this.eggFrames = eggFrames;
        this.honeyFrames = honeyFrames;
        this.beesBatches = beesBatches;
        this.honeyBatches = honeyBatches;
        this.honey = honey;
        this.queen = queen;
        this.numberOfBees = numberOfBees;
    }

    public boolean isItWasSplit() {
        return itWasSplit;
    }

    public boolean isWasMovedAnEggsFrame() {
        return wasMovedAnEggsFrame;
    }

    public void setWasMovedAnEggsFrame(boolean wasMovedAnEggsFrame) {
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }

    public boolean isAnswerIfWantToSplit() {
        return answerIfWantToSplit;
    }

    public void setAnswerIfWantToSplit(boolean answerIfWantToSplit) {
        this.answerIfWantToSplit = answerIfWantToSplit;
    }

    public void setApiary(Apiary apiary) {
        this.apiary = apiary;
    }

    public Apiary getApiary() {
        return apiary;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", itWasSplit=" + this.itWasSplit +
                ", answerIfWantToSplit=" + this.answerIfWantToSplit +
                ",wasMovedAnEggsFrame=" + this.wasMovedAnEggsFrame +
                ", numberOfHoneyFrame=" + this.honeyFrames.size() +
                ", numberOfEggsFrame=" + this.eggFrames.size() + "\n" +
                ", eggsFrames=" + this.eggFrames + "\n" +
                ", numberOfBees=" + this.numberOfBees +
                ", age of queen=" + this.queen.getAgeOfQueen() +
                ", beesBatches=" + this.beesBatches +
                ", honeyFrames=" + this.honeyFrames +
                ", honeyBatches=" + this.honeyBatches +
                '}';
    }

    public void setEggsFrames(List<EggFrame> eggFrames) {
        this.eggFrames = eggFrames;
    }

    public void setBeesBatches(List<BeesBatch> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public void setHoneyFrames(List<HoneyFrame> honeyFrames) {
        this.honeyFrames = honeyFrames;
    }

    public void setHoneyBatches(List<HoneyBatch> honeyBatches) {
        this.honeyBatches = honeyBatches;
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

    public int getAgeOfQueen() {
        return getQueen().getAgeOfQueen();
    }

    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public void addBeesBatches(List<BeesBatch> beesBatches) {
        this.beesBatches.addAll(beesBatches);
    }

    public void addHoneyBatches(List<HoneyBatch> honeyBatches) {
        this.honeyBatches.addAll(honeyBatches);
    }

    public void addHoneyFrames(List<HoneyFrame> honeyFrames) {
        this.honeyFrames.addAll(honeyFrames);
    }

    public void addEggsFrames(List<EggFrame> eggFrames) {
        this.eggFrames.addAll(eggFrames);
    }

    public List<HoneyBatch> getHoneyBatches() {
        return honeyBatches;
    }

    public List<HoneyFrame> getHoneyFrames() {
        return honeyFrames;
    }

    public List<EggFrame> getEggsFrames() {
        return eggFrames;
    }

    public List<BeesBatch> getBeesBatches() {
        return beesBatches;
    }


    public void checkAndAddEggsToBees(Date currentDate) {
/* this method check creation date of each eggs batch and difference between current date and creation date is more
        than 20 days, eggs will hatch into bees. Eggs batch will be removed from list and number of eggs will be add to
        number of bees from hive

 */
        int numberOfBeesFromEggsBatch = 0;

        for (EggFrame eggFrame : eggFrames) {
            List<BeesBatch> hatchedBatches = eggFrame.checkAndHatchEggs(currentDate);
            for (BeesBatch batch : hatchedBatches) {
                numberOfBeesFromEggsBatch += batch.getNumberOfBees();
            }
        }
        if (numberOfBeesFromEggsBatch > 0) {
            this.numberOfBees += numberOfBeesFromEggsBatch;
            BeesBatch newBeesBatch = new BeesBatch(numberOfBeesFromEggsBatch, currentDate);
            beesBatches.add(newBeesBatch);
        }
    }


    public void addNewEggsFrameInHive(int year) {

        /*
        this method will add new empty eggs frame in hive. If total number of eggs frame full in hive is equal with
        6, will call method to split hive in two hives.
         */


        // a frame have around 8500 cells. 75% more or less are used by the queen to lay eggs.
        // Remaining cells are fill up with honey or are damaged

        if (this.eggFrames.size() == 6) {

            boolean allFramesAreFull = true;
            for (EggFrame eggFrame : this.eggFrames) {
                if (eggFrame.getNumberOfEggs() < eggFrame.getMaxEggPerFrame()) {
                    allFramesAreFull = false;
                    break;
                }
            }
            if (allFramesAreFull) {
                Scanner scanner = new Scanner(System.in);
                String answer;
                if (!this.answerIfWantToSplit) {
                    do {
                        System.out.println("this is year: " + year);
                        System.out.println("You can choose to split or not hive number " + this.getId());
                        System.out.println("You can split this hive only once in a year and is only one question per year");
                        System.out.println("You have to insert Y(yes) or N(no): ");

                        while (true) {
                            answer = scanner.nextLine().trim().toUpperCase();
                            if (answer.equals("Y") || answer.equals("N")) {
                                break;
                            } else {
                                System.out.print("Invalid input. Please enter Y or N: ");
                            }
                        }
                        this.setAnswerIfWantToSplit(true);

                        if (answer.equals("Y")) {
                            System.out.println("You choose to split your hive");
                            this.getApiary().splitHive(this);
                        }
                        break;

                    } while (false);
                }
            }
        }

            /*for a hive, maximum of eggsFrame for spring time is 6 frame.
             After queen fill this number of frame, we can choose to split the hive or add
            another 1/2 hive on top with frame just for honey and bottom hive numberOfEggsFrame will be 10
            and remaining 2 honeyFrame will be not harvested
             */


        int eggsFrameFull = 0;
        for (int i = 0; i < eggFrames.size(); i++) {
            if (this.eggFrames.get(i).getNumberOfEggs() == this.eggFrames.get(i).getMaxEggPerFrame() && eggsFrameFull < eggFrames.size()) {
                eggsFrameFull += 1;
            }
        }
        if (eggsFrameFull == eggFrames.size()) {
            int maximumNumberOfFramesToAdd = 6 - eggsFrameFull;
            if (maximumNumberOfFramesToAdd != 0) {
                this.eggFrames.add(new EggFrame());
            }
        }
    }

    public void fillUpExistingHoneyFrameFromHive(Date currentDate) {

        /*
        this method will fill up with honey honeyFrame from hive after it was created. when all first honeyFrame was full
        will add another honeyFrame in hive. maximum number of honeyFrame is 6.
         */

        double maxKgOfHoneyPerFrame = 4.5;
        // a frame could be loaded with around  4.5Kg of honey
        Random random = new Random();
        System.out.println();
        int numberOfHoneyFrameNotFull = honeyFrames.size() - this.getNumberOfFullHoneyFrame();
        int numberOfFlight = random.nextInt(3, 6);
        double kgOfHoneyToAdd = this.numberOfBees * numberOfFlight * 0.00002;//0.02gr/flight/bee
        System.out.println("daily honey production for " + this.getId() + " is " + kgOfHoneyToAdd + " kg");
        for (HoneyFrame honeyFrame : honeyFrames) {
            if (honeyFrame.getKgOfHoney() < maxKgOfHoneyPerFrame) {
                honeyFrame.setKgOfHoney(Math.min(maxKgOfHoneyPerFrame, honeyFrame.getKgOfHoney() + kgOfHoneyToAdd / numberOfHoneyFrameNotFull));
            }
        }
        System.out.println(" date is " + currentDate);
        System.out.println(" your hive is :" + this);
    }


    public void addNewHoneyFrameInHive() {

        /*
        this method will add new empty honey frame in hive. If total number of honey frame full in hive is equal with
        6, will call method to collect honey from frames.
         */

        double maxKgOfHoneyPerFrame = 4.5;
        // a frame could be loaded with around  4.5Kg of honey

        int honeyFrameFull = 0;
        for (HoneyFrame honeyFrame : honeyFrames) {
            if (honeyFrame.getKgOfHoney() == maxKgOfHoneyPerFrame && honeyFrameFull < honeyFrames.size()) {
                honeyFrameFull += 1;
            }
        }
        if (honeyFrameFull == honeyFrames.size()) {
            int maximumNumberOfFramesToAdd = 6 - honeyFrameFull;
            if (maximumNumberOfFramesToAdd != 0) {
                this.addHoneyFrames(createNewHoneyFrame());
            }
        }
    }


    public List<EggFrame> createNewEggsFrame() {
        // this method will create a new empty eggs frame

        List<EggFrame> eggFrames = new ArrayList<>();
        EggFrame eggFrame = new EggFrame();
        eggFrames.add(eggFrame);
        return eggFrames;
    }

    public List<HoneyFrame> createNewHoneyFrame() {
        // this method will create a new empty honey frame

        List<HoneyFrame> honeyFrames = new ArrayList<>();
        HoneyFrame honeyFrame = new HoneyFrame(0, getHoney().getHoneyType());
        honeyFrames.add(honeyFrame);
        return honeyFrames;
    }


    public void beesDie(Date currentDate) {
/*
this method will check the date when bees hatched and if difference between hatched date and current date is more than 31 days
they will die. bees number from each batch will subtract from total number of bees from hive
 */

        List<BeesBatch> beesBatches = this.getBeesBatches();
        Iterator<BeesBatch> iterator = beesBatches.iterator();
        while (iterator.hasNext()) {
            BeesBatch beesBatch = iterator.next();
            Date creationDate = beesBatch.getCreationDate();
            long differenceInMillisecond = Math.abs(currentDate.getTime() - creationDate.getTime());
            long differenceInDays = differenceInMillisecond / (24 * 60 * 60 * 1000);
            if (differenceInDays > 30) {
                this.numberOfBees -= beesBatch.getNumberOfBees(); // Subtract number of bees from each beesBatch from total number
                iterator.remove();
            }

        }
    }

    public boolean checkIfAll6EggsFrameAre80PercentFull() {
        int eggsFrameFull = 0;
        boolean allEggsFrameAreFull = false;
        EggFrame eggFrame = this.eggFrames.get(eggsFrameFull);
        if (eggFrames.size() == 6) {
            for (int i = 1; i < 7; i++) {
                if (eggFrame.getNumberOfEggs() > 0.8 * eggFrame.getMaxEggPerFrame()) {
                    eggsFrameFull += 1;
                }
            }
        }
        if (eggsFrameFull == 6) {
            allEggsFrameAreFull = true;
        }
        return allEggsFrameAreFull;
    }

    public int getNumberOfFullEggsFrame() {
        int eggsFrameFull = 0;
        for (int i = 0; i < eggFrames.size(); i++) {
            if (this.eggFrames.get(i).getNumberOfEggs() == this.eggFrames.get(i).getMaxEggPerFrame() && eggsFrameFull < eggFrames.size()) {
                eggsFrameFull += 1;
            }
        }
        return eggsFrameFull;
    }

    public int getNumberOfFullHoneyFrame() {

        int honeyFrameFull = 0;
        double maxKgOfHoneyPerFrame = 4.5;
        for (int i = 0; i < this.honeyFrames.size(); i++) {
            if (this.honeyFrames.get(i).getKgOfHoney() == maxKgOfHoneyPerFrame && honeyFrameFull < this.honeyFrames.size()) {
                honeyFrameFull += 1;
            }
        }
        return honeyFrameFull;
    }

    public void moveAnEggsFrameFromUnsplitHiveToASplitOne() {
        if (this.checkIfAll6EggsFrameAre80PercentFull() && !this.itWasSplit && !this.wasMovedAnEggsFrame) {
            List<Hive> hives = this.getApiary().getHives();
            for (Hive hive : hives) {
                if (hive.itWasSplit) {
                    System.out.println("We move an eggs frame " + this.eggFrames.get(0) +
                            " from hive " + this.getId() + " to hive " + hive.getId());

                    EggFrame frameToMove = this.getEggsFrames().remove(hive.getEggsFrames().size() - 1);
                    hive.eggFrames.add(frameToMove);
                    this.setWasMovedAnEggsFrame(true);// Move only one frame to one split hive
                    break;
                }
            }
        }
    }

    public void changeQueen() {
        queen = new Queen(0);
    }

    public void fillUpEggsFrame(Date currentDate, int numberOfEggs) {

        int maxEggPerFrame = 6400; // a frame have around 8500 cells. 75% more or less are used by the queen to lay eggs.
        // Remaining cells are fill up with honey or are damaged

        int size = getEggsFrames().size() - this.getNumberOfFullEggsFrame();
        //int remainingEggs = numberOfEggs % size;

        if (size != 0) {
            int numberOfEggsToPutInFrame = numberOfEggs / size;

            for (EggFrame eggFrame : getEggsFrames()) {
                if (eggFrame.getNumberOfEggs() < eggFrame.getMaxEggPerFrame()) {
                    if (eggFrame.getNumberOfEggs() + numberOfEggsToPutInFrame < maxEggPerFrame) {
                        eggFrame.addEggs(numberOfEggsToPutInFrame, currentDate);
                    } else {
                        eggFrame.addEggs(maxEggPerFrame-eggFrame.getNumberOfEggs(), currentDate);
                    }
                }
            }
        }
        // eggsFrames.get(0).addEggs(remainingEggs,currentDate);
    }
}
