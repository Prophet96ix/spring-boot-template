package de.aaronwagner.demo.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import de.aaronwagner.demo.config.security.rest.AuthRequest;
import de.aaronwagner.demo.config.security.rest.AuthResponse;
import de.aaronwagner.demo.exceptions.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("${security.jwt.token.expire-length}")
    private long validityMs;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private final CustomAuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthResponse login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.email(),
                            authRequest.password()
                    )
            );
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException();
        }

        return new AuthResponse(
                createToken(
                        authRequest.email()
                )
        );
    }

    public String createToken(String email) throws JWTCreationException {
        Instant now = Instant.now();
        return JWT.create()
                .withClaim("email", email)
                .withIssuer("auth0")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(Duration.ofMillis(validityMs))))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication getAuthentication(String token) {
        String email = JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer("auth0")
                .build()
                .verify(token)
                .getClaim("email")
                .asString();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities()
        );
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
