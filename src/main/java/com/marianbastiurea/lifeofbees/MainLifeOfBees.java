package com.marianbastiurea.lifeofbees;

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
        LifeOfBees lifeOfBees = new LifeOfBees();
        lifeOfBees.createHives(numberOfStartingHives);
        lifeOfBees.iterateOverTwoYears();

    }
}

