package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.util.Optional;

public abstract class WeeklyProducerAbstract <T> implements ActionOfTheWeekProducer<T>{
    public abstract Optional<Boolean> produce(BeeTime beetime);

}
