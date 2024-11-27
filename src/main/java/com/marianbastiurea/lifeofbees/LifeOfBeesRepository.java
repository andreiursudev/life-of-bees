package com.marianbastiurea.lifeofbees;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeOfBeesRepository extends MongoRepository<LifeOfBees, String> {
    LifeOfBees findByGameName(String gameName);
}