package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.action.ActionType;
import com.marianbastiurea.lifeofbees.action.ActionsOfTheWeek;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.action.ActionOfTheWeek;
import com.marianbastiurea.lifeofbees.time.BeeTime;

import  java.time.Month;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDate;

public class Hive {
    private int id;
    private boolean itWasSplit;
    private boolean wasMovedAnEggsFrame;
    private EggFrames eggFrames;
    private Queen queen;
    LinkedList<Integer> beesBatches = new LinkedList<>();
    //TODO Create class HoneyFrames
    private List<HoneyFrame> honeyFrames;
    private HoneyFrames honeyFrames1;
    private List<HoneyBatch> honeyBatches;


    public Hive(EggFrames eggFrames, LinkedList<Integer> beesBatches, HoneyFrames honeyFrames1, List<HoneyBatch> honeyBatches) {
        this.eggFrames = eggFrames;
        this.beesBatches = beesBatches;
        this.honeyFrames1 = honeyFrames1;
        this.honeyBatches = honeyBatches;
    }

    public Hive(int id, boolean itWasSplit, Queen queen) {
        this.id = id;
        this.itWasSplit = itWasSplit;
        this.queen = queen;
    }

    public Hive(boolean wasMovedAnEggsFrame) {
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }

    public Hive(
            int hiveIdCounter,
            boolean itWasSplit,
            boolean wasMovedAnEggsFrame,
            EggFrames eggFrames,
            HoneyFrames honeyFrames1,
            LinkedList<Integer> beesBatches,
            List<HoneyBatch> honeyBatches,
            Queen queen) {
        this.id = hiveIdCounter;
        this.itWasSplit = itWasSplit;
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
        this.eggFrames = eggFrames;
        this.honeyFrames1 = honeyFrames1;
        this.beesBatches = beesBatches;
        this.honeyBatches = honeyBatches;
        this.queen = queen;
    }

    public boolean isItWasSplit() {
        return itWasSplit;
    }

    public void setWasMovedAnEggsFrame(boolean wasMovedAnEggsFrame) {
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
    }

