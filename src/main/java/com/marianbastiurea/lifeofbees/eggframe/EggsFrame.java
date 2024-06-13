package com.marianbastiurea.lifeofbees.eggframe;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
        return "{" +
                "numberOfEggs=" + getNumberOfEggs() +
                '}';
    }
    public void addEggs(int numberOfEggs, Date date){
        eggsBatches.add(new EggsBatch(numberOfEggs, date));
    }
}



