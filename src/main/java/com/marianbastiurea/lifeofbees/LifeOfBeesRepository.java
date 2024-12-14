package com.marianbastiurea.lifeofbees;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifeOfBeesRepository extends MongoRepository<LifeOfBees, String> {
    LifeOfBees findByGameName(String gameName);
    List<LifeOfBees> findTop10ByIsPublicTrueOrderByCurrentDateDesc();
    List<LifeOfBees> findTop5ByUserIdOrderByCurrentDateDesc(String userId);

}