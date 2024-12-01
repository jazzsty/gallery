package com.example.backend.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;

//import java.security.Key;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import jakarta.xml.bind.DatatypeConverter;
import io.jsonwebtoken.security.Keys;

@Service("jwtService")
public class JwtServiceImpl implements JwtService {

	private String secretKey = "abcd1234!@#$jajdnvpowjmw[jejovpnw-pjwpojwje9e9h9wjsj84";
//	private String base64Key = Base64.getEncoder().encodeToString(secretKey.getBytes());

	@Override
	public String getToken(String key, Object value) {

		Date expTime = new Date();
		expTime.setTime(expTime.getTime() + 1000 * 60 * 30);
		
//		byte[] secretByteKey = Base64.getDecoder().decode(base64Key);
//		Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
		SecretKey signKey = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("type", "JWT");
		headerMap.put("alg", "HS256");
		
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		
		JwtBuilder builder = Jwts.builder()
				.setHeader(headerMap)
				.setClaims(map)
				.setExpiration(expTime)
				.signWith(signKey, SignatureAlgorithm.HS256);
		
		return builder.compact();
	}
	
	@Override
	public Claims getClaims(String token) {
		System.out.println("check step getClaims");
//		if(token != null && !"".equals(token)) {
		if(token != null && !token.isEmpty()) {
			try {
				
				SecretKey signKey = Keys.hmacShaKeyFor(secretKey.getBytes());
				Claims claims = Jwts.parserBuilder()
						.setSigningKey(signKey)
						.build()
						.parseClaimsJws(token)
						.getBody();
				
				return claims;
			} catch (ExpiredJwtException e) {
				// 만료됨
				System.err.println("Token has expired: " + e.getMessage());
//				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token expired");
			} catch (JwtException e) {
				// 유효하지 않음
				System.err.println("Invalid token: " + e.getMessage());
//				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
			}
		}
		return null;
	}

	@Override
	public boolean isValid(String token) {
		System.out.println("check step isValid");
		return this.getClaims(token) != null;
	}

	@Override
	public int getId(String token) {
		System.out.println("check step getId");
		Claims claims = this.getClaims(token);
		if(claims != null && claims.containsKey("id")) {
			try {
				return Integer.parseInt(claims.get("id").toString());
			} catch (NumberFormatException e) {
				System.err.println("Invalid token: " + e.getMessage());
			}
		}
		return 0;
	}

}
