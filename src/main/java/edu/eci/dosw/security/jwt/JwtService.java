package edu.eci.dosw.security.jwt;

import edu.eci.dosw.model.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtService {

    private static final String SECRET =
            "dosw-secret-key-dosw-secret-key-dosw-secret-key-dosw-secret-key";

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String username, Role role) {

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.name())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 3600000))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}
