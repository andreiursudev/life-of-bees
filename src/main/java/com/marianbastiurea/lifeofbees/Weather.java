package com.marianbastiurea.lifeofbees;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Weather  {

    private Map<String,WeatherData> allWeatherData;

    public Weather(Map<String, WeatherData> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }

    public Map<String, WeatherData> getAllWeatherData() {
        return allWeatherData;
    }

    public void setAllWeatherData(Map<String, WeatherData> allWeatherData) {
        this.allWeatherData = allWeatherData;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "allWeatherData=" + allWeatherData +
                '}';
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