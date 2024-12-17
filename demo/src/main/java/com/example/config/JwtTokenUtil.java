package com.example.config;

import com.example.exption.CustomException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenUtil {


    private static final String SECRET_KEY = "secret";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }


    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 1000 * 60* 60); // 1 hour
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public void validateToken(String token) throws  ExpiredJwtException {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e ) {
             throw new CustomException("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            throw new CustomException("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        } catch (AccessDeniedException e) {
            throw new CustomException("FORBIDDEN", HttpStatus.FORBIDDEN);
        }catch (SignatureException e) {
            throw new CustomException("BAD_REQUEST", HttpStatus.BAD_REQUEST);
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
