package cl.duoc.app.recetas_backend.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UsuarioReqTest {

    @Test
    void testUsuarioReqConstructor() {
        // Datos de prueba
        String nombre = "testUser";
        String password = "testPass";

        // Creación de la instancia
        UsuarioReq usuarioReq = new UsuarioReq(nombre, password);

        // Validaciones
        assertNotNull(usuarioReq);
        assertEquals(nombre, usuarioReq.getNombre());
        assertEquals(password, usuarioReq.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        // Creación de la instancia
        UsuarioReq usuarioReq = new UsuarioReq("testUser", "testPass");

        // Modificar valores
        usuarioReq.setNombre("newUser");
        usuarioReq.setPassword("newPass");

        // Validaciones
        assertEquals("newUser", usuarioReq.getNombre());
        assertEquals("newPass", usuarioReq.getPassword());
    }

    @Test
    void testToString() {
        // Datos de prueba
        String nombre = "testUser";
        String password = "testPass";

        // Creación de la instancia
        UsuarioReq usuarioReq = new UsuarioReq(nombre, password);

        // Validación del toString generado por Lombok
        String expected = "UsuarioReq(nombre=testUser, password=testPass)";
        assertEquals(expected, usuarioReq.toString());
    }
}
