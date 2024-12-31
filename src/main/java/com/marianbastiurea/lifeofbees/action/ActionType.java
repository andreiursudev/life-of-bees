package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;

import java.util.function.BiConsumer;

public enum ActionType {
    ADD_EGGS_FRAME(new AddEggsFramesProducer(), new AddEggsFramesConsumer()),
    ADD_HONEY_FRAME(o -> null, (o, o2) -> {}),
    MOVE_EGGS_FRAME(o -> null, (o, o2) -> {}),
    FEED_BEES(o -> null, (o, o2) -> {}),
    SPLIT_HIVE(o -> null, (o, o2) -> {}),
    INSECT_CONTROL(o -> null, (o, o2) -> {}),
    HIBERNATE(o -> null, (o, o2) -> {}),
    HARVEST_HONEY(o -> null, (o, o2) -> {});

    private final ActionOfTheWeekProducer producer;
    private final BiConsumer<Apiary, Object> biConsumer;

    ActionType(ActionOfTheWeekProducer producer, BiConsumer<Apiary, Object> biConsumer) {
        this.producer = producer;
        this.biConsumer = biConsumer;
    }

    public ActionOfTheWeekProducer getProducer() {
        return producer;
    }

    public BiConsumer<Apiary, Object> getConsumer() {
        return biConsumer;
    }
}
