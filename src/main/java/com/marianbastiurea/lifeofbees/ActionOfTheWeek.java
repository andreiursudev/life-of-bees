package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionOfTheWeek {
    private String actionType;
    private Map<String, Object> data;

    public ActionOfTheWeek(String actionType, Map<String, Object> data) {
        this.actionType = actionType;
        this.data = data;
    }

    public ActionOfTheWeek() {
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "ActionOfTheWeek{" +
                "actionType='" + actionType + '\'' +
                ", data=" + data +
                '}';
    }

    public static ActionOfTheWeek findOrCreateAction(String actionType, List<ActionOfTheWeek> actionsOfTheWeek) {
        return actionsOfTheWeek.stream()
                .filter(action -> action.getActionType().equals(actionType))
                .findFirst()
                .orElseGet(() -> {
                    ActionOfTheWeek newAction = new ActionOfTheWeek(actionType, new HashMap<>());
                    actionsOfTheWeek.add(newAction);
                    return newAction;
                });
    }

    public void addOrUpdateAction(String actionType, int hiveId, Map<String, Object> data, List<ActionOfTheWeek> actionsOfTheWeek) {
        ActionOfTheWeek action = findOrCreateAction(actionType, actionsOfTheWeek);

        List<Integer> hiveIds = (List<Integer>) action.getData().getOrDefault("hiveIds", new ArrayList<>());
        if (!hiveIds.contains(hiveId)) {
            hiveIds.add(hiveId);
            action.getData().put("hiveIds", hiveIds);
        }
    }

    public void addOrUpdateAction1(String actionType, List<Integer> hiveIdPair, Map<String, Object> data, List<ActionOfTheWeek> actionsOfTheWeek) {
        ActionOfTheWeek action = actionsOfTheWeek.stream()
                .filter(a -> a.actionType.equals(actionType))
                .findFirst()
                .orElse(null);
        if (action == null) {
            action = new ActionOfTheWeek(actionType, new HashMap<>());
            action.actionType = actionType;
            action.data = new HashMap<>();
            actionsOfTheWeek.add(action);
        }
        List<List<Integer>> hiveIdPairs = (List<List<Integer>>) action.data.getOrDefault("hiveIdPairs", new ArrayList<>());
        if (!hiveIdPairs.contains(hiveIdPair)) {
            hiveIdPairs.add(hiveIdPair);
            action.data.put("hiveIdPairs", hiveIdPairs);
        }
    }
}
