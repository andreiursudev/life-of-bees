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

    public void executeActions(LifeOfBees lifeOfBees, Object data) {
        if (data == null) {
            System.out.println("No actions to do");
            return;
        }
        if (!(data instanceof Map)) {
            System.out.println("Invalid data format");
            return;
        }
        Map<String, Object> stringKeyActions = (Map<String, Object>) data;
        Map<ActionType, Object> actions = new HashMap<>();
        for (Map.Entry<String, Object> entry : stringKeyActions.entrySet()) {
            try {
                ActionType actionType = ActionType.valueOf(entry.getKey());
                actions.put(actionType, entry.getValue());
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown action type: " + entry.getKey());
            }
        }
        for (Map.Entry<ActionType, Object> action : actions.entrySet()) {
            ActionType actionType = action.getKey();
            Object actionData = action.getValue();
            actionType.getBiConsumer().accept(lifeOfBees, actionData);
        }
    }
}

