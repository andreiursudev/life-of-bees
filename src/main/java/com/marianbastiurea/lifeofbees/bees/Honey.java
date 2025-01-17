package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.time.Month;


public class Honey {

    public Honey() {
    }

    //TODO metoda asta nu are nevoie de nimic din clasa Honey ci doar de BeeTime currentDate.
    // Muta metoda in clasa Beetime


    public static double honeyProductivity(HoneyType honeyType) {
        return switch (honeyType) {
            case Acacia, Linden -> 1;
            case Rapeseed, SunFlower -> 0.8;
            case WildFlower -> 0.75;
            case FalseIndigo -> 0.7;
        };
    }

}

