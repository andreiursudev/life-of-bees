package com.marianbastiurea.lifeofbees;

public class Honey {
    private String honeyType;
    private double honeyKg;

    public String getHoneyType() {
        return honeyType;
    }

    public void setHoneyType(String honeyType) {
        this.honeyType = honeyType;
    }

    public double getHoneyKg() {
        return honeyKg;
    }

    public void setHoneyKg(double honeyKg) {
        this.honeyKg = honeyKg;
    }

    public static String honeyTypes(HarvestingMonths month, int dayOfMonth) {
        String honeyType;
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
    public static int honeyProductivity(String honeyType){
        int kgOnHa=1;
        switch (honeyType){
            case "Acacia":
                return kgOnHa=1600;
            case "Rapeseed":
                return  kgOnHa=50;
            case "WildFlower":
                return kgOnHa=40;
            case "Linden":
                return kgOnHa=1200;
            case "SunFlower":
                return kgOnHa=60;
            case "FalseIndigo":
                return kgOnHa=70;
        }
        return kgOnHa;
    }

}
