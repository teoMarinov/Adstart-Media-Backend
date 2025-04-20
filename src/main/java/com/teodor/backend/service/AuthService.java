package com.teodor.backend.service;

import com.teodor.backend.dto.UserCredentialsDto;
import com.teodor.backend.entity.User;
import com.teodor.backend.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public void registerUser(@NotNull UserCredentialsDto input) {
        Optional<User> existingUser = userRepository.findByUsername(input.getUsername());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User " + input.getUsername() +  " already exists");
        }

        User user = new User(input.getUsername(), passwordEncoder.encode(input.getPassword()));

        userRepository.save(user);
    }
    public User loginUser(UserCredentialsDto input) throws RuntimeException{
        User user = userRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new AuthenticationException("Bad credentials") {});

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return user;
    }
}
