package pt.diariobordo.diario.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value; // IMPORTANTE: Usar o do Spring
import org.springframework.stereotype.Service;
import pt.diariobordo.diario.entity.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Pegando a chave do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("diario-de-bordo")
                    .withSubject(usuario.getEmail())
                    .withClaim("role", usuario.getRole().name()) // AQUI: Adiciona a role no payload
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("diario-de-bordo")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            // Se o token for inválido ou expirado, retorna vazio para o filtro barrar
            return "";
        }
    }

    private Instant genExpirationDate() {
        // Define expiração (ex: 2 horas à frente no fuso de Brasília/Portugal conforme necessário)
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}