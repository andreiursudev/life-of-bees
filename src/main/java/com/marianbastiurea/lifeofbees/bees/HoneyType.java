package com.marianbastiurea.lifeofbees.bees;

public enum HoneyType {
    Acacia(1),
    Rapeseed(0.8),
    WildFlower(0.75),
    Linden(1),
    SunFlower(0.8),
    FalseIndigo(0.7);

    private final double productivity;

    HoneyType(double productivity) {
        this.productivity = productivity;
    }

    public double getProductivity() {
        return productivity;
    }
}
