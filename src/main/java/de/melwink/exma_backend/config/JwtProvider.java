package de.melwink.exma_backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String issueJwt(long userId, String email, List<String> roles) {
       return JWT
               .create()
               .withSubject(String.valueOf(userId))
               .withClaim("email", email)
               .withClaim("roles", roles)
               .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))) // One day duration
               .sign(Algorithm.HMAC256(secret));
    }
}
