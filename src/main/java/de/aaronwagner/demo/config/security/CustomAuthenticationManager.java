package de.aaronwagner.demo.config.security;

import de.aaronwagner.demo.exceptions.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (!DigestUtils.sha256Hex(password).equals(userDetails.getPassword())) {
            throw new NotAuthorizedException();
        }

        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                AuthorityUtils.createAuthorityList("Demo")
        );
    }

}
