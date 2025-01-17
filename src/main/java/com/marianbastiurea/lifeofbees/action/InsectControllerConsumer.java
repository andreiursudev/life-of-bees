package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

public class InsectControllerConsumer implements ActionOfTheWeekConsumer<String> {


    @Override
    public void accept(LifeOfBees lifeOfBees, String answer) {
        Apiary apiary = lifeOfBees.getApiary();
        //TODO Foloseste boolean in loc de yes
        if ("yes".equals(answer)) {
            int cost = apiary.getHives().size() * 10;
            lifeOfBees.setMoneyInTheBank(lifeOfBees.getMoneyInTheBank() - cost);
        } else {
            apiary.removeLastTwoBeesBatches();
        }
    }
}
