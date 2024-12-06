package cl.duoc.app.recetas_backend.response;

import cl.duoc.app.recetas_backend.model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginResponseTest {

    @Test
    void testLoginResponseConstructor() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        String token = "testToken";

        // Creación de la instancia
        LoginResponse loginResponse = new LoginResponse(optionalUsuario, token);

        // Validaciones
        assertNotNull(loginResponse);
        assertEquals(optionalUsuario, loginResponse.getUsuario());
        assertEquals(token, loginResponse.getToken());
    }

    @Test
    void testSettersAndGetters() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        String token = "testToken";

        // Creación de la instancia
        LoginResponse loginResponse = new LoginResponse(optionalUsuario, token);

        // Validaciones
        assertEquals(optionalUsuario, loginResponse.getUsuario());
        assertEquals(token, loginResponse.getToken());
    }

    @Test
    void testToString() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        String token = "testToken";

        // Creación de la instancia
        LoginResponse loginResponse = new LoginResponse(optionalUsuario, token);

        // Validación del toString generado por Lombok
        String expected = "LoginResponse(usuario=Optional[Usuario(id=null, nombreUsuario=testUser, nombreCompleto=null, email=null, contrasena=null, rol=false)], token=testToken)";
        assertEquals(expected, loginResponse.toString());
    }

}
