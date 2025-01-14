package com.marianbastiurea.lifeofbees.action;

public enum ActionType<T>{
    ADD_EGGS_FRAME(new AddEggsFramesProducer(), new AddEggsFramesConsumer()),
    ADD_HONEY_FRAME(new AddHoneyFramesProducer(), new AddHoneyFramesConsumer()),
    MOVE_EGGS_FRAME(new MoveAnEggsFrameProducer(), new MoveAnEggsFrameConsumer()),
    FEED_BEES(new FeedBeesProducer(), new FeedBeesConsumer()),
    SPLIT_HIVE(new SplitHiveProducer(), new SplitHiveConsumer()),
    INSECT_CONTROL(new InsectControlProducer(), new InsectControllerConsumer()),
    HARVEST_HONEY(new HarvestHoneyProducer(), (o, o2) -> {
    });

    private final ActionOfTheWeekProducer <T> producer;
    private final ActionOfTheWeekConsumer <T> biConsumer;

    ActionType(ActionOfTheWeekProducer<?> producer, ActionOfTheWeekConsumer<?> biConsumer) {
        this.producer = producer;
        this.biConsumer = biConsumer;
    }

    public ActionOfTheWeekProducer<?> getProducer() {
        return producer;
    }

    public ActionOfTheWeekConsumer<?> getBiConsumer() {
        return biConsumer;
    }
}
