package com.marianbastiurea.lifeofbees;

import java.util.LinkedList;

public class EggFrames {

    private final int maxEggPerFrame = 6400;
    private int numberOfEggFrames;

    
    LinkedList<Integer> eggBatches = new LinkedList<>();

    public boolean isFull() {
        //return sum eggBatches == numberOfEggFrames * maxEggPerFrame
        return true;
    }

    public boolean is80PercentFull(){
        //return sum eggBatches > numberOfEggFrames * maxEggPerFrame * 80%
        return true;
    }

}
