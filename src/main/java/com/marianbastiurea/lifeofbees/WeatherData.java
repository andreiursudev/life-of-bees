package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class WeatherData {
    private double windSpeed;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;//
    private LocalDate date;// in mm

    public WeatherData(LocalDate date, double windSpeed, double temperature, double precipitation) {
        this.date=date;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
        this.precipitation = precipitation;

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
                '}';
    }

    public double weatherIndex(WeatherData weatherData) {
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
    public WeatherData getDailyWeatherDataForDate(LocalDate date, Map<String, WeatherData> allWeatherData) {
        // Formatăm data pentru a accesa `allWeatherData`
        String formattedDate = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Căutăm datele meteo pentru data respectivă
        WeatherData weatherData = allWeatherData.get(formattedDate);

        // Returnăm datele meteo găsite sau `null` dacă nu există
        return weatherData;
    }
}
