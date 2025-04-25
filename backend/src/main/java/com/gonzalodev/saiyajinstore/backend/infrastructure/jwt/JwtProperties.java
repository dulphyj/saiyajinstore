package com.gonzalodev.saiyajinstore.backend.infrastructure.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@ConfigurationProperties(prefix = "application.security.jwt")
@Data
public class JwtProperties {
    private String secretKey;
    private long expiration;
    private  String headerAuthorization;
    private String tokenBearer;

    public SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(getSecretKey().getBytes());
    }
}
