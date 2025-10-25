package com.example.api_gateway.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class JwtService {

    private String secretKey = "sdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdc";

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUserIdFromToken(String token) {
        log.info("Extracting user id from token: {}", token);
        return Jwts.parser().
                verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
