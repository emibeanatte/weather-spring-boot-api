package com.weather.api.emibeanatte.security.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;



@Service
public class JwtService {
    
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public Optional<String> generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    
    public Optional<String> generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        if (extraClaims == null) {
            return Optional.empty();
        }

        return Optional.of(buildToken(extraClaims, userDetails, jwtExpiration));
    }
    
    public String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration){

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256,getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token).getBody().getSubject();
    }

    private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token).getBody().getExpiration();
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    public long getExpirationTime() {
        return jwtExpiration;
    }
    
    private byte[] getSecretKey() {
        return secretKey.getBytes(StandardCharsets.UTF_8);
    }

    
}
