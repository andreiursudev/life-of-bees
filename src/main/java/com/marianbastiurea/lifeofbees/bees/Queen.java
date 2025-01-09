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
        System.out.println("weather index in Queen este: "+weatherIndex);
        System.out.println("Honey.honeyType(currentDate) in Queen este: "+Honey.honeyType(currentDate));
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
