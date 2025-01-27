package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;


public class InsectControlProducer implements ActionOfTheWeekProducer<Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(InsectControlProducer.class);


    @Override
    public Optional<Boolean> produce(Hives hives) {
        boolean canInsectControl = hives.checkInsectControl();
        return canInsectControl ? Optional.of(true) : Optional.empty();
    }
}
