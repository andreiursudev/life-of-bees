package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;

public class FeedBeesProducer implements ActionOfTheWeekProducer {
    @Override
    public Object produce(Apiary apiary) {
        return  apiary.checkIfCanMoveAnEggsFrame();
    }
}
