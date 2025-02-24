package com.marianbastiurea.lifeofbees.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.marianbastiurea.lifeofbees.users.User;
import com.marianbastiurea.lifeofbees.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
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
            System.out.println("This is UserId from method /register: " + userId);
            String token = jwtTokenProvider.generateToken(userId);
            return ResponseEntity.ok(Map.of("userId", userId, "token", token));
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage())); // Trimite mesaj JSON clar
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An error occurred during the registration process"));
        }
    }


    @GetMapping("/google-client-id")
    public ResponseEntity<String> getGoogleClientId() {
        String googleClientId = System.getenv("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID");
        return ResponseEntity.ok(googleClientId);
    }

    @GetMapping("/github-client-id")
    public ResponseEntity<Map<String, String>> getGitHubClientId() {
        String githubClientId = System.getenv("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_ID");
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
            String token = jwtTokenProvider.generateToken(user.getUserId());
            return ResponseEntity.ok(Map.of(
                    "userId", user.getUserId(),
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
                    .setAudience(Collections.singletonList(System.getenv("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID")))
                    .build();
            String email;
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                email = payload.getEmail();
                String username = email.split("@")[0];

                User user = oAuth2UserServiceHelper.processOAuthPostLogin(email, "GOOGLE");
                System.out.println("User processed successfully: " + user);
                String token = jwtTokenProvider.generateToken(user.getUserId());
                System.out.println("Generated JWT token: " + token);
                System.out.println("Returning success response with user details.");
                return ResponseEntity.ok(Map.of(
                        "userId", user.getUserId(),
                        "username", username,
                        "token", token
                ));
            } else {
                System.out.println("OAuth2User principal is null. Returning unauthorized response.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Principal is null");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during Google OAuth authentication: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during Google OAuth authentication");
        }
    }


//    @PostMapping("/oauth/github")
//    public ResponseEntity<?> authenticateWithGitHub(@AuthenticationPrincipal OAuth2User principal) {
//        System.out.println("Starting GitHub OAuth authentication process.");
//
//        try {
//            if (principal == null) {
//                System.out.println("OAuth2User principal is null. Returning unauthorized response.");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Principal is null");
//            }
//            Map<String, Object> attributes = principal.getAttributes();
//            System.out.println("OAuth2User attributes: " + attributes);
//            String email = (String) attributes.get("email");
//            String providerId = (String) attributes.get("id");
//            System.out.println("Extracted email: " + email + ", providerId: " + providerId);
//            if (email == null || providerId == null) {
//                System.out.println("Email or providerId is missing. Returning bad request response.");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body("Email or providerId is missing");
//            }
//            User user = oAuth2UserServiceHelper.processOAuthPostLogin(email, providerId);
//            System.out.println("User processed successfully: " + user);
//            String token = jwtTokenProvider.generateToken(user.getUserId());
//            System.out.println("Generated JWT token: " + token);
//            return ResponseEntity.ok(Map.of(
//                    "userId", user.getUserId(),
//                    "email", user.getEmail(),
//                    "token", token
//            ));
//        } catch (Exception e) {
//            System.out.println("An error occurred during GitHub OAuth authentication: " + e.getMessage());
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An error occurred during GitHub OAuth authentication");
//        }
//    }

    @PostMapping("/oauth/github")
    public ResponseEntity<?> authenticateWithGitHub(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        if (code == null) {
            return ResponseEntity.badRequest().body("Authorization code is missing");
        }

        String clientId = System.getenv("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_ID");
        String clientSecret = System.getenv("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GITHUB_CLIENT_SECRET");

        String tokenUrl = "https://github.com/login/oauth/access_token";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, String> body = new HashMap<>();
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("code", code);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, requestEntity, Map.class);
        Map<String, Object> responseBody = response.getBody();

        if (responseBody == null || !responseBody.containsKey("access_token")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to retrieve access token");
        }

        String accessToken = (String) responseBody.get("access_token");

        // Folosim token-ul pentru a obține detalii despre utilizator
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>("", authHeaders);
        ResponseEntity<Map> userResponse = restTemplate.exchange(
                "https://api.github.com/user", HttpMethod.GET, entity, Map.class
        );

        Map<String, Object> userDetails = userResponse.getBody();
        if (userDetails == null || !userDetails.containsKey("id")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to retrieve user details");
        }

        String githubId = String.valueOf(userDetails.get("id"));
        String email = (String) userDetails.get("email");
        String username = (String) userDetails.get("login");

        User user = oAuth2UserServiceHelper.processOAuthPostLogin(email, githubId);

        String token = jwtTokenProvider.generateToken(user.getUserId());

        return ResponseEntity.ok(Map.of(
                "userId", user.getUserId(),
                "username", username,
                "token", token
        ));
    }


}
