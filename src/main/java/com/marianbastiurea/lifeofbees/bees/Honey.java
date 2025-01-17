package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.time.Month;


public class Honey {

    public Honey() {
    }

    //TODO metoda asta nu are nevoie de nimic din clasa Honey ci doar de BeeTime currentDate. Muta metoda in clasa Beetime
    public static HoneyType honeyType(BeeTime currentDate) {
        Month month = currentDate.getMonth();
        int dayOfMonth = currentDate.getDayOfMonth();
        return switch (month) {
            case MARCH, AUGUST, SEPTEMBER -> HoneyType.WildFlower;
            case APRIL -> dayOfMonth <= 20
                    ? HoneyType.Rapeseed
                    : HoneyType.WildFlower;
            case MAY -> dayOfMonth <= 20
                    ? HoneyType.Acacia
                    : HoneyType.FalseIndigo;
            case JUNE -> dayOfMonth <= 20
                    ? HoneyType.Linden
                    : HoneyType.WildFlower;
            case JULY -> HoneyType.SunFlower;
            default -> HoneyType.WildFlower;
        };
    }


    //TODO Muta productivitatea intr-un camp din enumul HoneyType
    public static double honeyProductivity(HoneyType honeyType) {
        return switch (honeyType) {
            case Acacia, Linden -> 1;
            case Rapeseed, SunFlower -> 0.8;
            case WildFlower -> 0.75;
            case FalseIndigo -> 0.7;
        };
    }

}

