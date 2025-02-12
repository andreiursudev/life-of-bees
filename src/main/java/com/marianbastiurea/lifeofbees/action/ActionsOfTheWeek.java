package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.bees.Hives;
import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class ActionsOfTheWeek {
    private Map<ActionType, Object> actions;

    public ActionsOfTheWeek() {
        this.actions = new EnumMap<>(ActionType.class);
    }

    @Override
    public String toString() {
        return "ActionsOfTheWeek{" +
                "actions=" + actions +
                '}';
    }

    public void put(ActionType value, Object data) {
        actions.put(value, data);
    }

    public Map<ActionType, Object> getActions() {
        return actions;
    }


    public void createActions(Hives hives) {
        actions.clear();
        for (ActionType actionType : ActionType.values()) {
            Optional<?> dataOptional = actionType.getProducer().produce(hives);
            dataOptional.ifPresent(data -> actions.put(actionType, data));
        }
    }

    public void executeActions(LifeOfBees lifeOfBees, Map<ActionType, Object> actions) {
        if (actions == null || actions.isEmpty()) {
            System.out.println("No actions to do");
            return;
        }

        for (Map.Entry<ActionType, Object> action : actions.entrySet()) {
            ActionType actionType = action.getKey();
            Object actionData = action.getValue();

            try {

                ActionOfTheWeekConsumer biConsumer = actionType.getBiConsumer();
                biConsumer.accept(lifeOfBees, actionData);
            } catch (ClassCastException e) {
                System.out.println("Error: Mismatched type for action " + actionType.name());
            }
        }
    }


}
