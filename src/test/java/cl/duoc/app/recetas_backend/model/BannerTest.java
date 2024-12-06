package cl.duoc.app.recetas_backend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class BannerTest {

    @Test
    void testBannerCreation() {
        // Fecha simulada
        Date fecha = new Date();

        // Crear instancia de Banner
        Banner banner = new Banner(1L, "Empresa X", "Promoción especial", "http://imagen.com/img.jpg", "http://empresa.com", fecha);

        // Verificar propiedades
        Assertions.assertEquals(1L, banner.getId());
        Assertions.assertEquals("Empresa X", banner.getEmpresaNombre());
        Assertions.assertEquals("Promoción especial", banner.getMensaje());
        Assertions.assertEquals("http://imagen.com/img.jpg", banner.getUrlImagen());
        Assertions.assertEquals("http://empresa.com", banner.getLinkWebsite());
        Assertions.assertEquals(fecha, banner.getFechaCreacion());
    }

    @Test
    void testDefaultFechaCreacion() {
        // Crear instancia sin especificar fecha
        Banner banner = new Banner();

        // Verificar que la fecha de creación no es nula
        Assertions.assertNotNull(banner.getFechaCreacion());
    }

    @Test
    void testSettersAndGetters() {
        // Crear instancia vacía
        Banner banner = new Banner();

        // Establecer valores
        Date fecha = new Date();
        banner.setId(2L);
        banner.setEmpresaNombre("Empresa Y");
        banner.setMensaje("Oferta exclusiva");
        banner.setUrlImagen("http://imagen.com/oferta.jpg");
        banner.setLinkWebsite("http://empresaY.com");
        banner.setFechaCreacion(fecha);

        // Verificar valores
        Assertions.assertEquals(2L, banner.getId());
        Assertions.assertEquals("Empresa Y", banner.getEmpresaNombre());
        Assertions.assertEquals("Oferta exclusiva", banner.getMensaje());
        Assertions.assertEquals("http://imagen.com/oferta.jpg", banner.getUrlImagen());
        Assertions.assertEquals("http://empresaY.com", banner.getLinkWebsite());
        Assertions.assertEquals(fecha, banner.getFechaCreacion());
    }

    @Test
    void testEqualsAndHashCode() {
        // Crear dos instancias iguales
        Date fecha = new Date();
        Banner banner1 = new Banner(1L, "Empresa X", "Mensaje X", "http://imagen.com", "http://empresa.com", fecha);
        Banner banner2 = new Banner(1L, "Empresa X", "Mensaje X", "http://imagen.com", "http://empresa.com", fecha);

        // Crear una instancia diferente
        Banner banner3 = new Banner(2L, "Empresa Y", "Mensaje Y", "http://imagenY.com", "http://empresaY.com", fecha);

        // Verificar igualdad
        Assertions.assertEquals(banner1, banner2);
        Assertions.assertNotEquals(banner1, banner3);

        // Verificar hashCode
        Assertions.assertEquals(banner1.hashCode(), banner2.hashCode());
        Assertions.assertNotEquals(banner1.hashCode(), banner3.hashCode());
    }

    @Test
    void testToString() {
        // Crear instancia
        Date fecha = new Date();
        Banner banner = new Banner(1L, "Empresa X", "Mensaje X", "http://imagen.com", "http://empresa.com", fecha);

        // Verificar contenido del toString
        String toStringResult = banner.toString();
        Assertions.assertTrue(toStringResult.contains("Empresa X"));
        Assertions.assertTrue(toStringResult.contains("Mensaje X"));
        Assertions.assertTrue(toStringResult.contains("http://imagen.com"));
        Assertions.assertTrue(toStringResult.contains("http://empresa.com"));
    }
}
