package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
When the honeybee colony senses that it needs a new queen, perhaps because the queen is ailing or is preparing to
swarm, the worker bees will begin the process of raising new queen bees.
Feed with royal jelly.
Queen cell is place where new queen develops.
When the queen bee larva is ready to pupate (metamorphosize) into a queen, the worker bees will cap the cell, and
she will transform inside it.  At day 15, when she is fully developed, she will chew her way out of the cell with
the help of a few worker bees.
After the queen chews her way out of her queen cell, she has some dirty work to do...she instinctively sets out to
kill her sisters....the other new queens.
Once the battling is done, the queen will take a mating flight...a one-time flight from the hive to mate
with male drones.
After mating, she will return to the hive.  If her mother (the old queen) is still in the hive and nearing the end
of her life, the new queen will kill her (called 'supercedure').  If the old queen has left with a swarm, the new
queen will take over laying eggs.
In the summer, she can lay up to 2000 eggs a day!
A drone is a male bee. Unlike the female worker bee, a drone has no stinger.
 */

public class Queen {
    private int ageOfQueen;

    public Queen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }

    public Queen() {
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public void setAgeOfQueen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }


    public int makeEggs(Hive hive, int dayOfMonth, HarvestingMonths month) {

        int numberOfEggs = (int) (2000 * hive.ageOfQueenIndex(dayOfMonth,month));//*Whether.geWhetherIndex());
        // have to add another index, a  whetherIndex which will depend on quantity of honey made it
        return numberOfEggs;
    }

    // Method to make eggs and create a list of EggsBatch, which store daily
    // numberOfEggs and date from calendar when eggs are made
    public List<EggsBatch> makeBatchOfEggs(int numberOfEggs, Date date) {
        List<EggsBatch> eggsBatches = new ArrayList<>();
        EggsBatch eggsBatch = new EggsBatch(numberOfEggs, date);
        eggsBatches.add(eggsBatch);
        System.out.println("Eggs Batch are: " + eggsBatch);
        System.out.println("number of eggs are: " + numberOfEggs);
        return eggsBatches;

    }

    public List<EggsFrame> fillUpWithEggs(int numberOfEggsFrame, int numberOfEggs) {
        List<EggsFrame> eggsFrames = new ArrayList<>();
        EggsFrame eggsFrame = new EggsFrame(numberOfEggsFrame, numberOfEggs);
        eggsFrames.add(eggsFrame);

        return eggsFrames;
    }

}
