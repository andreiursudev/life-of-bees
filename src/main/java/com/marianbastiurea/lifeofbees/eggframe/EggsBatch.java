package com.marianbastiurea.lifeofbees.eggframe;

import java.util.Date;

class EggsBatch {
    private int numberOfEggs;
    private final Date creationDate;

    EggsBatch(int numberOfEggs, Date creationDate) {
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
