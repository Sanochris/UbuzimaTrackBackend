
package com.chris.health_monitoring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
   private static final String SECRET = "mySuperSecretKeyThatIsAtLeast32Chars!";
   private static final long EXPIRATION = 28800000L;

   public JwtService() {
   }

   private Key getSigningKey() {
      return Keys.hmacShaKeyFor("mySuperSecretKeyThatIsAtLeast32Chars!".getBytes());
   }

   public String generateToken(String username, String role) {
      return Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 28800000L)).signWith(this.getSigningKey(), SignatureAlgorithm.HS256).compact();
   }

   public String extractUsername(String token) {
      return this.getClaims(token).getSubject();
   }

   public String extractRole(String token) {
      return (String)this.getClaims(token).get("role", String.class);
   }

   public boolean isTokenValid(String token) {
      try {
         this.getClaims(token);
         return true;
      } catch (Exception var3) {
         return false;
      }
   }

   private Claims getClaims(String token) {
      return (Claims)Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody();
   }
}
