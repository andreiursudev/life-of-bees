package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.Optional;

public interface ActionOfTheWeekProducer<T> {
    Optional<T> produce(LifeOfBees lifeOfBees);
}
