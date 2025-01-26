package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Apiary;
import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.List;
import java.util.Optional;

public class MoveAnEggsFrameProducer implements ActionOfTheWeekProducer<List<List<Integer>>> {

    @Override
    public Optional<List<List<Integer>>> produce(Hives hives) {
        List<List<Integer>> result = hives.checkIfCanMoveAnEggsFrame();
        return result.isEmpty() ? Optional.empty() : Optional.of(result);
    }
}
