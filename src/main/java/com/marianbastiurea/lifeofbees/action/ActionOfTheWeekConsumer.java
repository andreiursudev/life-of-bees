package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;

public interface ActionOfTheWeekConsumer {
    Object accept(Apiary apiary,Object data);
}
