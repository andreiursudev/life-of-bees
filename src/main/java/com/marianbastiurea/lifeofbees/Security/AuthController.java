package com.marianbastiurea.lifeofbees.Security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.marianbastiurea.lifeofbees.Users.User;
import com.marianbastiurea.lifeofbees.Users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.google.api.client.json.gson.GsonFactory;


import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")

public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final OAuth2UserServiceHelper oAuth2UserServiceHelper;


    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncode, OAuth2UserServiceHelper oAuth2UserServiceHelper) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncode;
        this.oAuth2UserServiceHelper = oAuth2UserServiceHelper;
    }


    @GetMapping("**")
    public ResponseEntity<?> logUnexpectedEndpoints(HttpServletRequest request) {
        System.out.println("Received request for: " + request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endpoint not found");
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            String userId = userService.registerUser(registerRequest);
            System.out.println("Acesta e userId: " + userId);
            String token = jwtTokenProvider.generateToken(userId);
            return ResponseEntity.ok(Map.of("userId", userId, "token", token));
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during the registration process");
        }
    }

    @GetMapping("/google-client-id")
    public ResponseEntity<String> getGoogleClientId() {
        String googleClientId = System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID");
        return ResponseEntity.ok(googleClientId);
    }

//
//    @GetMapping("/github-client-id")
//    public ResponseEntity<String> getGitHubClientId() {
//        String githubClientId = System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_ID");
//        return ResponseEntity.ok(githubClientId);
//    }

    @GetMapping("/github-client-id")
    public ResponseEntity<Map<String, String>> getGitHubClientId() {
        String githubClientId = System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_ID");
        if (githubClientId == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "GitHub Client ID is not configured"));
        }
        return ResponseEntity.ok(Map.of("clientId", githubClientId));
    }



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.findUserByUsername(loginRequest.getUsername());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
            boolean isPasswordValid = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if (!isPasswordValid) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
            String token = jwtTokenProvider.generateToken(user.getId());
            return ResponseEntity.ok(Map.of(
                    "userId", user.getId(),
                    "username", user.getUsername(),
                    "token", token
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during the login process");
        }
    }

    @PostMapping("/oauth/google")
    public ResponseEntity<?> authenticateWithGoogle(@RequestBody Map<String, String> request) {
        System.out.println("Starting Google OAuth authentication process.");
        String idToken = request.get("token");

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID")))
                    .build();
            String email;
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                email = payload.getEmail();
                String name = (String) payload.get("name");
            } else {

                System.out.println("OAuth2User principal is null. Returning unauthorized response.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Principal is null");
            }

            if (email == null ) {
                System.out.println("Email is missing. Returning bad request response.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email or providerId is missing");
            }
            User user = oAuth2UserServiceHelper.processOAuthPostLogin(email, "GOOGLE");
            System.out.println("User processed successfully: " + user);
            String token = jwtTokenProvider.generateToken(user.getId());
            System.out.println("Generated JWT token: " + token);
            System.out.println("Returning success response with user details.");
            return ResponseEntity.ok(Map.of(
                    "userId", user.getId(),
                    "email", user.getEmail(),
                    "token", token
            ));
        } catch (Exception e) {
            System.out.println("An error occurred during Google OAuth authentication: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during Google OAuth authentication");
        }
    }

    @PostMapping("/oauth/github")
    public ResponseEntity<?> authenticateWithGitHub(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("Starting GitHub OAuth authentication process.");

        try {
            if (principal == null) {
                System.out.println("OAuth2User principal is null. Returning unauthorized response.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Principal is null");
            }
            Map<String, Object> attributes = principal.getAttributes();
            System.out.println("OAuth2User attributes: " + attributes);
            String email = (String) attributes.get("email");
            String providerId = (String) attributes.get("id"); // GitHub folosește "id" pentru identificatorul unic
            System.out.println("Extracted email: " + email + ", providerId: " + providerId);
            if (email == null || providerId == null) {
                System.out.println("Email or providerId is missing. Returning bad request response.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email or providerId is missing");
            }
            User user = oAuth2UserServiceHelper.processOAuthPostLogin(email, providerId);
            System.out.println("User processed successfully: " + user);
            String token = jwtTokenProvider.generateToken(user.getId());
            System.out.println("Generated JWT token: " + token);
            return ResponseEntity.ok(Map.of(
                    "userId", user.getId(),
                    "email", user.getEmail(),
                    "token", token
            ));
        } catch (Exception e) {
            System.out.println("An error occurred during GitHub OAuth authentication: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during GitHub OAuth authentication");
        }
    }

//
//    @GetMapping("/github/callback")
//    public ResponseEntity<String> handleGitHubCallback(@RequestParam("code") String code) {
//        try {
//            System.out.println("Received GitHub authorization code: " + code);
//            String accessTokenUrl = "https://github.com/login/oauth/access_token";
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Accept", "application/json");
//
//            Map<String, String> body = Map.of(
//                    "client_id", System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_ID"),
//                    "client_secret", System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_SECRET"),
//                    "code", code
//            );
//
//            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
//            ResponseEntity<Map> response = restTemplate.postForEntity(accessTokenUrl, request, Map.class);
//
//            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
//                String accessToken = (String) response.getBody().get("access_token");
//                System.out.println("GitHub access token: " + accessToken);
//                return ResponseEntity.ok("Access token: " + accessToken);
//            } else {
//                System.err.println("Failed to retrieve access token. Status code: " + response.getStatusCode());
//                if (response.getBody() != null) {
//                    System.err.println("Response body: " + response.getBody());
//                } else {
//                    System.err.println("Response body is null.");
//                }
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to retrieve access token");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error handling GitHub callback: " + e.getMessage());
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An error occurred during GitHub callback handling");
//        }
//    }


}
