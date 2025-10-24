package com.example.api_gateway.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    private String secretKey = "sdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdcsdsdcsdcsdcscsdc";

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUserIdFromToken(String token) {
        return Jwts.parser().
                verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
