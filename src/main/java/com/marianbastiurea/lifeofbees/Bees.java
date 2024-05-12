package com.marianbastiurea.lifeofbees;

import java.util.*;


/*

bee workers live 28-30 days during foraging and 6-8 months during winter
bee stage life:

 */

public class Bees {


    public double addHoney(Hive hive) {
        //method to bring honey to the hive
// add a new honey frame until maximum of 6
        Random random = new Random();
        int numberOfBees = hive.getNumberOfBees();
        int numberOfFlight = random.nextInt(3, 10);
        double kgOfHoney = numberOfBees * numberOfFlight * 0.00002;//0.02gr/flight/bee
        return kgOfHoney;
    }


}


//they die/ dissapear

