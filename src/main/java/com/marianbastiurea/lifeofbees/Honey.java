package com.marianbastiurea.lifeofbees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Honey {
    private String honeyType;
    private List<Hive> hives;


//    public Honey(String honeyType) {
//        this.honeyType = honeyType;
//    }

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
               // ", honeyKg=" + honeyKg +
                '}';
    }

    public String honeyTypes(HarvestingMonths month, int dayOfMonth) {
        switch (month) {
            case APRIL:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return honeyType="Rapeseed";
                } else if (dayOfMonth >= 21 && dayOfMonth <= 30) {
                    return honeyType="WildFlower";
                }
                break;
            case MAY:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return honeyType="Acacia";
                }else if (dayOfMonth >= 21 && dayOfMonth <= 31) {
                    return honeyType="FalseIndigo";
                }
                break;
            case JUNE:
                if (dayOfMonth >= 1 && dayOfMonth <= 20) {
                    return honeyType="Linden";
                }else if (dayOfMonth >= 21 && dayOfMonth <= 30) {
                    return honeyType="WildFlower";
                }
                break;
            case JULY:
                return honeyType="SunFlower";
            case AUGUST:
                return honeyType="WildFlower";
            default:
                break;
        }
        return honeyType=""; // Default value if month and day combination doesn't match any condition
    }

    public double honeyProductivity( HarvestingMonths month, int dayOfMonth){
        String honeyType=honeyTypes(month, dayOfMonth);
        int indexHoneyProductivity=0;
        switch (honeyType){
            case "Acacia":
                return 1 ;//kgOnHa=1600
            case "Rapeseed":
                return  0.8; //kgOnHa=50
            case "WildFlower":
                return 0.5 ;//kgOnHa=40
            case "Linden":
                return 1;//kgOnHa=1200
            case "SunFlower":
                return 0.7;//kgOnHa=60
            case "FalseIndigo":
                return 0.5;//kgOnHa=70
        }
        return indexHoneyProductivity;
    }

    public List<HoneyBatch> makeHoneyBatch(Hive hive,Date currentDate) {
        List<HoneyBatch> honeyBatches = new ArrayList<>();

            double totalKgOfHoneyPerHive = 0;
            int frameCounter = 0;

            if (hive.isItWasSplit()&& hive.checkIfAll6EggsFrameAreFull()) {
                List<HoneyFrame> hiveHoneyFrames = hive.getHoneyFrames();
                for (HoneyFrame honeyFrame : hiveHoneyFrames) {
                    if (honeyFrame.getKgOfHoney() > 3) {
                        frameCounter++;
                        totalKgOfHoneyPerHive += honeyFrame.getKgOfHoney();
                        honeyFrame.setKgOfHoney(0);
                    }
                }

            if (totalKgOfHoneyPerHive > 0) {
                HoneyBatch honeyBatch = new HoneyBatch(hive.getId(), currentDate, totalKgOfHoneyPerHive,
                        getHoneyType(), frameCounter);
                honeyBatches.add(honeyBatch);
                System.out.println("Honey Batch are:" + honeyBatch);
            }
        }
            return honeyBatches;
    }
}
