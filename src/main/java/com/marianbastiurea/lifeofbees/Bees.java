package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

/*

bee workers live 28-30 days during foraging and 6-8 months during winter
bee stage life:

 */


public class Bees {
    private int numberOfBees;
    private List<EggsBatch> eggsBatchList;

    public Bees() {
        this.eggsBatchList = new ArrayList<>();
    }

    //method bring honey to the hive
    public double addHoney() {
        Hive hive = new Hive();
        Random random = new Random();
        numberOfBees = hive.getNumberOfBees();
        int numberOfFlight = random.nextInt(1, 7);
        double kgOfHoney = numberOfBees * numberOfFlight * 0.00002;//0.02gr/flight/bee
        return kgOfHoney;
    }

    @Override
    public String toString() {
        return "Bees{" +
                "numberOfBees=" + numberOfBees +
                ", eggsBatchList=" + eggsBatchList +
                '}';
    }

    public void addBees(int numberOfEggs, Date currentDate) {
        EggsBatch eggsBatch = new EggsBatch(numberOfEggs, currentDate);
        eggsBatchList.add(eggsBatch);


        // Check if 21 days have passed
        if (isTwentyOneDays(currentDate)) {
            int totalEggs = 0;
            for (EggsBatch batch : eggsBatchList) {
                totalEggs += batch.getNumberOfEggs();
            }
        }

    }
    private boolean isTwentyOneDays (Date currentDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth == 21; // Check if it's the 21st day of the month
    }
//        public int deadBees () {
//        }




    public static void main(String[] args) {
            Bees bees = new Bees();
            Date currentDate = new Date(); // Use current date

            // Adding eggs batches for testing
            for (int i = 0; i < 25; i++) {
                bees.addBees((int) (Math.random() * 100), currentDate); // Random number of eggs
            }

            // Printing eggs batch list size (should be 0 after processing 21 days)
            //System.out.println("Eggs Batch List Size: " + bees.eggsBatchList.size());
        }
    //they die/ dissapear
}
