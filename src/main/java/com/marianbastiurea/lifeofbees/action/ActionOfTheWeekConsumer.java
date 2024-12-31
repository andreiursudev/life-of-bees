package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;

public interface ActionOfTheWeekConsumer {
    void accept(Apiary apiary,Object data);
}
