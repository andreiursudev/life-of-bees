package com.marianbastiurea.lifeofbees;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Hive {
    private int id;
    private int numberOfHoneyFrame;
    private int numberOfEggsFrame;
    private int ageOfQueen;
    private int numberOfEggs;
    private List<EggsFrame> eggsFrames;
    private HoneyFrame honeyFrame;
    private int numberOfBees;
    private double honeyQuantity;
    private Queen queen;
    private Bees bees;
    private List<EggsBatch> eggsBatches;

    public Hive(List<EggsBatch> eggsBatches, List<EggsFrame> eggsFrames) {
        this.eggsBatches = new ArrayList<>(eggsBatches);
        this.eggsFrames = new ArrayList<>(eggsFrames);
    }

    public void setEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches = eggsBatches;
    }

    public void setEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames = eggsFrames;
    }

    public Hive(int id, int ageOfQueen) {
        this.id = id;
        this.ageOfQueen = ageOfQueen;
    }

    public Bees getBees() {
        return bees;
    }

    public void setBees(Bees bees) {
        this.bees = bees;
    }

    public Queen getQueen() {
        return queen;
    }

    public void setQueen(Queen queen) {
        this.queen = queen;
    }

    public Hive() {
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

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public void setNumberOfEggs(int numberOfEggs) {
        this.numberOfEggs = numberOfEggs;
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public void setAgeOfQueen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }


    public HoneyFrame getHoneyFrame() {
        return honeyFrame;
    }

    public void setHoneyFrame(HoneyFrame honeyFrame) {
        this.honeyFrame = honeyFrame;
    }

    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public double getHoneyQuantity() {
        return honeyQuantity;
    }

    public void setHoneyQuantity(double honeyQuantity) {
        this.honeyQuantity = honeyQuantity;
    }

    @Override
    public String toString() {
        return "Hive{" +
                "id=" + id +
                ", numberOfHoneyFrame=" + numberOfHoneyFrame +
                ", numberOfEggsFrame=" + numberOfEggsFrame +
                ", ageOfQueen=" + ageOfQueen +
                ", eggsFrames=" + eggsFrames +
                ", honeyFrame=" + honeyFrame +
                ", numberOfBees=" + numberOfBees +
                ", honeyQuantity=" + honeyQuantity +
                ", queen=" + queen +
                ", bees=" + bees +
                ", eggsBatches=" + eggsBatches +
                '}';
    }




    public void setHoneyFrames(List<HoneyFrame> honeyFrames) {

    }

    public void addEggsBatches(List<EggsBatch> eggsBatches) {
        this.eggsBatches.addAll(eggsBatches);
    }

    public void addEggsFrames(List<EggsFrame> eggsFrames) {
        this.eggsFrames.addAll(eggsFrames);
    }


    public List<EggsBatch> getEggsBatches() {
        return eggsBatches;
    }

    public List<EggsFrame> getEggsFrames() {
        return eggsFrames;
    }




    public void checkAndAddEggsToBees() {
        // after 21 days eggs hatch in bees. this method will check date when eggsBatch was laid
        //and add the number of eggs to bee number from hive.

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime(); // Get current date

        // Set calendar to 21 days ago
        calendar.add(Calendar.DAY_OF_MONTH, -21);
        Date cutoffDate = calendar.getTime();

        // Iterate over each eggs batch
        for (EggsBatch eggsBatch : eggsBatches) {
            // Check if the batch date is before the cutoff date
            if (eggsBatch.getCreationDate().before(cutoffDate)) {
                // Add the number of eggs to the number of bees
                numberOfBees += eggsBatch.getNumberOfEggs();
            }
        }
    }

}

