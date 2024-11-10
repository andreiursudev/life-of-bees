package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.util.*;


public class Hive {
    private int id;
    private boolean itWasSplit;
    private boolean wasMovedAnEggsFrame;
    //TODO: Replace List<EggFrame> eggFrames with EggFrames.
    private List<EggFrames> eggFrames;
    private Queen queen;
    LinkedList<Integer> beesBatches = new LinkedList<>();
    private List<HoneyFrame> honeyFrames;
    private List<HoneyBatch> honeyBatches;

    private ActionOfTheWeek actionOfTheWeek;

    public Hive( List<EggFrames> eggFrames, LinkedList<Integer> beesBatches,
                List<HoneyFrame> honeyFrames, List<HoneyBatch> honeyBatches) {
        this.eggFrames = new ArrayList<>(eggFrames);
        this.beesBatches = new LinkedList<>(beesBatches);
        this.honeyFrames = new ArrayList<>(honeyFrames);
        this.honeyBatches = new ArrayList<>(honeyBatches);
        this.actionOfTheWeek = new ActionOfTheWeek();

    }

    public Hive() {
        this.actionOfTheWeek = new ActionOfTheWeek();
    }

    public Hive( int id, boolean itWasSplit, Queen queen) {
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
            List<EggFrames> eggFrames,
            List<HoneyFrame> honeyFrames,
            LinkedList<Integer> beesBatches,
            List<HoneyBatch> honeyBatches,
            Queen queen) {
        this.id = hiveIdCounter;
        this.itWasSplit = itWasSplit;
        this.wasMovedAnEggsFrame = wasMovedAnEggsFrame;
        this.eggFrames = eggFrames;
        this.honeyFrames = honeyFrames;
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

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", itWasSplit=" + this.itWasSplit +
                ",wasMovedAnEggsFrame=" + this.wasMovedAnEggsFrame +
                ", numberOfHoneyFrame=" + this.honeyFrames.size() +
                ", numberOfEggsFrame=" + this.eggFrames.get(0).getNumberOfEggFrames()+ "\n" +
                ", eggsFrames=" + this.eggFrames + "\n" +
                ", age of queen=" + this.queen.getAgeOfQueen() +
                ", beesBatches=" + this.beesBatches +
                ", honeyFrames=" + this.honeyFrames +
                ", honeyBatches=" + this.honeyBatches +
                '}';
    }

    public void setEggFrames(List<EggFrames> eggFrames) {
        this.eggFrames = eggFrames;
    }

