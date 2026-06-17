package ms_login.ms_login.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {

    private static final String SECRET = "sanosysalvos_super_secret_key_2026_123456";

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private static final long EXPIRATION_TIME = 3600000;

    public String generarToken(String username, Long usuarioId, String rol) {
        return Jwts.builder()
                .setSubject(username)
                .claim("id", usuarioId)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractSubject(String token) {
        return obtenerClaims(token).getSubject();
    }

    public Long extractUsuarioId(String token) {
        Object id = obtenerClaims(token).get("id");

        if (id instanceof Integer) {
            return ((Integer) id).longValue();
        }

        if (id instanceof Long) {
            return (Long) id;
        }

        return Long.valueOf(id.toString());
    }

    public String extractRol(String token) {
        Object rol = obtenerClaims(token).get("rol");
        return rol != null ? rol.toString() : null;
    }

    private Claims obtenerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
