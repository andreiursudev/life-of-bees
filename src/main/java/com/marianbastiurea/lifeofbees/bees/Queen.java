package com.marianbastiurea.lifeofbees.bees;

import java.time.LocalDate;

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


    public int ageOneDay(LocalDate currentDate, double whetherIndex) {
        int numberOfEggs = 7000;
       // int numberOfEggs = (int) (2000 * this.ageOfQueenIndex() * Honey.honeyProductivity(Honey.honeyType(currentDate)) * whetherIndex);
        return numberOfEggs;
    }

    public double ageOfQueenIndex() {
        int ageOfQueen = this.getAgeOfQueen();
        switch (ageOfQueen) {
            case 0, 1, 2, 3:
                return 1;
            case 4:
                return 0.75;
            default:
                break;
        }
        return 0;
    }
}
