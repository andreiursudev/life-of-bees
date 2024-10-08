package com.marianbastiurea.lifeofbees;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bees")
public class LifeOfBeesController {

    private LifeOfBeesGame game;


    @PostMapping("/create-hives")
    public List<GameResponse> createHives(@RequestBody GameRequest gameRequest) {
        game = new LifeOfBeesGame(gameRequest.getName(),
                gameRequest.getLocation(),
                gameRequest.getStartDate(),
                gameRequest.getNumberOfStartingHives());

        return game.createApiary(gameRequest.getNumberOfStartingHives());
    }


    @GetMapping("/hives")
    public List<GameResponse> getHives() {
        if (game != null) {
            return game.createApiary(game.getNumberOfStartingHives());
        } else {
            throw new IllegalStateException("No game has been created yet.");
        }
    }
}
