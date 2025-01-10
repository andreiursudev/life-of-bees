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


    public int ageOneDay(LocalDate currentDate, double weatherIndex) {
        return (int) (2200 * this.ageOfQueenIndex() * Honey.honeyProductivity(Honey.honeyType(currentDate)) * weatherIndex);
    }

    public double ageOfQueenIndex() {
        int ageOfQueen = this.getAgeOfQueen();
        return switch (ageOfQueen) {
            case 0, 1, 2, 3 -> 1;
            case 4 -> 0.75;
            default -> 0;
        };
    }
}
