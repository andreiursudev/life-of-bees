package com.marianbastiurea.lifeofbees.Security;

import com.marianbastiurea.lifeofbees.Users.User;
import com.marianbastiurea.lifeofbees.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")

public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            String userId = userService.registerUser(registerRequest);
            System.out.println("Acesta e userId: "+userId);
            String token = jwtTokenProvider.generateToken(userId);
            return ResponseEntity.ok(Map.of("userId", userId, "token", token));
        } catch (IllegalArgumentException e) {
            // Logare eroare de validare
            System.err.println("Validation error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Logare eroare generală
            e.printStackTrace();  // Sau logare detaliată a erorii
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during the registration process");
        }
    }




}
