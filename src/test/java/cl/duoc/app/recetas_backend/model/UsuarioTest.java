package cl.duoc.app.recetas_backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    void testUsuarioCreation() {
        // Crear instancia de Usuario
        Usuario usuario = new Usuario(1L, "user123", "Juan Pérez", "juan@example.com", "password123", true);

        // Verificar propiedades
        Assertions.assertEquals(1L, usuario.getId());
        Assertions.assertEquals("user123", usuario.getNombreUsuario());
        Assertions.assertEquals("Juan Pérez", usuario.getNombreCompleto());
        Assertions.assertEquals("juan@example.com", usuario.getEmail());
        Assertions.assertEquals("password123", usuario.getContrasena());
        Assertions.assertTrue(usuario.isRol());
    }

    @Test
    void testSettersAndGetters() {
        // Crear instancia vacía
        Usuario usuario = new Usuario();

        // Establecer valores
        usuario.setId(2L);
        usuario.setNombreUsuario("user456");
        usuario.setNombreCompleto("Ana López");
        usuario.setEmail("ana@example.com");
        usuario.setContrasena("pass456");
        usuario.setRol(false);

        // Verificar valores
        Assertions.assertEquals(2L, usuario.getId());
        Assertions.assertEquals("user456", usuario.getNombreUsuario());
        Assertions.assertEquals("Ana López", usuario.getNombreCompleto());
        Assertions.assertEquals("ana@example.com", usuario.getEmail());
        Assertions.assertEquals("pass456", usuario.getContrasena());
        Assertions.assertFalse(usuario.isRol());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos instancias iguales
        Usuario usuario1 = new Usuario(1L, "user123", "Juan Pérez", "juan@example.com", "password123", true);
        Usuario usuario2 = new Usuario(1L, "user123", "Juan Pérez", "juan@example.com", "password123", true);

        // Crear una instancia diferente
        Usuario usuario3 = new Usuario(2L, "user456", "Ana López", "ana@example.com", "pass456", false);

        // Verificar igualdad
        Assertions.assertEquals(usuario1, usuario2);
        Assertions.assertNotEquals(usuario1, usuario3);

        // Verificar hashCode
        Assertions.assertEquals(usuario1.hashCode(), usuario2.hashCode());
        Assertions.assertNotEquals(usuario1.hashCode(), usuario3.hashCode());
    }

    @Test
    void testToString() {
        // Crear instancia
        Usuario usuario = new Usuario(1L, "user123", "Juan Pérez", "juan@example.com", "password123", true);

        // Verificar contenido del toString
        String toStringResult = usuario.toString();
        Assertions.assertTrue(toStringResult.contains("user123"));
        Assertions.assertTrue(toStringResult.contains("Juan Pérez"));
        Assertions.assertTrue(toStringResult.contains("juan@example.com"));
        Assertions.assertTrue(toStringResult.contains("password123"));
    }
}
