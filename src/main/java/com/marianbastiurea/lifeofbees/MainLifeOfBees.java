package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.eggframe.EggFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainLifeOfBees {

    public static void main(String[] args) {
        int numberOfStartingHives;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please input number of starting hive from apiary. ");
            System.out.println("You have to insert a number lower than 10: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter an integer: ");
                scanner.next();
            }
            numberOfStartingHives = scanner.nextInt();
            if (numberOfStartingHives > 11) {
                System.out.println("Input should be lower than 10. Please try again.");
            }
        } while (numberOfStartingHives > 11);

        Random random = new Random();


        List<Hive> hives = new ArrayList<>();
        String honeyType = "WildFlower";

        Apiary apiary=new Apiary(hives,new ArrayList<>());
        for (int i = 1; i < numberOfStartingHives + 1; i++) {
            int ageOfQueen = random.nextInt(1, 6);
            double kgOfHoney = random.nextDouble(2.5, 3);
            List<EggFrame> eggFrames = new ArrayList<>();
            for (int j = 1; j < random.nextInt(5, 6); j++) {
                eggFrames.add(new EggFrame());
            }
            List<HoneyFrame> honeyFrames = new ArrayList<>();
            for (int k = 1; k < random.nextInt(3, 4); k++) {
               // honeyFrames.add(new HoneyFrame());
                honeyFrames.add(new HoneyFrame(random.nextDouble(2.5, 3), "WildFlower"));
            }
            int numberOfBees = random.nextInt(2000, 2500) * (honeyFrames.size() + eggFrames.size());
            Hive hive = new Hive(apiary,
                    i,
                    false,
                    false,
                    false,
                    eggFrames,
                    honeyFrames,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Honey(honeyType),
                    new Queen(ageOfQueen),
                    numberOfBees);
            hives.add(hive);
        }

        IWeather whether = new Whether();

        System.out.println("First " + numberOfStartingHives + " are " + hives);


        LifeOfBees lifeOfBees = new LifeOfBees(new Apiary(hives, new ArrayList<>()));

        lifeOfBees.iterateOverTwoYears(whether);

    }
}

