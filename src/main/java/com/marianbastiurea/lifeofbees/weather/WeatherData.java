package com.marianbastiurea.lifeofbees.weather;

import java.time.LocalDate;

public class WeatherData {
    private double windSpeed;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;//in mm
    private LocalDate date;//

    public WeatherData(double windSpeed, double temperature, double precipitation) {
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.precipitation = precipitation;

    }

    public WeatherData() {
    }

    public WeatherData(LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public double weatherIndex(WeatherData weatherData) {
        double rainIndex;
        double temperatureIndex;
        double windSpeedIndex;
        if (weatherData.getPrecipitation() <= 4) {
            rainIndex = 1.1;
        } else if (weatherData.getPrecipitation() <= 16) {
            rainIndex = 0.95;
        } else if (weatherData.getPrecipitation() <= 50) {
            rainIndex = 0.9;
        } else {
            rainIndex = 0.7;
        }
        if (weatherData.getTemperature() <= 10) {
            temperatureIndex = 0.8;
        } else if (weatherData.getTemperature() < 30) {
            temperatureIndex = 1.2;
        } else {
            temperatureIndex = 0.8;
        }
        if (weatherData.getWindSpeed() <= 10) {
            windSpeedIndex = 1.1;
        } else if (weatherData.getWindSpeed() <= 20) {
            windSpeedIndex = 0.8;
        } else if (weatherData.getWindSpeed() <= 30) {
            windSpeedIndex = 0.75;
        } else {
            windSpeedIndex = 0.7;
        }
        System.out.println("Rain Index: " + rainIndex);
        System.out.println("Temperature Index: " + temperatureIndex);
        System.out.println("Wind Speed Index: " + windSpeedIndex);
        return rainIndex * temperatureIndex * windSpeedIndex;
    }

}
