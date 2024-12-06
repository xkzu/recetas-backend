package cl.duoc.app.recetas_backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValoracionTest {

    @Test
    void testValoracionCreation() {
        // Crear una instancia de Valoracion con valores iniciales
        Valoracion valoracion = new Valoracion();
        valoracion.setId(1L);
        valoracion.setIdReceta(10L);
        valoracion.setIdUsuario(5L);
        valoracion.setPuntaje(4);

        // Verificar propiedades
        Assertions.assertEquals(1L, valoracion.getId());
        Assertions.assertEquals(10L, valoracion.getIdReceta());
        Assertions.assertEquals(5L, valoracion.getIdUsuario());
        Assertions.assertEquals(4, valoracion.getPuntaje());
    }

    @Test
    void testSettersAndGetters() {
        // Crear una instancia vacía
        Valoracion valoracion = new Valoracion();

        // Establecer valores
        valoracion.setId(2L);
        valoracion.setIdReceta(20L);
        valoracion.setIdUsuario(15L);
        valoracion.setPuntaje(5);

        // Verificar valores
        Assertions.assertEquals(2L, valoracion.getId());
        Assertions.assertEquals(20L, valoracion.getIdReceta());
        Assertions.assertEquals(15L, valoracion.getIdUsuario());
        Assertions.assertEquals(5, valoracion.getPuntaje());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos instancias iguales
        Valoracion valoracion1 = new Valoracion();
        valoracion1.setId(1L);
        valoracion1.setIdReceta(10L);
        valoracion1.setIdUsuario(5L);
        valoracion1.setPuntaje(4);

        Valoracion valoracion2 = new Valoracion();
        valoracion2.setId(1L);
        valoracion2.setIdReceta(10L);
        valoracion2.setIdUsuario(5L);
        valoracion2.setPuntaje(4);

        // Crear una instancia diferente
        Valoracion valoracion3 = new Valoracion();
        valoracion3.setId(2L);
        valoracion3.setIdReceta(20L);
        valoracion3.setIdUsuario(15L);
        valoracion3.setPuntaje(3);

        // Verificar igualdad
        Assertions.assertEquals(valoracion1, valoracion2);
        Assertions.assertNotEquals(valoracion1, valoracion3);

        // Verificar hashCode
        Assertions.assertEquals(valoracion1.hashCode(), valoracion2.hashCode());
        Assertions.assertNotEquals(valoracion1.hashCode(), valoracion3.hashCode());
    }

    @Test
    void testToString() {
        // Crear una instancia
        Valoracion valoracion = new Valoracion();
        valoracion.setId(1L);
        valoracion.setIdReceta(10L);
        valoracion.setIdUsuario(5L);
        valoracion.setPuntaje(4);

        // Verificar contenido del toString
        String toStringResult = valoracion.toString();
        Assertions.assertTrue(toStringResult.contains("1"));
        Assertions.assertTrue(toStringResult.contains("10"));
        Assertions.assertTrue(toStringResult.contains("5"));
        Assertions.assertTrue(toStringResult.contains("4"));
    }
}
