package com.marianbastiurea.lifeofbees.eggframe;

import com.marianbastiurea.lifeofbees.Honey;
import com.marianbastiurea.lifeofbees.Queen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class EggsBatch {
    private int numberOfEggs;
    private final Date creationDate;
    private Queen queen;

    EggsBatch(int numberOfEggs, Date creationDate) {
        this.numberOfEggs = numberOfEggs;
        this.creationDate = creationDate;
    }

    public EggsBatch(Queen queen) {
        this.queen = queen;
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
    public List<EggsBatch> makeBatchOfEggs(int numberOfEggs, Date date) {
        /*
        this method will create a daily eggs batch
         */

        List<EggsBatch> eggsBatches = new ArrayList<>();
        EggsBatch eggsBatch = new EggsBatch(numberOfEggs,date);
        eggsBatches.add(eggsBatch);
        return eggsBatches;

    }
}
