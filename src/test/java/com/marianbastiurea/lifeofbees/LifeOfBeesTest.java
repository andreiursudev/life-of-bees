package com.marianbastiurea.lifeofbees;

import com.marianbastiurea.lifeofbees.weather.StableWeather;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifeOfBeesTest {

    @Test
    void queen_makes_eggs_depending_on_honey_frames() {
        LifeOfBees lifeOfBees = new LifeOfBees(
                new Environment(
                        new Square(50,50, new Hive(1,0),new Queen(), new ArrayList<>()),
                        new StableWeather(4,5)
                )
        );

        LifeOfBees nextLife = lifeOfBees.iterate();

        List<EggsBatch> eggsFrames = nextLife.getEnvironment().getTerrain()[50][50].getHive().getEggs();

        assertEquals(8000, eggsFrames.get(0).getNumberOfEggs());

        nextLife = lifeOfBees.iterate();

        eggsFrames = nextLife.getEnvironment().getTerrain()[50][50].getHive().getEggs();

        assertEquals(8000, eggsFrames.get(1).getNumberOfEggs());
    }

    @Test
    void queen_makes_eggs_depending_on_hardCodedWeather() {
        LifeOfBees lifeOfBees = new LifeOfBees(
                new Environment(
                        new Square(50,50, new Hive(1,0),new Queen(), new ArrayList<>()),
                        new HardCodedWeather()
                )
        );

        LifeOfBees nextLife = lifeOfBees.iterate();

        List<EggsBatch> eggsFrames = nextLife.getEnvironment().getTerrain()[50][50].getHive().getEggs();

        assertEquals(12000, eggsFrames.get(0).getNumberOfEggs());

        nextLife = lifeOfBees.iterate();

        eggsFrames = nextLife.getEnvironment().getTerrain()[50][50].getHive().getEggs();

        assertEquals(10000, eggsFrames.get(1).getNumberOfEggs());
    }

}
