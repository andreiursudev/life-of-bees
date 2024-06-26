package com.marianbastiurea.lifeofbees.eggframe;

import java.util.Date;


class EggBatch {
    private int numberOfEggs;
    private final Date creationDate;


    EggBatch(int numberOfEggs, Date creationDate) {
        this.numberOfEggs = numberOfEggs;
        this.creationDate = creationDate;
    }

    int getNumberOfEggs() {
        return numberOfEggs;
    }

    Date getCreationDate() {
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
