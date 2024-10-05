package com.marianbastiurea.lifeofbees;


import com.marianbastiurea.lifeofbees.eggframe.EggFrame;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class LifeOfBeesController {

    @PostMapping("/startGame")
    public List<GameResponse> startGame(@RequestBody GameRequest gameRequest) {
        int numberOfStartingHives = Integer.parseInt(gameRequest.getHives());
        List<Hive> hives = new ArrayList<>();
        Random random = new Random();

        Apiary apiary = new Apiary(hives, new ArrayList<>());
        for (int i = 1; i <= numberOfStartingHives; i++) {
            int ageOfQueen = random.nextInt(1, 6);
            List<EggFrame> eggFrames = new ArrayList<>();
            for (int j = 0; j < random.nextInt(5, 6); j++) {
                eggFrames.add(new EggFrame());
            }
            List<HoneyFrame> honeyFrames = new ArrayList<>();
            for (int k = 0; k < random.nextInt(4, 5); k++) {
                honeyFrames.add(new HoneyFrame(random.nextDouble(2.5, 3), "WildFlower"));
            }
            int numberOfBees = random.nextInt(2000, 2500) * (honeyFrames.size() + eggFrames.size());
            Hive hive = new Hive(apiary, i, false, false, false, eggFrames, honeyFrames, new ArrayList<>(), new ArrayList<>(), new Honey("WildFlower"), new Queen(ageOfQueen), numberOfBees);
            hives.add(hive);
        }

        IWeather weather = new Whether();
        LifeOfBees lifeOfBees = new LifeOfBees(apiary);
        lifeOfBees.iterateOverTwoYears(weather);


        List<GameResponse> hiveResponses = new ArrayList<>();
        for (Hive hive : hives) {
            GameResponse response = new GameResponse();
            response.setHiveId(hive.getId());
            response.setAgeOfQueen(hive.getQueen().getAgeOfQueen());
            response.setNumberOfBees(hive.getNumberOfBees());
            response.setEggsFrameSize(hive.getEggsFrames().size());
            response.setCurrentDate();
            hiveResponses.add(response);

        }

        return hiveResponses;
    }
}
