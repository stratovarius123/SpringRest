package br.com.cadastro.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String userName){
		return Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String substring) {
		Claims claims = getClaims(substring);
		if(claims != null){
			String username = claims.getSubject();
			Date data = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && data != null && now.before(data)){
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String substring) {
		try{
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(substring).getBody();
		}catch(Exception e){
			return null;
		}
	}

	public String getUserName(String substring) {
		Claims claims = getClaims(substring);
		if(claims != null)
			return claims.getSubject();
		
		return null;
	}

}
