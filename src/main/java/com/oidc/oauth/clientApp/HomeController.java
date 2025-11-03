package com.oidc.oauth.clientApp;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home(OAuth2AuthenticationToken authentication) {

        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
        return ResponseEntity.status(HttpStatus.OK).body("Hello " + authentication.getPrincipal().toString());
    }
}
