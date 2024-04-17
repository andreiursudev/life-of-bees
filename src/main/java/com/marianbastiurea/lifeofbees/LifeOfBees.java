package com.marianbastiurea.lifeofbees;

public class LifeOfBees {

    private final Environment environment;

    public LifeOfBees(Environment environment) {
        this.environment = environment;
    }

    public LifeOfBees iterate() {
        for (Square[] columns : environment.getTerrain()) {
            for (Square square : columns) {
                if (square != null) {
                    Queen queen = square.getQueen();
                    if (queen != null) {
                        double eggs = queen.makeEggs(square.getHive(), environment);
                        square.getHive().addEggFrames(eggs);
                    }
                }
            }
        }

        return new LifeOfBees(environment);
    }

    public Environment getEnvironment() {
        return environment;
    }
}