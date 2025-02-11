package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;

@FunctionalInterface
public interface ActionOfTheWeekConsumer<T> {
    void accept(LifeOfBees lifeOfBees, T data);
}
