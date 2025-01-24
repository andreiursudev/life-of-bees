package com.marianbastiurea.lifeofbees.bees;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ApiaryTest {


//    @Test
//    public void testHoneyHarvestedByHoneyType() {
//        List<HoneyBatch> honeyBatches1 = new ArrayList<>();
//        honeyBatches1.add(new HoneyBatch(1, 10.0, HoneyType.Acacia, false));
//        honeyBatches1.add(new HoneyBatch(1, 15.0, HoneyType.Rapeseed, false));
//        honeyBatches1.add(new HoneyBatch(1, 20.0, HoneyType.WildFlower, false));
//        honeyBatches1.add(new HoneyBatch(1, 25.0, HoneyType.Linden, false));
//        honeyBatches1.add(new HoneyBatch(1, 30.0, HoneyType.SunFlower, false));
//        honeyBatches1.add(new HoneyBatch(1, 35.0, HoneyType.FalseIndigo, false));
//
//        List<HoneyBatch> honeyBatches2 = new ArrayList<>();
//        honeyBatches2.add(new HoneyBatch(2, 5.0, HoneyType.Acacia, false));
//        honeyBatches2.add(new HoneyBatch(2, 10.0, HoneyType.Rapeseed, false));
//        honeyBatches2.add(new HoneyBatch(2, 15.0, HoneyType.WildFlower, false));
//        honeyBatches2.add(new HoneyBatch(2, 20.0, HoneyType.Linden, false));
//        honeyBatches2.add(new HoneyBatch(2, 25.0, HoneyType.SunFlower, false));
//        honeyBatches2.add(new HoneyBatch(2, 30.0, HoneyType.FalseIndigo, false));
//
//        Hive hive1 = new Hive();
//        hive1.setId(1);
//        hive1.setHoneyBatches(honeyBatches1);
//
//        Hive hive2 = new Hive();
//        hive2.setId(2);
//        hive2.setHoneyBatches(honeyBatches2);
//
//        List<Hive> hives = new ArrayList<>();
//        hives.add(hive1);
//        hives.add(hive2);
//
//       // Apiary apiary = new Apiary(hives);
//
//        HarvestHoney totalHarvestedHoney = new HarvestHoney();
//        apiary.setTotalHarvestedHoney(totalHarvestedHoney);
//
//        apiary.honeyHarvestedByHoneyType();
//        // double totalHarvestedKg = apiary.getTotalKgHoneyHarvested();
//        double expectedTotal = 15.0 + 25.0 + 35.0 + 45.0 + 55.0 + 65.0;
//        assertEquals(15.0, totalHarvestedHoney.getHoneyAmount(HoneyType.Acacia), "Acacia honey amount should match");
//        assertEquals(25.0, totalHarvestedHoney.getHoneyAmount(HoneyType.Rapeseed), "Rapeseed honey amount should match");
//        assertEquals(35.0, totalHarvestedHoney.getHoneyAmount(HoneyType.WildFlower), "WildFlower honey amount should match");
//        assertEquals(45.0, totalHarvestedHoney.getHoneyAmount(HoneyType.Linden), "Linden honey amount should match");
//        assertEquals(55.0, totalHarvestedHoney.getHoneyAmount(HoneyType.SunFlower), "SunFlower honey amount should match");
//        assertEquals(65.0, totalHarvestedHoney.getHoneyAmount(HoneyType.FalseIndigo), "FalseIndigo honey amount should match");
//        // assertEquals(expectedTotal, totalHarvestedKg, "The total weight of harvested honey should match");
//        hives.forEach(hive -> hive.getHoneyBatches().forEach(batch ->
//                assertTrue(batch.isProcessed(), "Honey batch should be marked as processed")
//        ));
//
//    }


}







