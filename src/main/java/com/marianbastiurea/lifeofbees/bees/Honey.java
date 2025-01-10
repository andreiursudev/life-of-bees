package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.time.BeeTime;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

public class Honey {

    public Honey() {
    }

    public static HoneyType honeyType(LocalDate currentDate) {
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


    public static double honeyProductivity(HoneyType honeyType) {
        return switch (honeyType) {
            case Acacia, Linden -> 1;
            case Rapeseed, SunFlower -> 0.8;
            case WildFlower -> 0.75;
            case FalseIndigo -> 0.7;
        };
    }

    public static List<HoneyBatch> harvestHoney(Hive hive, LocalDate currentDate) {
        if (!BeeTime.timeToHarvestHive(currentDate) || hive.isItWasSplit()) {
            return Collections.emptyList();
        }
        double harvestedHoney = hive.getHoneyFrames().harvestHoneyFromHoneyFrames();
        if (harvestedHoney <= 0) {
            return Collections.emptyList();
        }

        hive.setItWasHarvested(true);
        HoneyBatch honeyBatch = new HoneyBatch(
                hive.getId(),
                harvestedHoney,
                honeyType(currentDate),
                false
        );

        return List.of(honeyBatch);
    }

}

