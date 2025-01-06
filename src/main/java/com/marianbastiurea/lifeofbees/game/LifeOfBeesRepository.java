package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.game.LifeOfBees;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LifeOfBeesRepository extends MongoRepository<LifeOfBees, String> {
    Optional<LifeOfBees> findByGameId(String gameId);
}