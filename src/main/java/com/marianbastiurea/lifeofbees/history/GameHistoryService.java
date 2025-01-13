package com.marianbastiurea.lifeofbees.history;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameHistoryService {

    private final GameHistoryRepository gameHistoryRepository;

    public GameHistoryService(GameHistoryRepository gameHistoryRepository) {
        this.gameHistoryRepository = gameHistoryRepository;
    }
    public List<GameHistory> findGameHistoriesByGameId(String gameId) {
        return gameHistoryRepository.findByGameId(gameId);
    }


    public void saveGameHistory(LifeOfBees lifeOfBeesGame) {
        GameHistory gameHistory = new GameHistory(lifeOfBeesGame.getGameId(), lifeOfBeesGame);
        gameHistoryRepository.save(gameHistory);
    }
}
