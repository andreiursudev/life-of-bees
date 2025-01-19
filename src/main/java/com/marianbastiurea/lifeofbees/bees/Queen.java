package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;

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

    public int iterateOneDay(BeeTime currentDate, double weatherIndex) {
        HoneyType honeyType = currentDate.honeyType();
        double productivity = honeyType.getProductivity();
        return (int) (2200 * this.ageOfQueenIndex() * productivity * weatherIndex);
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
