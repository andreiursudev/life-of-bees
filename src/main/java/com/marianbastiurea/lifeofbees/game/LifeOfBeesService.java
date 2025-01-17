package com.marianbastiurea.lifeofbees.game;

import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<LifeOfBees> getGamesForJohnDoe() {
        User user = userService.findUserByUsername("johndoe");
        if (user != null) {
            //TODO recomand sa cauti jocurile dupa userId si sa stergi gamesList din user.
            List<String> gamesList = user.getGamesList();
            return lifeOfBeesRepository.findAllById(gamesList);
        }
        return List.of();
    }

    public List<LifeOfBees> getGamesForUserByType(String userId, String gameType) {
        User user = userService.findUserByUserId(userId);
        if (user != null) {
            List<String> gamesList = user.getGamesList();
            if (gamesList.isEmpty()) {
                return List.of();
            }
            //TODO cauta direct dupa abele criterii userId si gameType in acelas timp
            List<LifeOfBees> userGames = lifeOfBeesRepository.findAllById(gamesList);
            return userGames.stream()
                    .filter(game -> gameType.equals(game.getGameType()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public LifeOfBees save(LifeOfBees lifeOfBeesGame) {
        return lifeOfBeesRepository.save(lifeOfBeesGame);
    }
}
