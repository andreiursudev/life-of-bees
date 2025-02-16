package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;

import java.util.Optional;

public interface ActionOfTheWeekProducer<T> {
    Optional<T> produce(Hives hives);
}
