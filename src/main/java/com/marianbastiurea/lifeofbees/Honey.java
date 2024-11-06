package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Honey {
    //todo: remove honeyType field

    private String honeyType;

    public Honey(String honeyType) {
        this.honeyType = honeyType;
    }

    public Honey() {
    }

    public String getHoneyType() {
        return honeyType;
    }

    public void setHoneyType(String honeyType) {
        this.honeyType = honeyType;
    }

    @Override
    public String toString() {
        return "Honey{" +
                "honeyType='" + honeyType + '\'' +
                '}';
    }

    public HarvestingMonths getHarvestingMonth(LocalDate date) {
        int monthValue = date.getMonthValue();
        return HarvestingMonths.values()[monthValue - 3];
    }

    public String honeyType(HarvestingMonths month, int dayOfMonth) {
        switch (month) {
            case MARCH, AUGUST, SEPTEMBER:
                return "WildFlower";
            case APRIL:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return "Rapeseed";
                } else if (dayOfMonth >= 21 && dayOfMonth <= 30) {
                    return "WildFlower";
                }
                break;
            case MAY:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return "Acacia";
                } else if (dayOfMonth >= 21 && dayOfMonth <= 31) {
                    return "FalseIndigo";
                }
                break;
            case JUNE:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return "Linden";
                } else if (dayOfMonth >= 21 && dayOfMonth <= 30) {
                    return "WildFlower";
                }
                break;
            case JULY:
                return "SunFlower";
            default:
                return "No honey type available";
        }
        return "No honey type available";
    }

    // create HoneyType enum and pass it as argument to this method
    public double honeyProductivity(/*HoneyType honeyType*/) {
        int indexHoneyProductivity = 0;
        return switch (honeyType) {
            case "Acacia" -> 1;
            case "Rapeseed" -> 0.8;
            case "WildFlower" -> 0.75;
            case "Linden" -> 1;
            case "SunFlower" -> 0.8;
            case "FalseIndigo" -> 0.7;
            default -> indexHoneyProductivity;
        };
    }

    public List<HoneyBatch> harvestHoney(Hive hive, HarvestingMonths month, int dayOfMonth) {
        List<HoneyBatch> honeyBatches = new ArrayList<>();
        double kgOfHoney = 0;

        if ((month.equals(HarvestingMonths.APRIL) || month.equals(HarvestingMonths.MAY) ||
                month.equals(HarvestingMonths.JUNE) || month.equals(HarvestingMonths.JULY)) &&
                (dayOfMonth == 10 || dayOfMonth == 20)) {
            if (!hive.isItWasSplit()) {
                List<HoneyFrame> hiveHoneyFrames = hive.getHoneyFrames();
                for (HoneyFrame honeyFrame : hiveHoneyFrames) {
                    if (honeyFrame.getKgOfHoney() > 4) {
                        kgOfHoney += honeyFrame.getKgOfHoney();
                        honeyFrame.setKgOfHoney(0);
                    }
                }
                hive.setKgOfHoney(kgOfHoney);
                if (kgOfHoney > 0) {
                    HoneyBatch honeyBatch = new HoneyBatch(hive.getId(), kgOfHoney, honeyType(month, dayOfMonth), false);
                    honeyBatches.add(honeyBatch);
                }
            }
        }
        return honeyBatches;
    }
}

