package com.marianbastiurea.lifeofbees.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionsOfTheWeek {
    private Map<ActionType, ActionOfTheWeek> actions;

    public ActionsOfTheWeek() {
        this.actions = new HashMap<>();
    }


    public ActionOfTheWeek findOrCreateAction(ActionType actionType) {
        return actions.computeIfAbsent(actionType, key -> new ActionOfTheWeek(key.name(), new HashMap<>()));
    }

    public void addOrUpdateAction(ActionType actionType, int hiveId) {
        ActionOfTheWeek action = findOrCreateAction(actionType);

        List<Integer> hiveIds = (List<Integer>) action.getData().getOrDefault("hiveIds", new ArrayList<>());
        if (!hiveIds.contains(hiveId)) {
            hiveIds.add(hiveId);
            action.getData().put("hiveIds", hiveIds);
        }
    }

    public void addOrUpdateActionWithPairs(ActionType actionType, List<Integer> hiveIdPair) {
        ActionOfTheWeek action = findOrCreateAction(actionType);

        List<List<Integer>> hiveIdPairs = (List<List<Integer>>) action.getData().getOrDefault("hiveIdPairs", new ArrayList<>());
        if (!hiveIdPairs.contains(hiveIdPair)) {
            hiveIdPairs.add(hiveIdPair);
            action.getData().put("hiveIdPairs", hiveIdPairs);
        }
    }

    public Map<ActionType, ActionOfTheWeek> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "ActionsOfTheWeek{" +
                "actions=" + actions +
                '}';
    }
}

