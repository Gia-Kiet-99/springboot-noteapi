package com.example.noteapi.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenProvider {
  private final String JWT_SECRET_KEY = "giki1999";
  private final long JWT_EXPIRATION = 900000L;

  public String generateToken(CustomUserDetails userDetails) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    return Jwts.builder()
        .setSubject(userDetails.getUser().getId().toString())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .claim("fullName", userDetails.getUser().getFullName())
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
        .compact();
  }

  public UUID getUserIdFromToken(String accessToken) {
    Claims claims = Jwts.parser()
        .setSigningKey(JWT_SECRET_KEY)
        .parseClaimsJws(accessToken)
        .getBody();
    return UUID.fromString(claims.getSubject());
  }

  public boolean validateToken(String accessToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(accessToken);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }
}
