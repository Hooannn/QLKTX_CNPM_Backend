package com.ht.qlktx.filter;

import com.ht.qlktx.entities.User;
import com.ht.qlktx.utils.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${application.security.jwt.access-secret-key}")
    private String accessSecretKey;
    @Value("${application.security.jwt.password-secret-key}")
    private String passwordSecretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.password.expiration}")
    private long resetPasswordExpiration;

    private final RedisService redisService;

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(accessSecretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractSub(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    public String extractRole(String jwt) {
        return extractClaim(jwt, claims -> claims.get("role")).toString();
    }

    public String generateAccessToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().toString());
        return buildToken(claims, user, jwtExpiration, accessSecretKey);
    }

    public String generateResetPasswordToken(String email) {
        var token = buildToken(new HashMap<>(), email, resetPasswordExpiration, passwordSecretKey);
        redisService.setValue("reset_password_token:" + email, token, resetPasswordExpiration / 1000);
        return token;
    }

    public VerificationTokenResult verifyResetPasswordToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(passwordSecretKey))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return new VerificationTokenResult(false, null);
        }
        var email = claims.getSubject();
        var expiration = claims.getExpiration();
        var isTokenExpired = expiration.before(new Date());
        var tokenFromRedis = redisService.getValue("reset_password_token:" + email);
        var isTokenValid = !isTokenExpired && tokenFromRedis != null && tokenFromRedis.equals(token);
        return new VerificationTokenResult(isTokenValid, email);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            User user,
            long expiration,
            String secret
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            String subject,
            long expiration,
            String secret
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }
}
