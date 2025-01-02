package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.function.BiConsumer;

public enum ActionType {
    ADD_EGGS_FRAME(new AddEggsFramesProducer(), new AddEggsFramesConsumer()),
    ADD_HONEY_FRAME(new AddHoneyFrameProducer(), (o, o2) -> {}),
    MOVE_EGGS_FRAME(new MoveAnEggsFrameProducer(), (o, o2) -> {}),
    FEED_BEES(new FeedBeesProducer(), (o, o2) -> {}),
    SPLIT_HIVE(new SplitHiveProducer(), (o, o2) -> {}),
    INSECT_CONTROL(new InsectControlProducer(), (o, o2) -> {}),
    HIBERNATE(new HibernateProducer(), (o, o2) -> {}),
    HARVEST_HONEY(new HarvestHoneyProducer(), (o, o2) -> {});

    private final ActionOfTheWeekProducer producer;
    private final ActionOfTheWeekConsumer biConsumer;

    ActionType(ActionOfTheWeekProducer producer, ActionOfTheWeekConsumer biConsumer) {
        this.producer = producer;
        this.biConsumer = biConsumer;
    }

    public ActionOfTheWeekProducer getProducer() {
        return producer;
    }

    public ActionOfTheWeekConsumer getBiConsumer() {
        return biConsumer;
    }
}
