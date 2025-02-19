package com.marianbastiurea.lifeofbees.users;

import com.marianbastiurea.lifeofbees.security.RegisterRequest;
import com.marianbastiurea.lifeofbees.view.GameRequest;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername().toLowerCase().trim();

        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already exists";
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = User.createWithUsernameAndPassword(username, encodedPassword);

        try {
            User savedUser = userRepository.save(user);
            return "User registered successfully: " + savedUser.getUserId();
        } catch (DuplicateKeyException e) {
            return "Username already exists";
        }
    }



    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    public User getUser(GameRequest gameRequest) {
        User user = userRepository.findById(gameRequest.getUserId()).orElse(null);
        if (user == null) {
            user = new User();
            user.setUsername(gameRequest.getUsername());
            userRepository.save(user);
            System.out.println("Utilizator creat cu numele in createGame: " + user);
        } else {
            System.out.println("Utilizator găsit in createGame: " + user);
        }
        return user;
    }


}

