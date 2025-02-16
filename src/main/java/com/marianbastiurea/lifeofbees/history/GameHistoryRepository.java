package com.marianbastiurea.lifeofbees.history;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameHistoryRepository extends MongoRepository<GameHistory, String> {
    List<GameHistory> findByGameId(String gameId);

    boolean existsByGameId(String gameId);

}
