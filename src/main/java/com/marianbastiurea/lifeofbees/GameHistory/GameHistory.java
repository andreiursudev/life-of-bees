package com.marianbastiurea.lifeofbees.GameHistory;

import com.marianbastiurea.lifeofbees.Apiary;
import com.marianbastiurea.lifeofbees.LifeOfBees;
import com.marianbastiurea.lifeofbees.WeatherData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "gameHistory")
public class GameHistory {

    @Id
    private String  gameHistoryId;
    private String gameId;
    private List<LifeOfBees> gamesHistory;

    public GameHistory() {
    }

    public GameHistory(String gameHistoryId, String gameId, List<LifeOfBees> gamesHistory) {
        this.gameHistoryId = gameHistoryId;
        this.gameId = gameId;
        this.gamesHistory = gamesHistory;
    }


    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<LifeOfBees> getGamesHistory() {
        return gamesHistory;
    }

    public void setGamesHistory(List<LifeOfBees> gamesHistory) {
        this.gamesHistory = gamesHistory;
    }

    @Override
    public String toString() {
        return "GameHistory{" +
                "gameHistoryId='" + gameHistoryId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", gamesHistory=" + gamesHistory +
                '}';
    }
}