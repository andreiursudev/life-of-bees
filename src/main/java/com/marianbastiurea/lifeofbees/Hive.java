package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.time.Month;

public class Hive {
    private double honeyFrames;
    private static double eggsFrames;
    private static int cellsForEggs=6400;

    public Hive(double honeyFrames, double eggsFrames) {
        this.honeyFrames = honeyFrames;
        this.eggsFrames = eggsFrames;
    }

    public double getEggsFrames() {
        return eggsFrames;
    }

    public double getHoneyFrames() {
        return honeyFrames;
    }

    public void addEggFrames(double eggs) {
        eggsFrames += eggs;
    }

    public static int getDailyEggProduction(Month month, int dayOfMonth) {
        switch (month) {
            case MARCH:
                return 1000;
            case APRIL:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return 2000;
                } else if (dayOfMonth >= 21 && dayOfMonth <= 30) {
                    return 1000;
                }
                break;
            case MAY:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return 2000;
                }
                break;
            default:
                break;
        }
        return 0; // Default value if month and day combination doesn't match any condition
    }

    public static LocalDate layEggs(int eggsPerDay, int eggsFrames) {
        int daysToFill=(int)eggsFrames*cellsForEggs/eggsPerDay+1;
        System.out.println("Queen start laying eggs on 1st of March");
        System.out.println(" Queen finish to fill up first "+eggsFrames+" frames in "+daysToFill+" days");
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 3, 1);
        System.out.println(" Today is "+startDate.plusDays(daysToFill - 1));
        System.out.println("You have to decide if you add another one frames or wait few days");
        return startDate.plusDays( daysToFill- 1);
    }
}
