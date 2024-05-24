package com.marianbastiurea.lifeofbees;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.json.JSONObject;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Whether {
    private double speedWind;
    private double temperature;
    private double precipitation;
    private Date date;

    public Whether(double speedWind, double temperature, double precipitation, Date date) {
        this.speedWind = speedWind;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.date = date;
    }

//    public static void main(String[] args) {
//        // Define the start and end dates
//        Calendar startDate = Calendar.getInstance();
//        startDate.set(2023, Calendar.APRIL, 1);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2023, Calendar.SEPTEMBER, 30);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
//        Calendar currentDate = (Calendar) startDate.clone();
//
//        while (currentDate.before(endDate) || currentDate.equals(endDate)) {
//            String dateString = sdf.format(currentDate.getTime());
//            // Call API to get weather data for Galati, Romania for the current date
//          //  Whether weather = getWeatherDataFromAPI(dateString);
//
//            // Use weather object as needed
//            System.out.println("Date: " + sdf.format(weather.date));
//            System.out.println("Speed of Wind: " + weather.speedWind);
//            System.out.println("Temperature: " + weather.temperature);
//            System.out.println("Precipitation: " + weather.precipitation);
//
//            // Move to the next day
//            currentDate.add(Calendar.DATE, 1);
//        }
//    }
//
//    // Method to call OpenWeatherMap API and return weather data
////    private static Whether getWeatherDataFromAPI(String date) {
////        // Replace "YOUR_API_KEY" with your actual OpenWeatherMap API key
////        String apiKey = "YOUR_API_KEY";
////        String city = "Galati";
////        String country = "RO";
////        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&appid=" + apiKey;
////
////        double speedWind = 0;
////        double temperature = 0;
////        double precipitation = 0;
////        Date parsedDate = null;
////
//////        try {
////            URL url = new URL(urlString);
////            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////            conn.setRequestMethod("GET");
////            conn.setRequestProperty("Accept", "application/json");
////
////            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
////            String output;
////            StringBuilder response = new StringBuilder();
////            while ((output = br.readLine()) != null) {
////                response.append(output);
////            }
////
//////            JSONObject jsonResponse = new JSONObject(response.toString());
////
////            // Extract wind speed, temperature, and precipitation from the API response
////            JSONObject windObject = jsonResponse.getJSONObject("wind");
////            speedWind = windObject.getDouble("speed");
////
////            JSONObject mainObject = jsonResponse.getJSONObject("main");
////            temperature = mainObject.getDouble("temp");
////
////            // Assuming the API doesn't provide precipitation data, so setting it to 0
////            // If the API provides precipitation data, you can extract it similarly
////            precipitation = 0;
////
////            // Assuming date format is dd-MM
////            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
////            parsedDate = sdf.parse(date);
////
////            conn.disconnect();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        return new Whether(speedWind, temperature, precipitation, parsedDate);
//    }
}