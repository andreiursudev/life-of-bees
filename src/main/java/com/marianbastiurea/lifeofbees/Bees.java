package com.marianbastiurea.lifeofbees;

import java.util.*;


/*

bee workers live 28-30 days during foraging and 6-8 months during winter
bee stage life:

 */

public class Bees {
    private int numberOfBees;

    public Bees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public Bees() {
    }

    public int getNumberOfBees() {
        return numberOfBees= numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }


    @Override
    public String toString() {
        return "Bees{" +
                "numberOfBees=" + numberOfBees +
                '}';
    }

    public double addHoney() {
        //method to bring honey to the hive

        Random random = new Random();
         numberOfBees = this.getNumberOfBees();
        int numberOfFlight = random.nextInt(3, 10);
        double kgOfHoney = numberOfBees * numberOfFlight * 0.00002;//0.02gr/flight/bee
        return kgOfHoney;
    }


}

