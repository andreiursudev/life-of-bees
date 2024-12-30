package com.marianbastiurea.lifeofbees.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        actions.put(value,data);
    }

    public Map<ActionType, Object> getActions() {
        return actions;
    }
}

