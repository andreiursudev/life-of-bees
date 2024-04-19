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
                        queen.makeEggs(square.getHive(), environment);
                    }
                    Hive hive = square.getHive();
                    if(hive!=null){
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