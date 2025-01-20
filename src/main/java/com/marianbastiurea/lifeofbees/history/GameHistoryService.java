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

    public void deleteGameById(String gameId) {
        List<GameHistory> gameHistories = gameHistoryRepository.findByGameId(gameId);
        System.out.println("acesta e GamesHistory for gameid: "+gameId+"  "+ gameHistories);

        if (!gameHistories.isEmpty()) {
            gameHistoryRepository.deleteAll(gameHistories);
            System.out.println("Game with ID " + gameId + " has been deleted from history.");
        } else {
            throw new IllegalArgumentException("Game with ID " + gameId + " does not exist in history.");
        }
    }

    public boolean existsByGameId(String gameId) {
        return gameHistoryRepository.existsByGameId(gameId);
    }


}