    public boolean isWasMovedAnEggsFrame() {
        return wasMovedAnEggsFrame;
    }

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", itWasSplit=" + this.itWasSplit +
                ",wasMovedAnEggsFrame=" + this.wasMovedAnEggsFrame +
                ", numberOfHoneyFrame=" + this.honeyFrames1.getNumberOfHoneyFrames() +
                ", numberOfEggsFrame=" + this.eggFrames.getNumberOfEggFrames() + "\n" +
                ", eggFrames=" + this.eggFrames + "\n" +
                ", age of queen=" + this.queen.getAgeOfQueen() +
                ", beesBatches=" + this.beesBatches +
                ", honeyFrames=" + this.honeyFrames1 +
                ", honeyBatches=" + this.honeyBatches +
                '}';
    }

    public EggFrames getEggFrames() {
        return eggFrames;
    }

    public void setEggFrames(EggFrames eggFrames) {
        this.eggFrames = eggFrames;
    }

    public void setHoneyFrames(HoneyFrames honeyFrames1) {
        this.honeyFrames1 = honeyFrames1;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgeOfQueen() {
        return getQueen().getAgeOfQueen();
    }

    public List<HoneyBatch> getHoneyBatches() {
        return honeyBatches;
    }

    public HoneyFrames getHoneyFrames() {
        return honeyFrames1;
    }


    public LinkedList<Integer> getBeesBatches() {
        return beesBatches;
    }

    public void setBeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }


    public void checkAndAddEggsToBees(int bees) {
        this.getBeesBatches().add(bees);
    }

    public void checkIfHiveCouldBeSplit(LocalDate currentDate, ActionsOfTheWeek actionsOfTheWeek) {
        if (!this.itWasSplit) {
            if (BeeTime.timeToSplitHive(currentDate) && this.eggFrames.isFullEggFrames()) {
                if (this.eggFrames.is80PercentFull()) {
                    actionsOfTheWeek.addOrUpdateAction(ActionType.SPLIT_HIVE, getId());
                }
            }
        }
        System.out.println("aceasta e actionsOfTheWeek cu checkIfHiveCouldBeSplit:"+actionsOfTheWeek);

    }

    public void checkIfCanAddNewEggsFrameInHive(ActionsOfTheWeek actionsOfTheWeek) {
        if (eggFrames.canAddNewEggsFrame()) {
            actionsOfTheWeek.addOrUpdateAction(ActionType.ADD_EGGS_FRAME, getId());
            System.out.println("aceasta e actionsOfTheWeek cu checkIfCanAddNewEggsFrameInHive:"+actionsOfTheWeek);
        }
    }


    public void addNewEggsFrameInHive() {
        if (this.eggFrames != null) {
            this.eggFrames.incrementNumberOfEggFrames();
        }
    }

    public ActionsOfTheWeek checkIfCanAddANewHoneyFrameInHive(ActionsOfTheWeek actionsOfTheWeek) {
        long honeyFrameFull = this.honeyFrames.stream()
                .filter(HoneyFrame::isHarvestable)
                .count();
        if (this.honeyFrames.size() < 6) {
            if (honeyFrameFull == this.honeyFrames.size()) {
                actionsOfTheWeek.addOrUpdateAction(ActionType.ADD_HONEY_FRAME, getId());
            }
        }
        System.out.println("aceasta e actionsOfTheWeek cu checkIfCanAddANewHoneyFrameInHive:"+actionsOfTheWeek);
        return actionsOfTheWeek;
    }


    public ActionsOfTheWeek addHoneyBatches
            (List<HoneyBatch> honeyBatches, ActionsOfTheWeek actionsOfTheWeek) {
        if (honeyBatches != null && !honeyBatches.isEmpty()) {
            this.honeyBatches.addAll(honeyBatches);
            actionsOfTheWeek.addOrUpdateAction(ActionType.HARVEST_HONEY, getId());
        }
        System.out.println("aceasta e actionsOfTheWeek cu addHoneyBatches:"+actionsOfTheWeek);
        return actionsOfTheWeek;
    }

    public void fillUpExistingHoneyFrameFromHive(LocalDate currentDate) {

        Random random = new Random();
        Honey honey = new Honey();
        int numberOfHoneyFrameNotFull = honeyFrames.size() - this.getNumberOfFullHoneyFrame();
        int numberOfFlight = random.nextInt(3, 6);
        double kgOfHoneyToAdd = this.getBeesBatches().stream().mapToInt(Integer::intValue).sum() * numberOfFlight * 0.00002 * honey.honeyProductivity(honey.honeyType(currentDate));//0.02gr/flight/bee
        for (HoneyFrame honeyFrame : honeyFrames) {
            honeyFrame.fill(kgOfHoneyToAdd / numberOfHoneyFrameNotFull);
        }
    }


    public void addNewHoneyFrameInHive() {
        if (honeyFrames.size() < 6) {
            honeyFrames.add(new HoneyFrame(0));
            System.out.println("New honey frame added. Total: " + this.honeyFrames.size());
        }
    }


    public int getNumberOfFullHoneyFrame() {
        int honeyFrameFull = 0;
        for (int i = 0; i < this.honeyFrames.size(); i++) {
            if (this.honeyFrames.get(i).isFull() && honeyFrameFull < this.honeyFrames.size()) {
                honeyFrameFull += 1;
            }
        }
        return honeyFrameFull;
    }


//    public ActionsOfTheWeek checkIfCanMoveAnEggsFrame(ActionsOfTheWeek actionsOfTheWeek, LifeOfBees
//            lifeOfBeesGame) {
//        List<Integer> hiveIdPair;
//
//        if (this.getEggFrames().checkIfAll6EggsFrameAre80PercentFull() && !this.itWasSplit && !this.wasMovedAnEggsFrame) {
//            List<Hive> hives = lifeOfBeesGame.getApiary().getHives();
//            for (Hive hive : hives) {
//                if (hive.itWasSplit && hive.getQueen().getAgeOfQueen() == 0) {
//                    hiveIdPair = Arrays.asList(this.getId(), hive.getId());
//                   // Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("MOVE_EGGS_FRAME", actionsOfTheWeek).getData();
//
//                    actionsOfTheWeek.addOrUpdateAction(  ActionType.MOVE_EGGS_FRAME, hiveIdPair, actionsOfTheWeek);
//                }
//            }
//        }
//        return actionsOfTheWeek;
//    }

    public void changeQueen(LocalDate currentDate) {
        double numberRandom = Math.random();
        Month month = currentDate.getMonth();
        int dayOfMonth = currentDate.getDayOfMonth();
        if ((numberRandom < 0.5 && month == Month.MAY && dayOfMonth > 1 && dayOfMonth < 20) || queen.getAgeOfQueen() == 5) {
            queen = new Queen(0);
        }
    }


    public static void addHivesToApiary(List<Hive> newHives, LifeOfBees lifeOfBeesgame) {
        List<Hive> existingHives = lifeOfBeesgame.getApiary().getHives();
        for (Hive hive : newHives) {
            hive.setId(existingHives.size() + 1);
            existingHives.add(hive);
        }
    }

    public void ageOneDay(int numberOfEggs) {
        int bees = getEggFrames().ageOneDay(numberOfEggs);
        getBeesBatches().add(bees);
    }
}
