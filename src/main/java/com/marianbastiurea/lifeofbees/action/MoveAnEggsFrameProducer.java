package com.marianbastiurea.lifeofbees.action;


import com.marianbastiurea.lifeofbees.bees.Hives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class MoveAnEggsFrameProducer implements ActionOfTheWeekProducer<List<List<Integer>>> {
    private static final Logger logger = LoggerFactory.getLogger(MoveAnEggsFrameProducer.class);


    @Override
    public Optional<List<List<Integer>>> produce(Hives hives) {
        logger.info("Starting produce method...");

        List<List<Integer>> result = hives.checkIfCanMoveAnEggsFrame();

        if (result.isEmpty()) {
            logger.info("No valid hive pairs found.");
            return Optional.empty();
        } else {
            logger.info("Found valid hive pairs: {}", result);
            return Optional.of(result);
        }
    }

}
