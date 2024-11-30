package com.marianbastiurea.lifeofbees.Users;

import com.marianbastiurea.lifeofbees.Security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public String registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword()); // Trebuie criptată în viitor
        newUser.setGameIds(new ArrayList<>());

        User savedUser = userRepository.save(newUser);
        return savedUser.getId();
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        return user;
    }


    public void addGameToUser(User user, String gameId) {
        user.getGameIds().add(gameId);
        userRepository.save(user);
    }
}
