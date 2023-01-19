package gov.edu.anm.presenter.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${JWT_AUTH_SECRET}")
    private String SECRET_KEY;
    private static final Integer TOKEN_ACTIVE_HOURS = 2;
    private static final Integer TOKEN_REFRESH_HOURS = 24;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public Map<String, String> generateTokens(UserDetails userDetails) {
        return generateTokens(new HashMap<>(), userDetails);
    }

    public Map<String, String> generateTokens(Map<String, Object> extraClaims, UserDetails userDetails) {
        final Map<String, Object> tokenClaims = new HashMap<>();
        tokenClaims.put("roles", userDetails.getAuthorities());
        tokenClaims.putAll(extraClaims);

        final String access_token = Jwts.
                builder()
                .setClaims(tokenClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(TOKEN_ACTIVE_HOURS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        final String refresh_token = Jwts.
                builder()
                .setClaims(tokenClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(TOKEN_REFRESH_HOURS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return tokens;
    }

    public String refreshAccessToken(UserDetails userDetails) {
        return refreshAccessToken(new HashMap<>(), userDetails);
    }

    public String refreshAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        final Map<String, Object> tokenClaims = new HashMap<>();
        tokenClaims.put("roles", userDetails.getAuthorities());
        tokenClaims.putAll(extraClaims);

        return Jwts.
                builder()
                .setClaims(tokenClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(TOKEN_ACTIVE_HOURS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}




