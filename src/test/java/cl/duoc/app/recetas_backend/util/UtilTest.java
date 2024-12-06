package cl.duoc.app.recetas_backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilTest {

    @Test
    void testIsEmptyOrNullWithNull() {
        assertTrue(Util.isEmptyOrNull(null), "Se esperaba true para un valor null.");
    }

    @Test
    void testIsEmptyOrNullWithEmptyString() {
        assertTrue(Util.isEmptyOrNull(""), "Se esperaba true para una cadena vacía.");
    }

    @Test
    void testIsEmptyOrNullWithNonEmptyString() {
        assertFalse(Util.isEmptyOrNull("test"), "Se esperaba false para una cadena no vacía.");
    }

    @Test
    void testValidateTokenWithNullToken() {
        JwtUtil jwtUtilMock = mock(JwtUtil.class);
        String token = null;

        boolean result = Util.validateToken(token, jwtUtilMock);

        assertFalse(result, "Un token nulo debería considerarse inválido y retornar false.");
    }

    @Test
    void testValidateTokenWithInvalidPrefix() {
        JwtUtil jwtUtilMock = mock(JwtUtil.class);
        String token = "InvalidToken";

        boolean result = Util.validateToken(token, jwtUtilMock);

        assertFalse(result, "Un token con prefijo inválido debería considerarse inválido y retornar false.");
    }

    @Test
    void testValidateTokenWithValidToken() {
        JwtUtil jwtUtilMock = mock(JwtUtil.class);
        String token = "Bearer validToken";
        String extractedUsername = "testUser";

        when(jwtUtilMock.extractUsername("validToken")).thenReturn(extractedUsername);
        when(jwtUtilMock.validateToken("validToken", extractedUsername)).thenReturn(true);

        boolean result = Util.validateToken(token, jwtUtilMock);

        assertTrue(result, "Un token válido debería considerarse válido y retornar true.");
        verify(jwtUtilMock).extractUsername("validToken");
        verify(jwtUtilMock).validateToken("validToken", extractedUsername);
    }

    @Test
    void testValidateTokenWithInvalidToken() {
        JwtUtil jwtUtilMock = mock(JwtUtil.class);
        String token = "Bearer invalidToken";
        String extractedUsername = "testUser";

        when(jwtUtilMock.extractUsername("invalidToken")).thenReturn(extractedUsername);
        when(jwtUtilMock.validateToken("invalidToken", extractedUsername)).thenReturn(false);

        boolean result = Util.validateToken(token, jwtUtilMock);

        assertFalse(result, "Un token inválido debería considerarse inválido y retornar false.");
        verify(jwtUtilMock).extractUsername("invalidToken");
        verify(jwtUtilMock).validateToken("invalidToken", extractedUsername);
    }
}
