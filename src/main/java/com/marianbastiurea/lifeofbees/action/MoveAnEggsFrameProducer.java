package com.marianbastiurea.lifeofbees.action;


import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.bees.MoveEggFramePairHives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class MoveAnEggsFrameProducer implements ActionOfTheWeekProducer<List<MoveEggFramePairHives>> {
    private static final Logger logger = LoggerFactory.getLogger(MoveAnEggsFrameProducer.class);


    @Override
    public Optional<List<MoveEggFramePairHives>> produce(Hives hives) {
        logger.info("Starting produce method...");

        List<MoveEggFramePairHives> result = hives.checkIfCanMoveAnEggsFrame();
        if (result.isEmpty()) {
            logger.info("No valid hive pairs found.");
            return Optional.empty();
        } else {
            logger.info("Found valid hive pairs: {}", result);
            return Optional.of(result);
        }
    }
}
