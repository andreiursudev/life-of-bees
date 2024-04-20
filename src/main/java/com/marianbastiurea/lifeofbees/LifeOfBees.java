package com.marianbastiurea.lifeofbees;

import java.util.List;

public class LifeOfBees {

    private final Environment environment;
    private Queen queen;
    private List<Bee> bees;
    private Hive hive;

    public Queen getQueen() {
        return queen;
    }

    public List<Bee> getBees() {
        return bees;
    }

    public Hive getHive() {
        return hive;
    }

    public LifeOfBees(Environment environment) {
        this.environment = environment;
    }

    public LifeOfBees iterate() {
        for (Square[] columns : environment.getTerrain()) {
            for (Square square : columns) {
                if (square != null) {
                    Queen queen = getQueen();
                    if (queen != null) {
                        queen.makeEggs(getHive(), environment);
                    }
                    Hive hive = getHive();
                    if (hive != null) {
                        for (EggsBatch eggsBatch : hive.getEggs()) {
                            //eggsBatch does soemthing
                        }

                    }
                    //beekeeper does action
                }
            }
        }

        return new LifeOfBees(environment);
    }

    public Environment getEnvironment() {
        return environment;
    }
}