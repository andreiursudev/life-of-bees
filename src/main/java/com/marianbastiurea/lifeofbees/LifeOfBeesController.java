package com.marianbastiurea.lifeofbees;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bees")
public class LifeOfBeesController {

    @PostMapping("/create-hives")
    public List<GameResponse> createHives(@RequestBody GameRequest gameRequest) {
        LifeOfBeesGame game = new LifeOfBeesGame(gameRequest.getName(),
                gameRequest.getLocation(),
                gameRequest.getStartDate(),
                gameRequest.getNumberOfStartingHives());
        return game.createApiary(gameRequest.getNumberOfStartingHives());
    }
}

