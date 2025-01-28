package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hive;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedBeesConsumer implements ActionOfTheWeekConsumer<String> {
    private static final Logger logger = LoggerFactory.getLogger(FeedBeesConsumer.class);

    @Override
    public void accept(LifeOfBees lifeOfBees, String answer) {
        if ("yes".equals(answer)) {
            int cost =lifeOfBees.getApiary().getHives().getHives().size() * 10;
            lifeOfBees.setMoneyInTheBank(lifeOfBees.getMoneyInTheBank() - cost);
        } else {
            for (Hive hive :lifeOfBees.getApiary().getHives().getHives()) {
                hive.getBeesBatches().removeLastTwoBeesBatches();
            }
        }
    }
}
