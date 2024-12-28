package com.marianbastiurea.lifeofbees.bees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO use static method
public class Honey {

    public Honey() {
    }

    public static HarvestingMonths getHarvestingMonth(LocalDate date) {
        int monthValue = date.getMonthValue();
        return HarvestingMonths.values()[monthValue - 3];
    }

    public static HoneyType honeyType(HarvestingMonths month, int dayOfMonth) {
        switch (month) {
            case MARCH, AUGUST, SEPTEMBER:
                return HoneyType.WildFlower;
            case APRIL:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return HoneyType.Rapeseed;
                } else {
                    return HoneyType.WildFlower;
                }
            case MAY:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return HoneyType.Acacia;
                } else {
                    return HoneyType.FalseIndigo;
                }
            case JUNE:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return HoneyType.Linden;
                } else {
                    return HoneyType.WildFlower;
                }
            case JULY:
                return HoneyType.SunFlower;
        }
        return null;
    }

    public static double honeyProductivity(HoneyType honeyType) {
        return switch (honeyType) {
            case Acacia -> 1;
            case Rapeseed -> 0.8;
            case WildFlower -> 0.75;
            case Linden -> 1;
            case SunFlower -> 0.8;
            case FalseIndigo -> 0.7;
        };
    }

    public static List<HoneyBatch> harvestHoney(Hive hive, HarvestingMonths month, int dayOfMonth) {
        List<HoneyBatch> honeyBatches = new ArrayList<>();
        double kgOfHoney = 0;
        if ((month.equals(HarvestingMonths.APRIL) || month.equals(HarvestingMonths.MAY) ||
                month.equals(HarvestingMonths.JUNE) || month.equals(HarvestingMonths.JULY)||month.equals(HarvestingMonths.AUGUST) &&
                        (dayOfMonth == 10 || dayOfMonth == 20))) {
            if (!hive.isItWasSplit()) {
                List<HoneyFrame> hiveHoneyFrames = hive.getHoneyFrames();
                for (HoneyFrame honeyFrame : hiveHoneyFrames) {
                    if (honeyFrame.isHarvestable()) {
                        kgOfHoney = honeyFrame.harvest();
                    }
                }
                if (kgOfHoney > 0) {
                    HoneyBatch honeyBatch = new HoneyBatch(hive.getId(), kgOfHoney, honeyType(month, dayOfMonth), false);
                    honeyBatches.add(honeyBatch);
                }
            }
        }
        return honeyBatches;
    }
}

