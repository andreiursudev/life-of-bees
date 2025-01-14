package com.marianbastiurea.lifeofbees.action;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ActionsOfTheWeek {
    private Map<ActionType, Object> actions;

    public ActionsOfTheWeek() {
        this.actions = new HashMap<>();
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

    public void createActions(LifeOfBees lifeOfBees) {
        actions.clear();
        for (ActionType actionType : ActionType.values()) {
            Object data = actionType.getProducer().produce(lifeOfBees);
            if (data != null &&
                    !(data instanceof Number && ((Number) data).intValue() == 0) &&
                    !(data instanceof Collection && ((Collection<?>) data).isEmpty())) {
                actions.put(actionType, data);
            }
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
