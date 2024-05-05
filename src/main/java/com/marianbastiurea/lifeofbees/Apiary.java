package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Apiary {
    /* An apiary is a location where beehives of honey bees are kept.
    Our apiary will start with 1 beehive and we limit this number at 100.
    Our apiary will be in it's first year of existence and we will stop our iteration at year 100.
    At few years acacia or linden forest because of harsh weather will not have flower for bees
    and no honey from this two forest.

     */
    private int numberOfHives;
    private List<Hive> hives;
    private List<Honey> honeys;
    private Hive hive;

    public Apiary() {
        this.hives = new ArrayList<>();
        this.honeys = new ArrayList<>();
        this.numberOfHives=numberOfHives;
    }

    public int getNumberOfHives() {
        return numberOfHives;
    }

    public void setNumberOfHives(int numberOfHives) {
        this.numberOfHives = numberOfHives;
    }

    public Hive getHive() {
        return hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    public Apiary(List<Hive> hives) {
        this.hives = hives;
    }

    public List<Hive> getHives() {
        return hives;
    }

    public void setHives(List<Hive> hives) {
        this.hives = hives;
    }

    public List<Honey> getHoneys() {
        return honeys;
    }

    public void setHoneys(List<Honey> honeys) {
        this.honeys = honeys;
    }

    // Add a hive to the apiary
    public void addHive(Hive hive) {
        hives.add(hive);
    }

    // Add a honey to the apiary
    public void addHoney(Honey honey) {
        honeys.add(honey);
    }

    @Override
    public String toString() {
        return "Apiary{" +
                "numberOfHives=" + numberOfHives +
                ", hives=" + hives +
                ", honeys=" + honeys +
                ", hive=" + hive +
                '}';
    }

    public void splitHive(Hive hive) {
        if (this.getHive().getNumberOfEggsFrame() == 6) {
            // Create a new hive with 3 eggs frames and 3 honey frames
            Hive newHive = new Hive();
            newHive.setId(this.getNumberOfHives() + 1); // Set ID to numberOfHives + 1
            newHive.setNumberOfEggsFrame(3);
            newHive.setNumberOfHoneyFrame(3);
            newHive.setNumberOfBees((int) this.getHive().getNumberOfBees() / 2); //
            newHive.getQueen().setAgeOfQueen(0); // Set age of queen 0
            newHive.setEggsFrames(this.getHive().getEggsFrames().subList(3, 5));
            newHive.setHoneyFrames(this.getHive().getHoneyFrames().subList(3, 5));
            this.getHive().getEggsFrames().subList(3, 5).clear();
            this.getHive().getHoneyFrames().subList(3, 5).clear();
            this.addHive(newHive);
            this.setNumberOfHives(this.getNumberOfHives() + 1); // Increment numberOfHives
        }
    }
}