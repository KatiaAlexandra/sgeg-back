package utez.edu.mx.sgeg.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {
    private static final String SECRET = "o+BtBTvTO5ihCqB9hIXb5bl0npGGFtxcKeiV4u4P0aAZwPUdC5Xo1qmlsGpF4qZYS+LxVr15AAInngBwS/eRg==";
    private static final long EXPIRATION_TIME = 86400000; // 1 día

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Genera los claims personalizados (sin password por seguridad)
    private Map<String, Object> generateClaims(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return claims;
    }

    // Genera el JWT
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setClaims(generateClaims(username, role))
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    // Valida si el token es válido
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    // Extrae todos los claims del token
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtiene el username (subject)
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Obtiene el rol
    public String getRoleFromToken(String token) {
        return (String) getClaimsFromToken(token).get("role");
    }

    // (Opcional) Obtener fecha de expiración
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }
}