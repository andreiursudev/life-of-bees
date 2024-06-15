package com.marianbastiurea.lifeofbees.eggframe;


import com.marianbastiurea.lifeofbees.BeesBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.*;

public class EggsFrame {

    private int maxEggPerFrame = 6400;

    private List<EggsBatch> eggsBatches;

    public EggsFrame() {
        this.eggsBatches = new ArrayList<>();
    }

    public int getNumberOfEggs() {
        return eggsBatches.stream().mapToInt(EggsBatch::getNumberOfEggs).sum();
    }

    public int getMaxEggPerFrame() {
        return maxEggPerFrame;
    }

    @Override
    public String toString() {
        return "EggsFrame{" +
                "numberOfEggs=" + getNumberOfEggs() +
                " , eggsBatches=" + eggsBatches +
                '}'+"\n";
    }

    public void addEggs(int numberOfEggs, Date date){
        eggsBatches.add(new EggsBatch(numberOfEggs, date));
    }
    public List<BeesBatch> checkAndHatchEggs(Date currentDate) {
        List<BeesBatch> hatchedBatches = new ArrayList<>();
        Iterator<EggsBatch> iterator = eggsBatches.iterator();

        while (iterator.hasNext()) {
            EggsBatch eggsBatch = iterator.next();
            Date creationDate = eggsBatch.getCreationDate();
            long differenceInMillisecond = Math.abs(currentDate.getTime() - creationDate.getTime());
            long differenceInDays = differenceInMillisecond / (24 * 60 * 60 * 1000);

            if (differenceInDays > 21) {
                hatchedBatches.add(new BeesBatch(eggsBatch.getNumberOfEggs(), currentDate));
                iterator.remove();
            }
        }
        return hatchedBatches;
    }
}



