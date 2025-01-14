package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;

public interface ActionOfTheWeekProducer<T> {
    T produce(LifeOfBees lifeOfBees);
}
