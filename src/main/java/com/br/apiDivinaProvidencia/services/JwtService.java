package com.br.apiDivinaProvidencia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	public String generatedToken(User user) {

		return Jwts.builder().setSubject(user.getLogin()).signWith(SignatureAlgorithm.HS512, this.secret).compact();

	}

	public String generatedTokenWithExpirationSendEmail(String email, String expiracao) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);

		return Jwts.builder().setSubject(email.trim()).setExpiration(data).signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
	}

	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
	}

	public boolean tokenIsValid(String token) {

		try {
			Claims claims = getClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(data);
		} catch (Exception e) {
			return false;
		}
	}

	public String getLoginUser(String token) throws ExpiredJwtException {
		return (String) this.getClaims(token).getSubject();
	}

}
