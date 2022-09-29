package de.aaronwagner.demo.config.security.rest;

public record AuthRequest(
        String email,
        String password
) {

}
