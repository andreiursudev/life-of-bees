package com.marianbastiurea.lifeofbees;

public class Whether {

    private double speedWind;
    private double temperature;
    private double precipitation;

    public Whether() {
    }

    public double getSpeedWind() {
        return speedWind;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }
    public double getWhetherIndex(){
        Whether whether=new Whether();
        double temperature= whether.getTemperature();
        double speedWind= whether.getSpeedWind();
        double precipitation= whether.getPrecipitation();
        double getHoneyIndex;
        if (speedWind>5||precipitation>10|| temperature<15){
            getHoneyIndex=0.75;
        } else{
            getHoneyIndex=1;
        }
        return getHoneyIndex;
    }
}