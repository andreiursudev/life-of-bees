package com.marianbastiurea.lifeofbees;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Weather  {
    private double windSpeed;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;// in mm
    private Map<String,Object> allWeatherData;

    public Weather(Map<String, Object> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }

    public Map<String, Object> getAllWeatherData() {
        return allWeatherData;
    }

    public void setAllWeatherData(Map<String, Object> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }

    public Weather(double windSpeed, double temperature, double precipitation) {
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.precipitation = precipitation;

    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public Weather() {
    }

    @Override
    public String toString() {
        return "Whether{" +
                "windSpeed=" + windSpeed +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                '}';
    }

    public double weatherIndex(Weather weatherData) {
        double rainIndex = 0;
        double temperatureIndex = 0;
        double speedWindIndex = 0;
        double whetherIndex = 0;
        if (weatherData.getPrecipitation() <= 4) {
            rainIndex = 1;
        } else if (weatherData.getPrecipitation() > 4 && weatherData.getPrecipitation() <= 16) {
            rainIndex = 0.95;
        } else if (weatherData.getPrecipitation() > 16 && weatherData.getPrecipitation() <= 50) {
            rainIndex = 0.9;
        } else if (weatherData.getPrecipitation() > 50) {
            rainIndex = 0.7;
        }
        if (weatherData.getTemperature() <= 10) {
            temperatureIndex = 0.8;
        } else if (weatherData.getPrecipitation() > 10 && weatherData.getTemperature() < 30) {
            temperatureIndex = 1;
        } else if (weatherData.getTemperature() >= 30) {
            temperatureIndex = 0.8;
        }
        if (weatherData.getWindSpeed() <= 10) {
            speedWindIndex = 1;
        } else if (weatherData.getWindSpeed() > 10 && weatherData.getWindSpeed() <= 20) {
            speedWindIndex = .8;
        } else if (weatherData.getWindSpeed() > 20 && weatherData.getWindSpeed() <= 30) {
            speedWindIndex = 0.75;
        } else if (weatherData.getWindSpeed() > 30) {
            speedWindIndex = 0.7;
        }
        whetherIndex = rainIndex * temperatureIndex * speedWindIndex;
        return whetherIndex;
    }

    public Weather getDailyWeatherDataForDate(LocalDate date, Map<String, Object> allWeatherData) {
        // Formatăm data pentru a accesa `weeklyWeatherData` dacă cheile sunt String-uri
        String formattedDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Căutăm datele pentru data specificată în `weeklyWeatherData`
        if (allWeatherData != null && allWeatherData.containsKey(formattedDate)) {
            Object dailyDataObj = allWeatherData.get(formattedDate);

            if (dailyDataObj instanceof Map) {
                Map<String, Object> dailyData = (Map<String, Object>) dailyDataObj;

                // Extragem valorile pentru windSpeed, temperature și precipitation
                double windSpeed = (double) dailyData.getOrDefault("windSpeed", 0.0);
                double temperature = (double) dailyData.getOrDefault("temperature", 0.0);
                double precipitation = (double) dailyData.getOrDefault("precipitation", 0.0);

                // Returnăm un nou obiect Weather pentru data specificată
                return new Weather(windSpeed, temperature, precipitation);
            }
        }
        // Returnăm null sau un obiect Weather implicit dacă data nu este găsită
        return null;
    }




}