package cl.duoc.app.recetas_backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class RecetaTest {

    @Test
    void testRecetaCreation() {
        // Crear una instancia de Receta con valores
        Receta receta = new Receta(
                1L,
                "Paella",
                "Española",
                "Arroz, azafrán, mariscos",
                "España",
                "Intermedia",
                90,
                "Cocine los mariscos, prepare el arroz...",
                45,
                4,
                "http://imagen.com/paella.jpg",
                "http://video.com/paella",
                LocalDateTime.now(),
                101L
        );

        // Verificar propiedades
        Assertions.assertEquals(1L, receta.getId());
        Assertions.assertEquals("Paella", receta.getNombre());
        Assertions.assertEquals("Española", receta.getTipoCocina());
        Assertions.assertEquals("Arroz, azafrán, mariscos", receta.getIngredientes());
        Assertions.assertEquals("España", receta.getPaisOrigen());
        Assertions.assertEquals("Intermedia", receta.getDificultad());
        Assertions.assertEquals(90, receta.getPopularidad());
        Assertions.assertEquals("Cocine los mariscos, prepare el arroz...", receta.getInstruccionesPreparacion());
        Assertions.assertEquals(45, receta.getTiempoCoccion());
        Assertions.assertEquals(4, receta.getPorciones());
        Assertions.assertEquals("http://imagen.com/paella.jpg", receta.getFotografiaUrl());
        Assertions.assertEquals("http://video.com/paella", receta.getVideo());
        Assertions.assertNotNull(receta.getFechaCreacion());
        Assertions.assertEquals(101L, receta.getIdUsuario());
    }

    @Test
    void testSettersAndGetters() {
        // Crear una instancia vacía
        Receta receta = new Receta();

        // Establecer valores
        LocalDateTime fecha = LocalDateTime.now();
        receta.setId(2L);
        receta.setNombre("Ceviche");
        receta.setTipoCocina("Peruana");
        receta.setIngredientes("Pescado, limón, cebolla");
        receta.setPaisOrigen("Perú");
        receta.setDificultad("Fácil");
        receta.setPopularidad(80);
        receta.setInstruccionesPreparacion("Mezcle todos los ingredientes...");
        receta.setTiempoCoccion(20);
        receta.setPorciones(2);
        receta.setFotografiaUrl("http://imagen.com/ceviche.jpg");
        receta.setVideo("http://video.com/ceviche");
        receta.setFechaCreacion(fecha);
        receta.setIdUsuario(102L);

        // Verificar valores
        Assertions.assertEquals(2L, receta.getId());
        Assertions.assertEquals("Ceviche", receta.getNombre());
        Assertions.assertEquals("Peruana", receta.getTipoCocina());
        Assertions.assertEquals("Pescado, limón, cebolla", receta.getIngredientes());
        Assertions.assertEquals("Perú", receta.getPaisOrigen());
        Assertions.assertEquals("Fácil", receta.getDificultad());
        Assertions.assertEquals(80, receta.getPopularidad());
        Assertions.assertEquals("Mezcle todos los ingredientes...", receta.getInstruccionesPreparacion());
        Assertions.assertEquals(20, receta.getTiempoCoccion());
        Assertions.assertEquals(2, receta.getPorciones());
        Assertions.assertEquals("http://imagen.com/ceviche.jpg", receta.getFotografiaUrl());
        Assertions.assertEquals("http://video.com/ceviche", receta.getVideo());
        Assertions.assertEquals(fecha, receta.getFechaCreacion());
        Assertions.assertEquals(102L, receta.getIdUsuario());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos instancias iguales
        LocalDateTime fecha = LocalDateTime.now();
        Receta receta1 = new Receta(
                1L, "Paella", "Española", "Arroz, azafrán, mariscos",
                "España", "Intermedia", 90, "Cocine los mariscos...",
                45, 4, "http://imagen.com/paella.jpg", "http://video.com/paella",
                fecha, 101L
        );

        Receta receta2 = new Receta(
                1L, "Paella", "Española", "Arroz, azafrán, mariscos",
                "España", "Intermedia", 90, "Cocine los mariscos...",
                45, 4, "http://imagen.com/paella.jpg", "http://video.com/paella",
                fecha, 101L
        );

        // Crear una instancia diferente
        Receta receta3 = new Receta(
                2L, "Ceviche", "Peruana", "Pescado, limón, cebolla",
                "Perú", "Fácil", 80, "Mezcle los ingredientes...",
                20, 2, "http://imagen.com/ceviche.jpg", "http://video.com/ceviche",
                fecha, 102L
        );

        // Verificar igualdad y hashCode
        Assertions.assertEquals(receta1, receta2);
        Assertions.assertNotEquals(receta1, receta3);
        Assertions.assertEquals(receta1.hashCode(), receta2.hashCode());
        Assertions.assertNotEquals(receta1.hashCode(), receta3.hashCode());
    }

    @Test
    void testToString() {
        // Crear una instancia
        LocalDateTime fecha = LocalDateTime.now();
        Receta receta = new Receta(
                1L, "Paella", "Española", "Arroz, azafrán, mariscos",
                "España", "Intermedia", 90, "Cocine los mariscos...",
                45, 4, "http://imagen.com/paella.jpg", "http://video.com/paella",
                fecha, 101L
        );

        // Verificar contenido de toString
        String toStringResult = receta.toString();
        Assertions.assertTrue(toStringResult.contains("Paella"));
        Assertions.assertTrue(toStringResult.contains("Española"));
        Assertions.assertTrue(toStringResult.contains("España"));
        Assertions.assertTrue(toStringResult.contains("Intermedia"));
        Assertions.assertTrue(toStringResult.contains("90"));
        Assertions.assertTrue(toStringResult.contains("101"));
    }
}
