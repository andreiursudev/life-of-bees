package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
}
