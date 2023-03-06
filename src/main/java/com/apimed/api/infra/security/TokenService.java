package com.apimed.api.infra.security;

import com.apimed.api.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) { 
        try {
            var algoritmo = Algorithm.HMAC256("12345678");
            return JWT.create()
                .withIssuer("Med API")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }        
    }

    public String getSubject(String tokenJWT) {
        try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.require(algoritmo)
                                .withIssuer("Med API")
                                .build()
                                .verify(tokenJWT)
                                .getSubject();
        } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!");
        }
}

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}