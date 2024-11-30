package com.example.backend.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;

//import java.security.Key;

import org.springframework.stereotype.Service;

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
		expTime.setTime(expTime.getTime() + 1000 * 60 * 5);
		
//		byte[] secretByteKey = Base64.getDecoder().decode(base64Key);
//		Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
		SecretKey signKey = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		Map<String, Object> headerMap = new HashMap<>();
		headerMap.put("type", "JWT");
		headerMap.put("alg", "HS256");
		
		Map<String, Object> map = new HashMap<>();
		map.put(key, value);
		
		JwtBuilder builder = Jwts.builder().setHeader(headerMap)
				.setClaims(map)
				.setExpiration(expTime)
				.signWith(signKey, SignatureAlgorithm.HS256);
		
		return builder.compact();
	}
	
	@Override
	public Claims getClaims(String token) {
		if(token != null && !"".equals(token)) {
			try {
				
				SecretKey signKey = Keys.hmacShaKeyFor(secretKey.getBytes());
				return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
				
			} catch (ExpiredJwtException e) {
				// 만료됨
			} catch (JwtException e) {
				// 유효하지 않음
			}
		}
		return null;
	}

	@Override
	public boolean isValid(String token) {
		return this.getClaims(token) != null;
	}

	@Override
	public int getId(String token) {
		Claims claims = this.getClaims(token);
		if(claims != null) {
			return Integer.parseInt(claims.get("id").toString());
		}
		return 0;
	}

}
