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


    public Apiary() {
        this.hives = new ArrayList<>();
        this.honeys = new ArrayList<>();
        this.numberOfHives = numberOfHives;
    }

    public int getNumberOfHives() {
        return numberOfHives;
    }

    public void setNumberOfHives(int numberOfHives) {
        this.numberOfHives = numberOfHives;
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
        return "{" +
                "numberOfHives=" + this.numberOfHives +
                ", hives=" + this.hives +
                ", honeys=" + this.honeys +
                '}';
    }

    public void splitHive() {
        for (Hive hive : hives) {
            if (hive.getNumberOfEggsFrame() == 6) {
                // Create a new hive with 3 eggs frames and 3 honey frames
                Hive newHive = new Hive();
                newHive.setId(this.getNumberOfHives() + 1);
                newHive.setNumberOfEggsFrame(3);
                newHive.setNumberOfHoneyFrame(3);
                newHive.setNumberOfBees((int) hive.getNumberOfBees() / 2);
                Queen queen = new Queen();
                newHive.setQueen(queen);
                newHive.getQueen().setAgeOfQueen(0);
                newHive.setEggsFrames(hive.getEggsFrames().subList(3, 6));
                newHive.setHoneyFrames(hive.getHoneyFrames().subList(0, 2));
              //  hive.getEggsFrames().remove(3);
                hive.setNumberOfEggsFrame(3);

               // hive.getEggsFrames().subList(3, 5).clear();
               // hive.getHoneyFrames().subList(3, 5).clear();
                this.addHive(newHive);
                this.setNumberOfHives(this.getNumberOfHives() + 1);
            }
        }
    }
}