package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActionMoveEggsFrame {
    private String actionMoveEggsFrameMessage;
    private String actionMoveEggsFrameMarker;
    private List<List<Integer>> hiveIdPair;

    public ActionMoveEggsFrame(String actionMoveEggsFrameMessage, String actionMoveEggsFrameMarker, List<List<Integer>> hiveIdPair) {
        this.actionMoveEggsFrameMessage = actionMoveEggsFrameMessage;
        this.actionMoveEggsFrameMarker = actionMoveEggsFrameMarker;
        this.hiveIdPair = hiveIdPair;
    }

    public String getActionMoveEggsFrameMessage() {
        return actionMoveEggsFrameMessage;
    }

    public void setActionMoveEggsFrameMessage(String actionMoveEggsFrameMessage) {
        this.actionMoveEggsFrameMessage = actionMoveEggsFrameMessage;
    }

    public String getActionMoveEggsFrameMarker() {
        return actionMoveEggsFrameMarker;
    }

    public void setActionMoveEggsFrameMarker(String actionMoveEggsFrameMarker) {
        this.actionMoveEggsFrameMarker = actionMoveEggsFrameMarker;
    }

    public List<List<Integer>> getHiveIdPair() {
        return hiveIdPair;
    }

    public void setHiveIdPair(List<List<Integer>> hiveIdPair) {
        this.hiveIdPair = hiveIdPair;
    }

    @Override
    public String toString() {
        return "ActionMoveEggsFrame{" +
                "actionMoveEggsFrameMessage='" + actionMoveEggsFrameMessage + '\'' +
                ", actionMoveEggsFrameMarker='" + actionMoveEggsFrameMarker + '\'' +
                ", hiveIdPair=" + hiveIdPair +
                '}';
    }

    public static void addOrUpdateActionForEggsFrameMove(List<ActionMoveEggsFrame> actionsMoveEggsFrame, String newAction, String actionMarker, List<List<Integer>> hiveIdPair) {

        Optional<ActionMoveEggsFrame> existingAction = actionsMoveEggsFrame.stream()
                .filter(action -> action.getActionMoveEggsFrameMarker().equals(actionMarker))
                .findFirst();

        if (existingAction.isPresent()) {
            for (List<Integer> newPair : hiveIdPair) {
                boolean pairExists = existingAction.get().getHiveIdPair().stream()
                        .anyMatch(existingPair -> existingPair.equals(newPair)); // Verifică fiecare pereche în parte

                if (!pairExists) {
                    existingAction.get().getHiveIdPair().add(new ArrayList<>(newPair)); // Creează o copie pentru a evita modificările ulterioare
                }
            }
        } else {
            List<List<Integer>> newHiveIds = new ArrayList<>();
            for (List<Integer> pair : hiveIdPair) {
                newHiveIds.add(new ArrayList<>(pair));  // Creează copii ale perechilor pentru a evita modificările
            }
            ActionMoveEggsFrame actionMoveEggsFrame = new ActionMoveEggsFrame(newAction, actionMarker, newHiveIds);
            actionsMoveEggsFrame.add(actionMoveEggsFrame);
        }
    }
}
