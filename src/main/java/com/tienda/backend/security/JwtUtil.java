package com.tienda.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.security.Key;
import java.util.Date;

/**
 * Clase utilitaria para generar JWTs.
 */
@Component
public class JwtUtil {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Clave secreta aleatoria
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 4; // 4 horas

    public String generateToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token){
        try{
            getClaims(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }
}
