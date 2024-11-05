package com.marianbastiurea.lifeofbees;


import java.util.*;


public class Weather implements IWeather {
    private double speedWind;// in km/h
    private double temperature;// in Celsius Degree
    private double precipitation;// in mm

    private static List<Weather> weatherList = new ArrayList<>();


    public Weather(double speedWind, double temperature, double precipitation) {
        this.speedWind = speedWind;
        this.temperature = temperature;
        this.precipitation = precipitation;

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

    public static List<Weather> getWhetherList() {
        return weatherList;
    }

    public static void setWhetherList(List<Weather> weatherList) {
        Weather.weatherList = weatherList;
    }

    public Weather() {
    }

    @Override
    public String toString() {
        return "Whether{" +
                "speedWind=" + speedWind +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                '}';
    }

    public Weather whetherToday(HarvestingMonths month, int dayOfMonth) {
        Random random = new Random();
        Weather dailyWeather = new Weather();
        switch (month) {
            case MARCH:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 4),
                            random.nextDouble(10, 12),
                            random.nextDouble(0, 40)
                    );
                    return dailyWeather;
                } else if (dayOfMonth > 15 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(0, 4),
                            random.nextDouble(9, 15),
                            random.nextDouble(0, 70)
                    );
                    return dailyWeather;
                }
                break;
            case APRIL:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 3),
                            random.nextDouble(7, 12),
                            random.nextDouble(0, 40)
                    );
                    return dailyWeather;
                } else if (dayOfMonth >= 16 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(3, 7),
                            random.nextDouble(10, 17),
                            random.nextDouble(0, 70)
                    );
                    return dailyWeather;
                }
                break;
            case MAY:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 3),
                            random.nextDouble(8, 14),
                            random.nextDouble(0, 60)
                    );
                    return dailyWeather;
                } else if (dayOfMonth >= 16 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(3, 7),
                            random.nextDouble(14, 23),
                            random.nextDouble(0, 85)
                    );
                    return dailyWeather;
                }
            case JUNE:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 3),
                            random.nextDouble(17, 22),
                            random.nextDouble(0, 50)
                    );
                    return dailyWeather;
                } else if (dayOfMonth >= 16 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(3, 7),
                            random.nextDouble(22, 27),
                            random.nextDouble(0, 80)
                    );
                    return dailyWeather;
                }
                break;
            case JULY:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 10),
                            random.nextDouble(18, 25),
                            random.nextDouble(0, 40)
                    );
                    return dailyWeather;
                } else if (dayOfMonth >= 16 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(3, 7),
                            random.nextDouble(25, 33),
                            random.nextDouble(0, 60)
                    );
                    return dailyWeather;
                }
            case AUGUST:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 3),
                            random.nextDouble(18, 32),
                            random.nextDouble(0, 10)
                    );
                    return dailyWeather;
                } else if (dayOfMonth >= 16 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(3, 7),
                            random.nextDouble(18, 28),
                            random.nextDouble(0, 30)
                    );
                    return dailyWeather;
                }
            case SEPTEMBER:
                if (dayOfMonth >= 1 && dayOfMonth <= 15) {
                    dailyWeather = new Weather(random.nextDouble(1, 3),
                            random.nextDouble(15, 27),
                            random.nextDouble(20, 40)
                    );
                    return dailyWeather;
                } else if (dayOfMonth >= 16 && dayOfMonth <= 30) {
                    dailyWeather = new Weather(random.nextDouble(3, 7),
                            random.nextDouble(10, 17),
                            random.nextDouble(0, 42)
                    );
                    return dailyWeather;
                }
            default:
                break;
        }
        return dailyWeather;
    }

    public double whetherIndex(Weather dailyWeather) {
        double rainIndex = 0;
        double temperatureIndex = 0;
        double speedWindIndex = 0;
        double whetherIndex = 0;
        // rainIndex by quantity of precipitation
        if (dailyWeather.precipitation <= 4) {
            rainIndex = 1;
        } else if (dailyWeather.precipitation > 4 && dailyWeather.precipitation <= 16) {
            rainIndex = 0.95;
        } else if (dailyWeather.precipitation > 16 && dailyWeather.precipitation <= 50) {
            rainIndex = 0.9;
        } else if (dailyWeather.precipitation > 50) {
            rainIndex = 0.7;
        }

        //temperatureIndex by Celsius Degree
        if (dailyWeather.temperature <= 10) {
            temperatureIndex = 0.8;
        } else if (dailyWeather.temperature > 10 && dailyWeather.temperature < 30) {
            temperatureIndex = 1;
        } else if (dailyWeather.temperature >= 30) {
            temperatureIndex = 0.8;
        }

        //speedWindIndex by wind speed
        if (dailyWeather.speedWind <= 10) {
            speedWindIndex = 1;
        } else if (dailyWeather.speedWind > 10 && dailyWeather.speedWind <= 20) {
            speedWindIndex = .8;
        } else if (dailyWeather.speedWind > 20 && dailyWeather.speedWind <= 30) {
            speedWindIndex = 0.75;
        } else if (dailyWeather.speedWind > 30) {
            speedWindIndex = 0.7;
        }
        whetherIndex = rainIndex * temperatureIndex * speedWindIndex;
        return whetherIndex;
    }

    public double weatherIndex(HarvestingMonths harvestingMonth, int dayOfMonth) {
        return whetherIndex(whetherToday(harvestingMonth, dayOfMonth));
    }
}