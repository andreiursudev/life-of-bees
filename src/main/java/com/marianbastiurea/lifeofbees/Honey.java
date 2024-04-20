package com.marianbastiurea.lifeofbees;

/*


 1 beehive 20-50 kg honey/year

 */

public class Honey {
    private String honeyType;

    public static String getHoneyTypes(HarvestingMonths month, int dayOfMonth) {
        String honeyType;
        switch (month) {
            case MARCH:
                return honeyType="";
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
