package cl.duoc.app.recetas_backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ComentarioTest {

    @Test
    void testComentarioCreation() {
        // Fecha simulada
        LocalDateTime fecha = LocalDateTime.now();

        // Crear instancia de Comentario
        Comentario comentario = new Comentario();
        comentario.setId(1L);
        comentario.setIdReceta(10L);
        comentario.setIdUsuario(5L);
        comentario.setContenido("Este es un comentario de prueba.");
        comentario.setFechaCreacion(fecha);
        comentario.setVisible(true);

        // Verificar propiedades
        Assertions.assertEquals(1L, comentario.getId());
        Assertions.assertEquals(10L, comentario.getIdReceta());
        Assertions.assertEquals(5L, comentario.getIdUsuario());
        Assertions.assertEquals("Este es un comentario de prueba.", comentario.getContenido());
        Assertions.assertEquals(fecha, comentario.getFechaCreacion());
        Assertions.assertTrue(comentario.isVisible());
    }

    @Test
    void testSettersAndGetters() {
        // Crear instancia vacía
        Comentario comentario = new Comentario();

        // Establecer valores
        LocalDateTime fecha = LocalDateTime.now();
        comentario.setId(2L);
        comentario.setIdReceta(20L);
        comentario.setIdUsuario(15L);
        comentario.setContenido("Otro comentario.");
        comentario.setFechaCreacion(fecha);
        comentario.setVisible(false);

        // Verificar valores
        Assertions.assertEquals(2L, comentario.getId());
        Assertions.assertEquals(20L, comentario.getIdReceta());
        Assertions.assertEquals(15L, comentario.getIdUsuario());
        Assertions.assertEquals("Otro comentario.", comentario.getContenido());
        Assertions.assertEquals(fecha, comentario.getFechaCreacion());
        Assertions.assertFalse(comentario.isVisible());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos instancias iguales
        LocalDateTime fecha = LocalDateTime.now();
        Comentario comentario1 = new Comentario();
        comentario1.setId(1L);
        comentario1.setIdReceta(10L);
        comentario1.setIdUsuario(5L);
        comentario1.setContenido("Comentario 1");
        comentario1.setFechaCreacion(fecha);
        comentario1.setVisible(true);

        Comentario comentario2 = new Comentario();
        comentario2.setId(1L);
        comentario2.setIdReceta(10L);
        comentario2.setIdUsuario(5L);
        comentario2.setContenido("Comentario 1");
        comentario2.setFechaCreacion(fecha);
        comentario2.setVisible(true);

        // Crear una instancia diferente
        Comentario comentario3 = new Comentario();
        comentario3.setId(2L);
        comentario3.setIdReceta(20L);
        comentario3.setIdUsuario(10L);
        comentario3.setContenido("Comentario 2");
        comentario3.setFechaCreacion(fecha);
        comentario3.setVisible(false);

        // Verificar igualdad
        Assertions.assertEquals(comentario1, comentario2);
        Assertions.assertNotEquals(comentario1, comentario3);

        // Verificar hashCode
        Assertions.assertEquals(comentario1.hashCode(), comentario2.hashCode());
        Assertions.assertNotEquals(comentario1.hashCode(), comentario3.hashCode());
    }

    @Test
    void testToString() {
        // Crear instancia
        LocalDateTime fecha = LocalDateTime.now();
        Comentario comentario = new Comentario();
        comentario.setId(1L);
        comentario.setIdReceta(10L);
        comentario.setIdUsuario(5L);
        comentario.setContenido("Comentario para toString.");
        comentario.setFechaCreacion(fecha);
        comentario.setVisible(true);

        // Verificar contenido del toString
        String toStringResult = comentario.toString();
        Assertions.assertTrue(toStringResult.contains("1"));
        Assertions.assertTrue(toStringResult.contains("10"));
        Assertions.assertTrue(toStringResult.contains("5"));
        Assertions.assertTrue(toStringResult.contains("Comentario para toString."));
        Assertions.assertTrue(toStringResult.contains(fecha.toString()));
        Assertions.assertTrue(toStringResult.contains("true"));
    }

}