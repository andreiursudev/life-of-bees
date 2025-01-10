package com.marianbastiurea.lifeofbees.history;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;


@Service
public class GameHistoryService {

    private final GameHistoryRepository gameHistoryRepository;

    public GameHistoryService(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }

    public GameHistory findGameByGameId(String gameId) {
        return gameHistoryRepository.findByGameId(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    public void saveGameHistory(LifeOfBees savedGame) {
        GameHistory gameHistory = new GameHistory();
        gameHistory.setGameId(savedGame.getGameId());
        gameHistory.setGamesHistory(new ArrayList<>());
        gameHistory.getGamesHistory().add(savedGame);
        gameHistoryRepository.save(gameHistory);
    }

    public void addGameInGameHistory(LifeOfBees lifeOfBeesGame) {
        GameHistory gameHistory = gameHistoryRepository.findByGameId(lifeOfBeesGame.getGameId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game history not found"));
        gameHistory.getGamesHistory().add(lifeOfBeesGame);
        gameHistoryRepository.save(gameHistory);
    }
}
