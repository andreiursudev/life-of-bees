package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

@FunctionalInterface
public interface ActionOfTheWeekConsumer<T> {
    void accept(Hives hives, T data);
}
