package de.melwink.exma_backend.service;

import de.melwink.exma_backend.model.LoginResponse;
import de.melwink.exma_backend.model.User;
import de.melwink.exma_backend.repository.UserRepository;
import de.melwink.exma_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResponse loginUser(String email, String password) {
        User user = userRepository.findUserByEmail(email).orElseThrow();
        var accessToken = jwtService.issueJwt(user.getId(), user.getEmail(), List.of("USER"));
        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
