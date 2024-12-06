package cl.duoc.app.recetas_backend.util;

import org.springframework.web.bind.annotation.RequestHeader;

public class Util {

    private Util() {
    }

    public static boolean isEmptyOrNull(String string) {
        return null == string || string.isEmpty();
    }

    public static boolean validateToken(@RequestHeader("Authorization") String token, JwtUtil jwtUtil) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }
        token = token.substring(7);
        String username = jwtUtil.extractUsername(token);
        return Boolean.TRUE.equals(jwtUtil.validateToken(token, username));
    }

}
