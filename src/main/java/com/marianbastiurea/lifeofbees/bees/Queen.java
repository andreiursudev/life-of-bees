package com.marianbastiurea.lifeofbees.bees;

import java.util.Objects;

import static com.marianbastiurea.lifeofbees.bees.ApiaryParameters.maxEggsDailyLaidByQueen;

public class Queen {
    private int ageOfQueen;
    public double feedBeesIndex;

    public Queen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
        this.feedBeesIndex=1.0;
    }

    public void setFeedBeesIndex(double feedBeesIndex) {
        this.feedBeesIndex = feedBeesIndex;
    }

    public Queen() {
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public void setAgeOfQueen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }

    public int makeEggs(double productivity, double weatherIndex) {
        return (int) (maxEggsDailyLaidByQueen * this.ageOfQueenIndex() * productivity * weatherIndex*feedBeesIndex);
    }

    public double ageOfQueenIndex() {
        return switch (ageOfQueen) {
            case 0, 1, 2, 3 -> 1;
            case 4 -> 0.75;
            default -> 0;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return ageOfQueen == queen.ageOfQueen &&
                Double.compare(queen.feedBeesIndex, feedBeesIndex) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ageOfQueen, feedBeesIndex);
    }
    @Override
    public String toString() {
        return "Queen{" +
                "ageOfQueen=" + ageOfQueen +
                ", feedBeesIndex=" + feedBeesIndex +
                '}';
    }
}
