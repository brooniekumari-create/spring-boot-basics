package com.oidc.oauth.clientApp;

import java.time.Instant;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public TokenController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/token")
    public String getAccessToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
            authentication.getAuthorizedClientRegistrationId(),
            authentication.getName()
        );

        if (authorizedClient == null) {
            return "No authorized client found.";
        }

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        Instant issuedAt = authorizedClient.getAccessToken().getIssuedAt();
        Instant expiresAt = authorizedClient.getAccessToken().getExpiresAt();
        
        return "Access Token: " + accessToken + " issuedAt :" + issuedAt.toString() + " expiresAt: " + expiresAt;
    }
     @GetMapping("/refresh-token")
    public String getRefreshToken(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
            authentication.getAuthorizedClientRegistrationId(),
            authentication.getName()
        );

        if (authorizedClient == null || authorizedClient.getRefreshToken() == null) {
            return "No refresh token available.";
        }

        String refreshToken = authorizedClient.getRefreshToken().getTokenValue();
        return "Refresh Token: " + refreshToken;
    }
}
