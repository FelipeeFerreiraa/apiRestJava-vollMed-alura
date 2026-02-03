package med.voll.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.model.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		System.out.println(secret);

		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);

			String token = JWT.create().withIssuer("API voll.med").withSubject(usuario.getLogin())
					.withClaim("id:", usuario.getId()).withExpiresAt(dataExpiracao()).sign(algorithm);

			return token;

		} catch (JWTCreationException exception) {
			throw new RuntimeException("ERRO AO GERAR TOKEN JWT...");
		}
	}

	public String getSubject(String tokenJWT) {

		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer("API voll.med").build().verify(tokenJWT).getSubject();

		} catch (JWTVerificationException exception) {
			throw new RuntimeException("TOKEN JWT INVÁLIDO OU EXPIRADO!");
		}

	}

	private Instant dataExpiracao() {

		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
	}

}
