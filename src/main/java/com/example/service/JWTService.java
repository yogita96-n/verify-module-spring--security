package com.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("{$jwt.algorithm.key}")
private String algorithmKey;
    @Value("${jwt.expiry.time}")
    private int expiryTime;
    @Value("{$issuer}")
    private String issuer;
    private Algorithm algorithm;
    private static  final String USER_NAME="";

@PostConstruct
    public void PostConstruct(){
    algorithm = Algorithm.HMAC256(algorithmKey);

}
//create token
    public String generateToken(Book book){
        return JWT.create().
                withClaim(USER_NAME,book.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis()+ expiryTime)).
                withIssuer(issuer).
                sign(algorithm);
    }
}
