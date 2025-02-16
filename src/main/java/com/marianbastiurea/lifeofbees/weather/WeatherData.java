package com.marianbastiurea.lifeofbees.weather;

import com.marianbastiurea.lifeofbees.time.BeeTime;

public class WeatherData {
    private double windSpeed;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;//in mm
    private BeeTime date;//

    public WeatherData(double windSpeed, double temperature, double precipitation) {
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.precipitation = precipitation;

    }

    public WeatherData() {
    }

    public WeatherData(BeeTime date) {
        this.date = date;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public BeeTime getDate() {
        return date;
    }

    public void setDate(BeeTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "windSpeed=" + windSpeed +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                ", date=" + date +
                '}';
    }

    public double weatherIndex() {
        double rainIndex;
        double temperatureIndex;
        double windSpeedIndex;
        if (getPrecipitation() <= 4) {
            rainIndex = 1.1;
        } else if (getPrecipitation() <= 16) {
            rainIndex = 0.95;
        } else if (getPrecipitation() <= 50) {
            rainIndex = 0.9;
        } else {
            rainIndex = 0.7;
        }
        if (getTemperature() <= 10) {
            temperatureIndex = 0.8;
        } else if (getTemperature() < 30) {
            temperatureIndex = 1.2;
        } else {
            temperatureIndex = 0.8;
        }
        if (getWindSpeed() <= 10) {
            windSpeedIndex = 1.1;
        } else if (getWindSpeed() <= 20) {
            windSpeedIndex = 0.8;
        } else if (getWindSpeed() <= 30) {
            windSpeedIndex = 0.75;
        } else {
            windSpeedIndex = 0.7;
        }
        return rainIndex * temperatureIndex * windSpeedIndex;
    }

}
