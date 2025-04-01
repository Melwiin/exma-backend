package de.melwink.exma_backend.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    String accessToken;
}
