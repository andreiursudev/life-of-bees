package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

public class FeedBeesConsumer implements ActionOfTheWeekConsumer<String> {


    @Override
    public void accept(LifeOfBees lifeOfBees, String answer) {
        Apiary apiary = lifeOfBees.getApiary();
        if ("yes".equals(answer)) {
            int cost = apiary.getHives().getHives().size() * 10;
            lifeOfBees.setMoneyInTheBank(lifeOfBees.getMoneyInTheBank() - cost);
        } else {
            for (Hive hive : apiary.getHives().getHives()) {
                hive.getBeesBatches().removeLastTwoBeesBatches();
            }
        }
    }

}
