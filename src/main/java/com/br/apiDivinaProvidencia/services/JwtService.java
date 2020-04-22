package com.br.apiDivinaProvidencia.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.br.apiDivinaProvidencia.documents.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JwtService {

	@Value("${security.jwt.expiration}")
	private String expiration;
	@Value("${jwt.secret}")
	private String secret;

	public String generatedToken(User user) {
		long exp = Long.valueOf(this.expiration);
		LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(exp);
		Date date = Date.from(dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant());

		return Jwts.builder().setSubject(user.getLogin())
				.signWith(SignatureAlgorithm.HS512, this.secret).compact();

	}

	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser()
				.setSigningKey(this.secret).parseClaimsJws(token).getBody();
	}

	public boolean tokenIsValid(String token) {

		try {

			Claims claims = this.getClaims(token);
			Date expiration = claims.getExpiration();
			LocalDateTime dateHourExpiration = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			return !LocalDateTime.now().isAfter(dateHourExpiration);

		} catch (Exception e) {
			return false;
		}
	}

	public String getLoginUser(String token) throws ExpiredJwtException {
		return (String) this.getClaims(token).getSubject();
	}

}
