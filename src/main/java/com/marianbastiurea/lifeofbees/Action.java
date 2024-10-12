package com.marianbastiurea.lifeofbees;

public class Action {
    public String actionOfTheWeek;

    public Action(String actionOfTheWeek) {
        this.actionOfTheWeek = actionOfTheWeek;
    }

    public Action() {
    }

    public String getActionOfTheWeek() {
        return actionOfTheWeek;
    }

    public void setActionOfTheWeek(String actionOfTheWeek) {
        this.actionOfTheWeek = actionOfTheWeek;
    }

    public String actionType(HarvestingMonths month, int dayOfMonth) {
        switch (month) {
            case MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER:

                if (dayOfMonth == 1 || dayOfMonth == 21) {
                    return actionOfTheWeek= "Insect Control";
                }
                break;
        }
            return actionOfTheWeek="weekly checking";
    }
}
