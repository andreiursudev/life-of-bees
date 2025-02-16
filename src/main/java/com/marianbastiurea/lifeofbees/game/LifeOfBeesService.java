package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LifeOfBeesService {

    @Autowired
    private UserService userService;

    @Autowired
    private LifeOfBeesRepository lifeOfBeesRepository;


    public Optional<LifeOfBees> getByGameId(String gameId) {
        return lifeOfBeesRepository.findByGameId(gameId);
    }

    public List<LifeOfBees> getGamesForJohnDoe(String userId) {
        return Optional.ofNullable(userId)
                .map(lifeOfBeesRepository::findByUserId)
                .orElse(Collections.emptyList());
    }


    public List<LifeOfBees> getGamesForUserByType(String userId, String gameType) {
        User user = userService.findUserByUserId(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        return lifeOfBeesRepository.findByUserIdAndGameType(userId, gameType);
    }


    public LifeOfBees save(LifeOfBees lifeOfBeesGame) {
        return lifeOfBeesRepository.save(lifeOfBeesGame);
    }

    public void deleteGameById(String gameId) {
        if (lifeOfBeesRepository.existsById(gameId)) {
            lifeOfBeesRepository.deleteById(gameId);
            System.out.println("Game with ID " + gameId + " has been deleted from game collection.");
        } else {
            throw new IllegalArgumentException("Game with ID " + gameId + " does not exist in game collections.");
        }
    }

    public boolean existsById(String gameId) {
        return lifeOfBeesRepository.existsById(gameId);
    }

}
