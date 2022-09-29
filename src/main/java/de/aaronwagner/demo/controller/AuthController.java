package de.aaronwagner.demo.controller;

import de.aaronwagner.demo.config.security.JwtTokenProvider;
import de.aaronwagner.demo.config.security.rest.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "auth/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity.ok(
                jwtTokenProvider.login(authRequest)
        );
    }

}