    public List<EggFrames> getEggFrames() {
        return eggFrames;
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

    public List<HoneyFrame> getHoneyFrames() {
        return honeyFrames;
    }

    public List<EggFrames> getEggsFrames() {
        return eggFrames;
    }

    public LinkedList<Integer> getBeesBatches() {
        return beesBatches;
    }

    public void setBeesBatches(LinkedList<Integer> beesBatches) {
        this.beesBatches = beesBatches;
    }

    public ActionOfTheWeek getAction() {
        return actionOfTheWeek;
    }

    public void setAction(ActionOfTheWeek actionOfTheWeek) {
        this.actionOfTheWeek = actionOfTheWeek;
    }

//    public void checkAndAddEggsToBees() {
//        int eggsToBees = 0;
//        for (EggFrame eggFrame : eggFrames) {
//            if (eggFrame.getEggBatches().size() > 20) {
//                eggsToBees += eggFrame.checkAndHatchEggs();
//            }
//        }
//        this.getBeesBatches().add(eggsToBees);
//    }

    public List<ActionOfTheWeek> checkIfHiveCouldBeSplit(HarvestingMonths month, int dayOfMonth, List<ActionOfTheWeek> actionsOfTheWeek, LifeOfBees lifeOfBeesGame) {
        if (!this.itWasSplit && lifeOfBeesGame.getApiary().getHives().size() < 10) {
            if ((month.equals(HarvestingMonths.APRIL) || month.equals(HarvestingMonths.MAY)) &&
                    (dayOfMonth == 1 || dayOfMonth == 10)) {
                if (this.eggFrames.size() == 6) {
                    boolean allFramesAreFull = true;
                    for (EggFrame eggFrame : this.eggFrames) {
                        if (eggFrame.getNumberOfEggs() < eggFrame.getMaxEggPerFrame()) {
                            allFramesAreFull = false;
                            break;
                        }
                    }
                    if (allFramesAreFull) {
                        Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("SPLIT_HIVE", actionsOfTheWeek).getData();
                        ActionOfTheWeek actionInstance = new ActionOfTheWeek();
                        actionInstance.addOrUpdateAction("SPLIT_HIVE", getId(), data, actionsOfTheWeek);
                    }
                }
            }
        }
        return actionsOfTheWeek;
    }

    public List<ActionOfTheWeek> checkIfCanAddNewEggsFrameInHive(List<ActionOfTheWeek> actionsOfTheWeek) {
        int eggsFrameFull = 0;
        for (EggFrame eggFrame : this.eggFrames) {
            if (eggFrame.getNumberOfEggs() > 6000) {
                eggsFrameFull += 1;
            }
        }
        if (this.eggFrames.size() < 6 && eggsFrameFull == this.eggFrames.size()) {
            Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("ADD_EGGS_FRAME", actionsOfTheWeek).getData();
            ActionOfTheWeek actionInstance = new ActionOfTheWeek();
            actionInstance.addOrUpdateAction("ADD_EGGS_FRAME", getId(), data, actionsOfTheWeek);
        }
        return actionsOfTheWeek;
    }

    public void addNewEggsFrameInHive() {
        if (this.eggFrames.size() < 6) {
            eggFrames.add(new EggFrame());
        }
    }

    public List<ActionOfTheWeek> checkIfCanAddANewHoneyFrameInHive(List<ActionOfTheWeek> actionsOfTheWeek) {
        long honeyFrameFull = this.honeyFrames.stream()
                .filter(HoneyFrame::isHarvestable)
                .count();

        if (this.honeyFrames.size() < 6) {
            if (honeyFrameFull == this.honeyFrames.size()) {
                Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("ADD_HONEY_FRAME", actionsOfTheWeek).getData();
                ActionOfTheWeek actionInstance = new ActionOfTheWeek();
                actionInstance.addOrUpdateAction("ADD_HONEY_FRAME", getId(), data, actionsOfTheWeek);
            }
        }
        return actionsOfTheWeek;
    }



    public List<ActionOfTheWeek> addHoneyBatches(List<HoneyBatch> honeyBatches, List<ActionOfTheWeek> actionsOfTheWeek) {
        if (honeyBatches != null && !honeyBatches.isEmpty()) {
            this.honeyBatches.addAll(honeyBatches);
            System.out.println("acesta e mierea culeasa in stupul" + this.getId() + " " + honeyBatches);
            Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("HARVEST_HONEY", actionsOfTheWeek).getData();
            ActionOfTheWeek actionInstance = new ActionOfTheWeek();
            actionInstance.addOrUpdateAction("HARVEST_HONEY", getId(), data, actionsOfTheWeek);
        }
        return actionsOfTheWeek;
    }

    public void fillUpExistingHoneyFrameFromHive(LifeOfBees lifeOfBeesGame) {

        Random random = new Random();
        Honey honey = new Honey();
        LocalDate date = lifeOfBeesGame.getCurrentDate();
        int day = date.getDayOfMonth();
        int monthValue = date.getMonthValue();
        HarvestingMonths month = HarvestingMonths.values()[monthValue - 3];
        int numberOfHoneyFrameNotFull = honeyFrames.size() - this.getNumberOfFullHoneyFrame();
        int numberOfFlight = random.nextInt(3, 6);
        double kgOfHoneyToAdd = this.getBeesBatches().stream().mapToInt(Integer::intValue).sum() * numberOfFlight * 0.00002 * honey.honeyProductivity(honey.honeyType(month, day));//0.02gr/flight/bee
        for (HoneyFrame honeyFrame : honeyFrames) {
            honeyFrame.fill(kgOfHoneyToAdd / numberOfHoneyFrameNotFull);
        }
    }



    public void addNewHoneyFrameInHive() {
        if (honeyFrames.size() < 6) {
            honeyFrames.add(new HoneyFrame(0));
            System.out.println("New honey frame added. Total: " + this.eggFrames.size());
        }
    }

    public boolean checkIfAll6EggsFrameAre80PercentFull() {
        if (eggFrames.size() != 6) {
            return false;
        }
        for (EggFrame eggFrame : eggFrames) {
            if (eggFrame.is80PercentFull()) {
                return false;
            }
        }
        return true;
    }

    public int getNumberOfFullEggsFrame() {
        int eggsFrameFull = 0;
        EggFrames eggFramesNew = new EggFrames();
        eggFramesNew.isFull();
        for (EggFrame eggFrame : eggFrames) {
            if (eggFrame.isEggFrameFull() && eggsFrameFull < eggFrames.size()) {
                eggsFrameFull += 1;
            }
        }
        return eggsFrameFull;
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



    public List<ActionOfTheWeek> checkIfCanMoveAnEggsFrame(List<ActionOfTheWeek> actionsOfTheWeek, LifeOfBees lifeOfBeesGame) {
        List<Integer> hiveIdPair;

        if (this.checkIfAll6EggsFrameAre80PercentFull() && !this.itWasSplit && !this.wasMovedAnEggsFrame) {
            List<Hive> hives = lifeOfBeesGame.getApiary().getHives();
            for (Hive hive : hives) {
                if (hive.itWasSplit && hive.getQueen().getAgeOfQueen() == 0) {
                    hiveIdPair = Arrays.asList(this.getId(), hive.getId());

                    Map<String, Object> data = ActionOfTheWeek.findOrCreateAction("MOVE_EGGS_FRAME", actionsOfTheWeek).getData();
                    ActionOfTheWeek actionInstance = new ActionOfTheWeek();
                    actionInstance.addOrUpdateAction1("MOVE_EGGS_FRAME", hiveIdPair, data, actionsOfTheWeek);
                }
            }
        }
        return actionsOfTheWeek;
    }

    public void changeQueen() {
        queen = new Queen(0);
    }

    public void fillUpEggsFrame(int numberOfEggs) {
        int size = getEggsFrames().size() - this.getNumberOfFullEggsFrame();
        if (size != 0) {
            int numberOfEggsToPutInFrame = numberOfEggs / size;
            System.out.println("numarul de oua de pus in rama:" + numberOfEggsToPutInFrame);
            for (EggFrame eggFrame : getEggsFrames()) {
                if (eggFrame.getNumberOfEggs() + numberOfEggsToPutInFrame < eggFrame.getMaxEggPerFrame()) {
                    eggFrame.getEggBatches().addFirst(numberOfEggsToPutInFrame);

                    System.out.println(" acesta oua sunt pe if" + eggFrame.getEggBatches().getFirst());
                } else {
                    eggFrame.getEggBatches().addFirst(eggFrame.getMaxEggPerFrame() - eggFrame.getNumberOfEggs());
                    System.out.println(" acesta oua sunt pe else" + eggFrame.getEggBatches().getFirst());
                }
                System.out.println("numarul de oua din rama este: " + eggFrame.getNumberOfEggs());
            }
        }
    }

    public static void addHivesToApiary( List<Hive> newHives, LifeOfBees lifeOfBeesgame) {
        List<Hive> existingHives =lifeOfBeesgame.getApiary().getHives();
        for (Hive hive : newHives) {
            hive.setId(existingHives.size() + 1);
            existingHives.add(hive);
        }
    }
}
