package cl.duoc.app.recetas_backend.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void testGenerateToken() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);
        assertNotNull(token);
        assertEquals(username, jwtUtil.extractUsername(token));
    }

    @Test
    void testExtractUsername() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);
        String extractedUsername = jwtUtil.extractUsername(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void testExtractExpiration() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);
        Date expiration = jwtUtil.extractExpiration(token);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    void testValidateTokenValid() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);
        boolean isValid = jwtUtil.validateToken(token, username);
        assertTrue(isValid, "El token debe ser válido cuando no ha expirado y el usuario coincide.");
    }

    @Test
    void testValidateTokenExpired() {
        JwtUtil shortLivedJwtUtil = new JwtUtil() {
            @Override
            public String generateToken(String username) {
                return Jwts.builder()
                        .setSubject(username)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000)) // 1 segundo
                        .signWith(Keys.hmacShaKeyFor("clave_secreta_clave_secreta_clave_secreta".getBytes()), SignatureAlgorithm.HS256)
                        .compact();
            }
        };

        String username = "testUser";
        String token = shortLivedJwtUtil.generateToken(username);

        try {
            Thread.sleep(1500); // Esperar 1.5 segundos para asegurar expiración
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            fail("Error inesperado al esperar la expiración del token");
        }

        boolean isValid;
        try {
            isValid = jwtUtil.validateToken(token, username);
            fail("Se esperaba una ExpiredJwtException");
        } catch (ExpiredJwtException e) {
            String exceptionMessage = e.getMessage();
            assertTrue(exceptionMessage.contains("JWT expired"), "El mensaje debe contener 'JWT expired'");
            isValid = false;
        }

        assertFalse(isValid, "El token no debe ser válido después de expirar.");
    }

    @Test
    void testValidateTokenDifferentUsername() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);

        boolean isValid = jwtUtil.validateToken(token, "anotherUser");

        assertFalse(isValid, "El token no debe ser válido si el usuario no coincide.");
    }

    @Test
    void testValidateTokenIsTokenExpiredFalse() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username);

        boolean isValid = jwtUtil.validateToken(token, username);

        assertTrue(isValid, "El token debe ser válido si no está expirado y el usuario coincide.");
    }
}
