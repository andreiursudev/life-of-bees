package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

public class FeedBeesConsumer implements ActionOfTheWeekConsumer<String> {


    @Override
    public void accept(Hives hives, String answer) {
        LifeOfBees lifeOfBees = new LifeOfBees();
        if ("yes".equals(answer)) {
            int cost =hives.getHives().size() * 10;
            lifeOfBees.setMoneyInTheBank(lifeOfBees.getMoneyInTheBank() - cost);
        } else {
            for (Hive hive : hives.getHives()) {
                hive.getBeesBatches().removeLastTwoBeesBatches();
            }
        }
    }

}
