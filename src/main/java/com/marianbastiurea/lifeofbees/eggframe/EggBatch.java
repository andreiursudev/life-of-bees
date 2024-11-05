package com.marianbastiurea.lifeofbees.eggframe;

import java.time.LocalDate;


class EggBatch {
    private int numberOfEggs;
    private final LocalDate creationDate;

    EggBatch(int numberOfEggs, LocalDate creationDate) {
        this.numberOfEggs = numberOfEggs;
        this.creationDate = creationDate;
    }

    int getNumberOfEggs() {
        return numberOfEggs;
    }

    LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "EggsBatch{" +
                "numberOfEggs=" + numberOfEggs +
                ", creationDate=" + creationDate +
                '}';
    }

}
