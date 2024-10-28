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
        // Căutăm o acțiune existentă după actionType
        for (ActionOfTheWeek action : actionsOfTheWeek) {
            if (action.getActionType().equals(actionType)) {
                return action;
            }
        }
        // Dacă nu găsim, creăm una nouă și o adăugăm în listă
        ActionOfTheWeek newAction = new ActionOfTheWeek(actionType, new HashMap<>());
        actionsOfTheWeek.add(newAction);
        return newAction;
    }





    public static void addOrUpdateAction(String actionType, int hiveId, Map<String, Object> data, List<ActionOfTheWeek> actionsOfTheWeek) {
        ActionOfTheWeek action = actionsOfTheWeek.stream()
                .filter(a -> a.actionType.equals(actionType))
                .findFirst()
                .orElse(null);

        if (action == null) {
            // Creăm o nouă acțiune dacă nu există una cu același actionType
            action = new ActionOfTheWeek(actionType, new HashMap<>());
            action.actionType = actionType;
            action.data = new HashMap<>();
            actionsOfTheWeek.add(action);
        }

        // Adăugăm hiveId în date dacă nu există deja
        List<Integer> hiveIds = (List<Integer>) action.data.getOrDefault("hiveIds", new ArrayList<>());
        if (!hiveIds.contains(hiveId)) {
            hiveIds.add(hiveId);
            action.data.put("hiveIds", hiveIds);
        }
    }

    // Metoda pentru a adăuga sau actualiza o acțiune pentru o pereche de stupi
    public static void addOrUpdateAction1(String actionType, List<Integer> hiveIdPair, Map<String, Object> data, List<ActionOfTheWeek> actionsOfTheWeek) {
        ActionOfTheWeek action = actionsOfTheWeek.stream()
                .filter(a -> a.actionType.equals(actionType))
                .findFirst()
                .orElse(null);

        if (action == null) {
            // Creăm o nouă acțiune dacă nu există una cu același actionType
            action = new ActionOfTheWeek(actionType, new HashMap<>());
            action.actionType = actionType;
            action.data = new HashMap<>();
            actionsOfTheWeek.add(action);
        }

        // Adăugăm perechea de hiveId în date dacă nu există deja
        List<List<Integer>> hiveIdPairs = (List<List<Integer>>) action.data.getOrDefault("hiveIdPairs", new ArrayList<>());
        if (!hiveIdPairs.contains(hiveIdPair)) {
            hiveIdPairs.add(hiveIdPair);
            action.data.put("hiveIdPairs", hiveIdPairs);
        }
    }
}
