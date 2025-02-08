package com.marianbastiurea.lifeofbees.bees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HoneyFramesTest {



    @Test
    void findKgOfHoneyIfNumberOfEggsIs2000AndProductivityIs1() {
        EggFrames eggFrames = new EggFrames(4);
        BeesBatches beesBatches = new BeesBatches(1000);
        RandomParameters randomParameters = mock(RandomParameters.class);
        when(randomParameters.numberOfFlights()).thenReturn(2);
        double kgOfHoneyToAdd = beesBatches.makeHoney(1, eggFrames.hatchBees(2000), randomParameters.numberOfFlights());
        assertEquals(kgOfHoneyToAdd, 1.24);
    }

    @Test
    void IfNumberOfFlightIs0NoHoneyHarvested() {

        EggFrames eggFrames = new EggFrames(4);
        BeesBatches beesBatches = new BeesBatches(1000);
        RandomParameters randomParameters = mock(RandomParameters.class);
        when(randomParameters.numberOfFlights()).thenReturn(0);
        double kgOfHoneyToAdd = beesBatches.makeHoney(1, eggFrames.hatchBees(2000), randomParameters.numberOfFlights());
        assertEquals(kgOfHoneyToAdd, 0);
    }

    @Test
    void ifKgOfHoneyToAddIs5AndNumberOfHoneyFramesIs5EachHoneyFrameHave1KgMore(){
        double kgOfHoneyToAdd=5;
        HoneyFrames initialHoneyFrames=new HoneyFrames(5,3);
        initialHoneyFrames.fillUpAHoneyFrame(kgOfHoneyToAdd);
        HoneyFrames actualHoneyFrames=new HoneyFrames(5,4);
        assertEquals(initialHoneyFrames,actualHoneyFrames);

    }

    @Test
    void ifKgOfHoneyToAddIs0AndNumberOfHoneyFramesIs5EachHoneyFrameHaveSameQuantity(){
        HoneyFrames initialHoneyFrames=new HoneyFrames(5,3);
        initialHoneyFrames.fillUpAHoneyFrame(0);
        HoneyFrames actualHoneyFrames=new HoneyFrames(5,3);
        assertEquals(initialHoneyFrames,actualHoneyFrames);

    }
}
