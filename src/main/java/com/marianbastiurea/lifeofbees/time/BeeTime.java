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

    public static boolean timeToSplitHive(BeeTime currentDate) {
        Month month = currentDate.getMonth();
        int dayOfMonth = currentDate.getDayOfMonth();
        return (month == Month.APRIL || month == Month.MAY)
                && (dayOfMonth >= 9 && dayOfMonth <= 16) ||
                (dayOfMonth >= 19 && dayOfMonth <= 25);
    }

    public static boolean timeToHarvestHive(BeeTime currentDate) {
        int dayOfMonth = currentDate.getDayOfMonth();
        return (currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 8)
                && (dayOfMonth == 10 || dayOfMonth == 20);
    }

    public static HoneyType honeyType(BeeTime currentDate) {
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

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public int getDayOfMonth() {
        return currentDate.getDayOfMonth();
    }

    public int getMonthValue() {
        return currentDate.getMonthValue();
    }

    public Month getMonth() {
        return currentDate.getMonth();
    }

    public Integer getYear() {
        return currentDate.getYear();
    }

    public boolean isEqual(BeeTime other) {
        return this.currentDate.isEqual(other.getCurrentDate());
    }

    public void updateDate(int year, int month, int day) {
        this.currentDate = LocalDate.of(year, month, day);
    }

    public void addDays(int days) {
        this.currentDate = this.currentDate.plusDays(days);
    }

    public BeeTime addingDays(long days) {
        return new BeeTime(this.currentDate.plusDays(days));
    }

    public boolean timeForInsectControl(BeeTime currentDate) {
        int dayOfMonth = currentDate.getDayOfMonth();
        return (currentDate.getMonthValue() >= 4 && currentDate.getMonthValue() <= 8)
                && (dayOfMonth >= 9 && dayOfMonth <= 16) ||
                (dayOfMonth >= 19 && dayOfMonth <= 25);
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
}
