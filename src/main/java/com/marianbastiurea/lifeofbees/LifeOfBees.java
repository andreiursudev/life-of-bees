package com.marianbastiurea.lifeofbees;

import java.util.Random;

public class LifeOfBees {

    private static final int MAX_YEARS = 5;

    public static void main(String[] args) {
        Hive[] hives = new Hive[1];

        // Creating 1 hives with IDs from 1
//        for (int i = 0; i < hives.length; i++) {
//            hives[i] = new Hive(i + 1,3, 0); // Sample values for totalFrame, honeyFrame, ageOfQueen
//        }

//        // Simulating life of bees for each hive
//        for (int year = 0; year <= MAX_YEARS; year++) {
//            System.out.println("Year: " + year);
//            for (Hive hive : hives) {
//                ageOfQueen(hive);
//                System.out.println(hive);
//            }
//            System.out.println();
//        }
   }
   Frame frame =new Frame();


    private static void ageOfQueen(Hive hive) {
        int ageOfQueen = hive.getQueen().getAgeOfQueen();
        Random random = new Random();

        if (ageOfQueen == 4) {
            double randomNumber = random.nextDouble();
            if (randomNumber < 0.5) {
                // Reset age of queen to 0
                hive.getQueen().setAgeOfQueen(0);
                System.out.println("Age of Queen is " + hive.getQueen().getAgeOfQueen());
            } else {
                // Delete the hive
                hive = null;
            }
        } else {
            hive.getQueen().setAgeOfQueen(ageOfQueen + 1);
        }
        System.out.println("Age of Queen is at the end; " + hive.getQueen().getAgeOfQueen());
    }
}
