package de.melwink.exma_backend.controller;

import de.melwink.exma_backend.model.LoginRequest;
import de.melwink.exma_backend.model.LoginResponse;
import de.melwink.exma_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/register")
    public String registerUser() {
        return "register here";
    }

    @PostMapping("/auth/login")
    public LoginResponse loginUser(@RequestBody @Validated LoginRequest loginRequest) {
        return authService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

}
