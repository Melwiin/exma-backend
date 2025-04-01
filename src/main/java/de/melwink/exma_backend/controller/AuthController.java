package de.melwink.exma_backend.controller;

import de.melwink.exma_backend.config.JwtProvider;
import de.melwink.exma_backend.model.LoginRequest;
import de.melwink.exma_backend.model.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;

    @GetMapping("/auth/register")
    public void registerUser() {

    }

    @PostMapping("/auth/login")
    public LoginResponse loginUser(@RequestBody @Validated LoginRequest loginRequest) {
        var accessToken = jwtProvider.issueJwt(1L, loginRequest.getEmail(), List.of("USER"));
        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

}
