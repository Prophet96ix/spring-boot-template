package de.aaronwagner.demo.config.security;

import de.aaronwagner.demo.models.Users;
import de.aaronwagner.demo.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException
                        (
                                String.format("User with email %s not found!", email)
                        )
                );

        return User
                .withUsername(users.getEmail())
                .password(users.getPasswordHash())
                .roles("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false).disabled(false)
                .build();
    }

}