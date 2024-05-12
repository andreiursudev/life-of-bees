package com.marianbastiurea.lifeofbees;

import java.util.*;


/*

bee workers live 28-30 days during foraging and 6-8 months during winter
bee stage life:

 */

public class Bees {
    private int numberOfBees;
    private Hive hive;

    public Bees(int numberOfBees, Hive hive) {
        this.numberOfBees = numberOfBees;
        this.hive = hive;
    }

    public int getNumberOfBees() {
        return numberOfBees= hive.getNumberOfBees();
    }


    public Hive getHive() {
        return hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    public double addHoney() {
        //method to bring honey to the hive

        Random random = new Random();
         numberOfBees = hive.getNumberOfBees();
        int numberOfFlight = random.nextInt(3, 10);
        double kgOfHoney = numberOfBees * numberOfFlight * 0.00002;//0.02gr/flight/bee
        return kgOfHoney;
    }


}

