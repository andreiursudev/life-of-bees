package com.marianbastiurea.lifeofbees;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifeOfBeesTest {

    @Test
    void queen_makes_eggs_depending_on_honey_frames() {
        LifeOfBees lifeOfBees = new LifeOfBees(new Environment(new Square(50,50, new Hive(1,0),new Queen(), new ArrayList<>())));

        LifeOfBees nextLife = lifeOfBees.iterate();

        double eggsFrames = nextLife.getEnvironment().getTerrain()[50][50].getHive().getHoneyFrames();

        assertEquals(1, eggsFrames);
    }
}
