package com.marianbastiurea.lifeofbees.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import javax.annotation.PostConstruct;
@Configuration
public class OAuth2Config {


    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        System.out.println("Creating ClientRegistrationRepository bean...");
        try {
            String clientId = System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID");
            String clientSecret = System.getProperty("SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_SECRET");
            System.out.println("Google Client ID in ClientRegistrationRepository: " + clientId);
            System.out.println("Google Client Secret in ClientRegistrationRepository: " + clientSecret);

            if (clientId == null || clientSecret == null) {
                System.out.println("Error: Client ID or Client Secret is null in ClientRegistrationRepository.");
                throw new RuntimeException("Client ID or Client Secret is missing in ClientRegistrationRepository ");
            }
            System.out.println("Building Google ClientRegistration in ClientRegistrationRepository");
            ClientRegistration googleClientRegistration = ClientRegistration.withRegistrationId("google")
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                    .scope("profile", "email")
                    .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                    .tokenUri("https://oauth2.googleapis.com/token")
                    .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .clientName("Google")
                    .build();

            System.out.println("Google ClientRegistration created successfully.");
            return new InMemoryClientRegistrationRepository(googleClientRegistration);

        } catch (Exception e) {
            System.out.println("Error creating ClientRegistrationRepository: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


    public void printOAuth2Config() {
        System.out.println("Client ID in OAuth2ClientProperties: " + oAuth2ClientProperties.getRegistration().get("google").getClientId());
        System.out.println("Client Secret in OAuth2ClientProperties: " + oAuth2ClientProperties.getRegistration().get("google").getClientSecret());
    }


}
