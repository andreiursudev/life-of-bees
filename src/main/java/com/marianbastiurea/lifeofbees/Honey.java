package com.marianbastiurea.lifeofbees;

public class Honey {
    private String honeyType;
    private double honeyKg;

    public Honey(String honeyType) {
        this.honeyType = honeyType;
    }

    public Honey(double honeyKg) {
        this.honeyKg = honeyKg;
    }

    public String getHoneyType() {
        return honeyType;
    }

    public Honey() {
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

    @Override
    public String toString() {
        return "Honey{" +
                "honeyType='" + honeyType + '\'' +
                ", honeyKg=" + honeyKg +
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

}
