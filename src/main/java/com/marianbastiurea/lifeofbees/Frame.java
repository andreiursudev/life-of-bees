package com.marianbastiurea.lifeofbees;

public class Frame {



    public void fillUpWithEggsFrame() {
        Hive hive = new Hive();
        int numberOfFrame = hive.getNumberOfEggsFrame();
        System.out.println("number of frames are: "+numberOfFrame);
        int numberOfEggs = hive.getQueen().makeEggs();
        System.out.println("number of eggs: "+numberOfEggs);
        int maxOfEggsPerFrame = 6400;
        int eggsAdd = 0;
        int i=0;
        while(i< numberOfFrame+1){
            eggsAdd += numberOfEggs;
            if (eggsAdd>maxOfEggsPerFrame){
                i++;
                eggsAdd=6400-eggsAdd;
            }
            System.out.println("eggs to add: "+eggsAdd);
        }
    }
}
