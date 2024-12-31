package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hive;

import java.util.ArrayList;
import java.util.List;

public class MoveAnEggsFrameProducer implements ActionOfTheWeekProducer {
    @Override
    public Object produce(Apiary apiary) {
        return  apiary.checkIfCanMoveAnEggsFrame();
    }
}
