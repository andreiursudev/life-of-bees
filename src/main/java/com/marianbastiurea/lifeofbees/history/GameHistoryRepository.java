package com.marianbastiurea.lifeofbees.history;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameHistoryRepository extends MongoRepository<GameHistory, String> {
    Optional<GameHistory> findByGameId(String gameId);

}
