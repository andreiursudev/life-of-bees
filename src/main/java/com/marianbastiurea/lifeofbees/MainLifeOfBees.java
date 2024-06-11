package com.marianbastiurea.lifeofbees;

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
        String honeyType = "Rapeseed";
        Apiary apiary=new Apiary(hives,new ArrayList<>());
        for (int i = 1; i < numberOfStartingHives + 1; i++) {
            int ageOfQueen = random.nextInt(1, 6);
            int numberOfHoneyFrame = random.nextInt(3, 4);
            int randomNumberOfEggs = random.nextInt(4500, 5000);
            double kgOfHoney = random.nextDouble(2.5, 3);
            List<EggsFrame> eggsFrames = new ArrayList<>();
             random.nextInt(3, 4);
            for (int j = 1; j < random.nextInt(3, 4); j++) {
                eggsFrames.add(new EggsFrame(randomNumberOfEggs));
            }
            List<HoneyFrame> honeyFrames = new ArrayList<>();
            for (int k = 0; k < numberOfHoneyFrame; k++) {
                honeyFrames.add(new HoneyFrame(kgOfHoney, honeyType));
            }
            int numberOfBees = random.nextInt(2000, 2500) * (numberOfHoneyFrame + eggsFrames.size());
            Hive hive = new Hive(apiary,
                    i,
                    false,
                    false,
                    false,
                    new ArrayList<>(),
                    eggsFrames,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new Honey(honeyType),
                    new Queen(ageOfQueen),
                    numberOfHoneyFrame,
                    numberOfBees);
            hives.add(hive);
        }

        IWeather whether = new Whether();

        System.out.println("First " + numberOfStartingHives + " are " + hives);


        LifeOfBees lifeOfBees = new LifeOfBees(new Apiary(hives, new ArrayList<>()));

        lifeOfBees.iterateOverTwoYears(whether);

    }
}

