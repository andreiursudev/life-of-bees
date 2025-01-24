package com.marianbastiurea.lifeofbees.bees;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import com.marianbastiurea.lifeofbees.time.BeeTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ApiaryTest {

    @Test
    void testCreateHive() {
        Apiary apiary = new Apiary(List.of());
        List<Hive> hives = apiary.createHive(5);
        assertNotNull(hives, "HiveList is not null");
        assertEquals(5, hives.size(), "Hive List contains 5 hives");
        assertTrue(hives.stream().allMatch(hive -> hive.getId() > 0), "Each hive have to get an valid id");
    }

    @Test
    void testRandomRemoveAHive() {
        List<Hive> hives = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            hives.add(new Hive(
                    i,
                    false,
                    EggFrames.getRandomEggFrames(),
                    HoneyFrames.getRandomHoneyFrames(),
                    new LinkedList<>(),
                    new ArrayList<>(),
                    new Queen(1),
                    false
            ));
        }
        Apiary apiary = new Apiary(hives);
        Integer removedHiveId = apiary.randomRemoveAHive();
        assertNotNull(removedHiveId, "Removed hive ID should not be null.");
        assertTrue(removedHiveId > 0 && removedHiveId <= 5, "Removed hive ID should be within the valid range.");
        assertEquals(4, apiary.getHives().size(), "The number of hives should decrease by 1.");
        assertTrue(apiary.getHives().stream().noneMatch(hive -> hive.getId() == removedHiveId),
                "The removed hive should no longer exist in the list.");
    }

    @Test
    void testHarvestHoney() {
        HarvestHoney initialHarvest = new HarvestHoney(10.0, 20.0, 15.0, 5.0, 7.0, 3.0);
        Apiary apiary = new Apiary(List.of());
        apiary.updateHoneyStock(initialHarvest);
        HarvestHoney updatedHarvest = apiary.getTotalHarvestedHoney();
        assertEquals(-10.0, updatedHarvest.getHoneyAmount(HoneyType.Acacia), 0.01, "Acacia honey quantity is ok");
        assertEquals(-20.0, updatedHarvest.getHoneyAmount(HoneyType.Rapeseed), 0.01, "Rapeseed honey should be ok");
    }


    @Test
    void testSplitHive() {
        Random random = new Random();
        LinkedList<Integer> eggsByDay = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            eggsByDay.add(900);
        }
        EggFrames eggFrames = new EggFrames(6, eggsByDay);
        HoneyFrames honeyFrames = new HoneyFrames(new ArrayList<>());
        for (int k = 0; k < random.nextInt(3, 5); k++) {
            honeyFrames.getHoneyFrame().add(new HoneyFrame(random.nextDouble(2.5, 3)));
        }
        LinkedList<Integer> beesBatches = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            beesBatches.add(100);
        }
        Hive parentHive = new Hive();
        parentHive.setId(1);
        parentHive.setEggFrames(eggFrames);
        parentHive.setHoneyFrames(honeyFrames);
        parentHive.setBeesBatches(beesBatches);
        parentHive.setItWasSplit(false);
        List<Hive> hives = new ArrayList<>();
        hives.add(parentHive);
        Apiary apiary = new Apiary(hives);
        apiary.splitHive(parentHive);
        assertTrue(parentHive.isItWasSplit(), "The parent hive should be marked as split");
        assertEquals(2, apiary.getHives().size(), "A new hive should be added to the Apiary");
        Hive newHive = apiary.getHives().get(1);
        assertNotNull(newHive, "The new hive should not be null");
        assertEquals(2, newHive.getId(), "The new hive should have the correct ID");
        assertNotEquals(parentHive.getEggFrames(), newHive.getEggFrames(), "EggFrames should be split");
        assertNotEquals(parentHive.getHoneyFrames(), newHive.getHoneyFrames(), "HoneyFrames should be split");
        assertNotEquals(parentHive.getBeesBatches(), newHive.getBeesBatches(), "BeesBatches should be split");
    }

    @Test
    void testHibernate() {
        Random random = new Random();
        LinkedList<Integer> eggsByDay = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            eggsByDay.add(100);
        }
        EggFrames eggFrames = new EggFrames(6, eggsByDay);

        HoneyFrames honeyFrames = new HoneyFrames(new ArrayList<>());
        for (int k = 0; k < 3; k++) {
            honeyFrames.getHoneyFrame().add(new HoneyFrame(random.nextDouble(2.5, 3)));
        }

        List<HoneyBatch> honeyBatches1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            honeyBatches1.add(new HoneyBatch(1, i * 10.0, HoneyType.Acacia, false));
        }

        LinkedList<Integer> beesBatches = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            beesBatches.add(100);
        }
        List<HoneyBatch> honeyBatches2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            honeyBatches2.add(new HoneyBatch(1, i * 10.0, HoneyType.Acacia, false));
        }
        Hive hive1 = new Hive();
        hive1.setId(1);
        hive1.setEggFrames(eggFrames);
        hive1.setHoneyFrames(honeyFrames);
        hive1.setBeesBatches(beesBatches);
        hive1.setQueen(new Queen(3));
        hive1.setItWasSplit(false);
        hive1.setHoneyBatches(honeyBatches1);

        Hive hive2 = new Hive();
        hive2.setId(2);
        hive2.setEggFrames(eggFrames);
        hive2.setHoneyFrames(honeyFrames);
        hive2.setBeesBatches(beesBatches);
        hive2.setQueen(new Queen(3));
        hive2.setHoneyBatches(honeyBatches2);
        List<Hive> hives = new ArrayList<>();
        hives.add(hive1);
        hives.add(hive2);

        Apiary apiary = new Apiary(hives);

        Integer removedHiveId = apiary.hibernate();
        for (Hive hive : apiary.getHives()) {
            assertEquals(hive.getQueen().getAgeOfQueen(), 4, "Queen's age should increase by 1 year");
            assertFalse(hive.isItWasSplit(), "Hive should not be marked as split");
            assertFalse(hive.getEggFrames().isWasMovedAnEggsFrame(), "Egg frames should not be marked as moved");
            assertTrue(hive.getHoneyBatches().isEmpty(), "Honey batches should be cleared");
            assertEquals(eggFrames.getEggs(), 0, "Egg frames should be cleared");
            assertEquals(hive.getBeesBatches().size(), Math.max(0, 26), "Only 26 bee batches should remain after removal");
            assertEquals(hive.getHoneyFrames().getHoneyFrame().size(), 0, "Honey frames should have at most 3 remaining after removal");
        }
        assertTrue(removedHiveId == null || (removedHiveId > 0 && removedHiveId <= 2), "A hive ID should be valid or null");
        if (removedHiveId != null) {
            assertEquals(apiary.getHives().size(), 1, "One hive should be removed after hibernation");
        }
    }

    @Test
    public void testRemoveLastTwoBeesBatches() {
        LinkedList<Integer> beesBatches = new LinkedList<>();
        beesBatches.add(100);
        beesBatches.add(100);
        Hive hive = new Hive();
        hive.setId(1);
        hive.setBeesBatches(new LinkedList<>(beesBatches));
         assertEquals(2, hive.getBeesBatches().size(), "Hive should initially have 2 bees batches");
        hive.removeBeesBatches();
        assertEquals(0, hive.getBeesBatches().size(), "Hive should have 0 bees batches after removal");
    }


    @Test
    public void testHoneyHarvestedByHoneyType() {
        List<HoneyBatch> honeyBatches1 = new ArrayList<>();
        honeyBatches1.add(new HoneyBatch(1, 10.0, HoneyType.Acacia, false));
        honeyBatches1.add(new HoneyBatch(1, 15.0, HoneyType.Rapeseed, false));
        honeyBatches1.add(new HoneyBatch(1, 20.0, HoneyType.WildFlower, false));
        honeyBatches1.add(new HoneyBatch(1, 25.0, HoneyType.Linden, false));
        honeyBatches1.add(new HoneyBatch(1, 30.0, HoneyType.SunFlower, false));
        honeyBatches1.add(new HoneyBatch(1, 35.0, HoneyType.FalseIndigo, false));

        List<HoneyBatch> honeyBatches2 = new ArrayList<>();
        honeyBatches2.add(new HoneyBatch(2, 5.0, HoneyType.Acacia, false));
        honeyBatches2.add(new HoneyBatch(2, 10.0, HoneyType.Rapeseed, false));
        honeyBatches2.add(new HoneyBatch(2, 15.0, HoneyType.WildFlower, false));
        honeyBatches2.add(new HoneyBatch(2, 20.0, HoneyType.Linden, false));
        honeyBatches2.add(new HoneyBatch(2, 25.0, HoneyType.SunFlower, false));
        honeyBatches2.add(new HoneyBatch(2, 30.0, HoneyType.FalseIndigo, false));

        Hive hive1 = new Hive();
        hive1.setId(1);
        hive1.setHoneyBatches(honeyBatches1);

        Hive hive2 = new Hive();
        hive2.setId(2);
        hive2.setHoneyBatches(honeyBatches2);

        List<Hive> hives = new ArrayList<>();
        hives.add(hive1);
        hives.add(hive2);

        Apiary apiary = new Apiary(hives);

        HarvestHoney totalHarvestedHoney = new HarvestHoney();
        apiary.setTotalHarvestedHoney(totalHarvestedHoney);

        apiary.honeyHarvestedByHoneyType();
        double totalHarvestedKg = apiary.getTotalKgHoneyHarvested();
        double expectedTotal = 15.0 + 25.0 + 35.0 + 45.0 + 55.0 + 65.0;
        assertEquals(15.0, totalHarvestedHoney.getHoneyAmount(HoneyType.Acacia), "Acacia honey amount should match");
        assertEquals(25.0, totalHarvestedHoney.getHoneyAmount(HoneyType.Rapeseed), "Rapeseed honey amount should match");
        assertEquals(35.0, totalHarvestedHoney.getHoneyAmount(HoneyType.WildFlower), "WildFlower honey amount should match");
        assertEquals(45.0, totalHarvestedHoney.getHoneyAmount(HoneyType.Linden), "Linden honey amount should match");
        assertEquals(55.0, totalHarvestedHoney.getHoneyAmount(HoneyType.SunFlower), "SunFlower honey amount should match");
        assertEquals(65.0, totalHarvestedHoney.getHoneyAmount(HoneyType.FalseIndigo), "FalseIndigo honey amount should match");
        assertEquals(expectedTotal, totalHarvestedKg, "The total weight of harvested honey should match");
        hives.forEach(hive -> hive.getHoneyBatches().forEach(batch ->
                assertTrue(batch.isProcessed(), "Honey batch should be marked as processed")
        ));

    }

    @Test
    void testCheckIfCanMoveAnEggsFrame() {

        Hive sourceHive = new Hive();
        sourceHive.setId(1);

        LinkedList<Integer> eggsByDay = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            eggsByDay.add(1920);
        }
        EggFrames eggFrames = new EggFrames(6, eggsByDay);
        sourceHive.setEggFrames(eggFrames);
        sourceHive.setItWasSplit(false);
        sourceHive.getEggFrames().isFullEggFrames();
        sourceHive.getEggFrames().is80PercentFull();
        sourceHive.getEggFrames().checkIfAll6EggsFrameAre80PercentFull();

        Hive targetHive = new Hive();
        targetHive.setId(2);
        targetHive.setItWasSplit(true);
        Queen queen = new Queen();
        queen.setAgeOfQueen(0);
        targetHive.setQueen(queen);

        EggFrames eggFrames1 = new EggFrames(3, new LinkedList<>());
        targetHive.setEggFrames(eggFrames1);
        List<Hive> hives = new ArrayList<>();
        hives.add(sourceHive);
        hives.add(targetHive);

        Apiary apiary = new Apiary(hives);
        List<List<Integer>> pairs = apiary.checkIfCanMoveAnEggsFrame();

        assertNotNull(pairs);
        assertEquals(1, pairs.size());
        assertEquals(1, pairs.get(0).get(0)); // Source Hive ID
        assertEquals(2, pairs.get(0).get(1)); // Target Hive ID
    }


    @Test
    public void testMoveAnEggsFrame() {
        Hive sourceHive = new Hive();
        sourceHive.setId(1);
        LinkedList<Integer> eggsByDay = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            eggsByDay.add(120);
        }
        EggFrames sourceEggFrames = new EggFrames(6, eggsByDay);
        sourceHive.setEggFrames(sourceEggFrames);
        sourceHive.setItWasSplit(false);

        Hive destinationHive = new Hive();
        destinationHive.setId(2);
        EggFrames destinationEggFrames = new EggFrames(3, eggsByDay);
        destinationHive.setEggFrames(destinationEggFrames);
        destinationHive.setItWasSplit(true);

        List<Hive> hives = new ArrayList<>();
        hives.add(sourceHive);
        hives.add(destinationHive);

        Apiary apiary = new Apiary(hives);
        List<List<Integer>> hiveIdPair = List.of(Arrays.asList(1, 2));

        int initialSourceFrames = sourceEggFrames.getNumberOfEggFrames();
        int initialDestinationFrames = destinationEggFrames.getNumberOfEggFrames();
        int initialSourceEggs = sourceEggFrames.getEggs();
        int initialDestinationEggs = destinationEggFrames.getEggs();
        apiary.moveAnEggsFrame(hiveIdPair);

        assertEquals(initialSourceFrames - 1, sourceHive.getEggFrames().getNumberOfEggFrames());
        assertEquals(initialDestinationFrames + 1, destinationHive.getEggFrames().getNumberOfEggFrames());
        assertTrue(sourceHive.getEggFrames().isWasMovedAnEggsFrame());

        int expectedSourceEggs = initialSourceEggs - (initialSourceEggs / initialSourceFrames);
        assertEquals(expectedSourceEggs, sourceHive.getEggFrames().getEggs());

        int expectedDestinationEggs = initialDestinationEggs + (initialSourceEggs / initialSourceFrames);
        assertEquals(expectedDestinationEggs, destinationHive.getEggFrames().getEggs());
    }


    @Test
    public void testAddHivesToApiary() {
        Hive existingHive1 = new Hive();
        existingHive1.setId(1);
        Hive existingHive2 = new Hive();
        existingHive2.setId(2);

        List<Hive> existingHives = new ArrayList<>(Arrays.asList(existingHive1, existingHive2));
        Apiary apiary = new Apiary(existingHives);


        LifeOfBees lifeOfBeesGame = new LifeOfBees();
        lifeOfBeesGame.setApiary(apiary);

        Hive newHive1 = new Hive();
        Hive newHive2 = new Hive();
        List<Hive> newHives = Arrays.asList(newHive1, newHive2);
        apiary.addHivesToApiary(newHives, lifeOfBeesGame);

        List<Hive> updatedHives = lifeOfBeesGame.getApiary().getHives();
        assertEquals(4, updatedHives.size());

        List<Integer> hiveIds = updatedHives.stream().map(Hive::getId).toList();
        assertTrue(hiveIds.contains(1));
        assertTrue(hiveIds.contains(2));
        assertTrue(hiveIds.contains(3));
        assertTrue(hiveIds.contains(4));
        assertTrue(updatedHives.contains(newHive1));
        assertTrue(updatedHives.contains(newHive2));
        assertEquals(3, newHive1.getId());
        assertEquals(4, newHive2.getId());
    }

    @Test
    public void testCheckFeedBeesInSeptember() {
        Hive hive1 = new Hive();
        Hive hive2 = new Hive();
        Hive hive3 = new Hive();
        List<Hive> hives = new ArrayList<>(List.of(hive1, hive2, hive3));

        Apiary apiary = new Apiary(hives);
        BeeTime currentDate = new BeeTime();
        currentDate.setCurrentDate(LocalDate.of(2025, Month.SEPTEMBER, 15));
        Integer result = apiary.checkFeedBees(currentDate);
        assertEquals(3, result, "Hives number have to be equal with apiary size in september");
    }

    @Test
    public void testCheckFeedBeesNotInSeptember() {
        Hive hive1 = new Hive();
        Hive hive2 = new Hive();
        Hive hive3 = new Hive();
        List<Hive> hives = new ArrayList<>(List.of(hive1, hive2, hive3));
        Apiary apiary = new Apiary(hives);
        BeeTime currentDate = new BeeTime();
        currentDate.setCurrentDate(LocalDate.of(2025, Month.AUGUST, 15));
        Integer result = apiary.checkFeedBees(currentDate);

        assertEquals(0, result, "Method should return 0 when month is not September");
    }
}







