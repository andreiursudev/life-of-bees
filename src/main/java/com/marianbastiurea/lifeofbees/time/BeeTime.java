package com.marianbastiurea.lifeofbees.time;

import com.marianbastiurea.lifeofbees.bees.HoneyType;

import java.time.LocalDate;
import java.time.Month;


public class BeeTime {

    private LocalDate currentDate;

    public BeeTime(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public BeeTime(int year, int month, int day) {
        this.currentDate = LocalDate.of(year, month, day);
    }

    public BeeTime() {
    }


    public BeeTime(String date) {
        this.currentDate = LocalDate.parse(date);
    }

    public boolean timeToSplitHive() {
        Month month = currentDate.getMonth();
        int dayOfMonth = currentDate.getDayOfMonth();
        return (month == Month.APRIL || month == Month.MAY)
                && (dayOfMonth >= 9 && dayOfMonth <= 16) ||
                (dayOfMonth >= 19 && dayOfMonth <= 25);
    }

    public boolean timeToHarvestHive() {
        int dayOfMonth = currentDate.getDayOfMonth();
        return (currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 8)
                && (dayOfMonth == 10 || dayOfMonth == 20);
    }

    public HoneyType honeyType( ) {
        Month month = currentDate.getMonth();
        int dayOfMonth = currentDate.getDayOfMonth();
        return switch (month) {
            case MARCH, AUGUST, SEPTEMBER -> HoneyType.WildFlower;
            case APRIL -> dayOfMonth <= 20
                    ? HoneyType.Rapeseed
                    : HoneyType.WildFlower;
            case MAY -> dayOfMonth <= 20
                    ? HoneyType.Acacia
                    : HoneyType.FalseIndigo;
            case JUNE -> dayOfMonth <= 20
                    ? HoneyType.Linden
                    : HoneyType.WildFlower;
            case JULY -> HoneyType.SunFlower;
            default -> HoneyType.WildFlower;
        };
    }

    public LocalDate getLocalDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public int getDayOfMonth() {
        return currentDate.getDayOfMonth();
    }

    public Month getMonth() {
        return currentDate.getMonth();
    }

    public Integer getYear() {
        return currentDate.getYear();
    }

    public void addDay() {
        this.currentDate = this.currentDate.plusDays(1);
    }

    public BeeTime addingDays(long days) {
        return new BeeTime(this.currentDate.plusDays(days));
    }

    public boolean timeForInsectControl() {
        int dayOfMonth = currentDate.getDayOfMonth();
        return (currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 8)
                && (dayOfMonth >= 9 && dayOfMonth <= 16) ||
                (dayOfMonth >= 19 && dayOfMonth <= 25);
    }

    public boolean isEndOfSeason() {
        return currentDate.isEqual(LocalDate.of(currentDate.getYear(), 9, 30));
    }

    public String toFormattedDate() {
        return currentDate.toString(); // Format implicit ISO-8601: YYYY-MM-DD
    }

    @Override
    public String toString() {
        return "{" +
                "currentDate=" + currentDate +
                '}';
    }

    public void changeYear() {
        currentDate = currentDate.plusYears(1).withMonth(3).withDayOfMonth(1);
    }
}
