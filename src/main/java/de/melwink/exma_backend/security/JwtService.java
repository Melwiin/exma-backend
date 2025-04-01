package de.melwink.exma_backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties properties;

    public String issueJwt(long userId, String email, List<String> roles) {

        return JWT
               .create()
               .withSubject(String.valueOf(userId))
               .withClaim("email", email)
               .withClaim("roles", roles)
               .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))) // One day duration
               .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }

    public DecodedJWT decode(String token) {
        return JWT
                .require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }

    public UserPrincipal jwtToPrincipal(DecodedJWT jwt) {
        return UserPrincipal.builder()
                .userId(Long.parseLong(jwt.getSubject()))
                .email(jwt.getClaim("email").asString())
                .authorities(extractAuthorities(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {
        var claim = jwt.getClaim("roles");
        if (claim.isNull() || claim.isMissing()) {
            return List.of();
        }
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
