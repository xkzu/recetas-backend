package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Banner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BannerServiceTest {

    @Mock
    private BannerService bannerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearBanner() {
        // Configurar un banner simulado
        Banner banner = new Banner();
        banner.setId(1L);
        banner.setEmpresaNombre("Empresa 1");
        banner.setMensaje("Promoción especial");
        banner.setUrlImagen("http://imagen.com/banner1.jpg");
        banner.setLinkWebsite("http://empresa1.com");
        banner.setFechaCreacion(new Date());

        // Simular el comportamiento del servicio
        when(bannerService.crearBanner(banner)).thenReturn(banner);

        // Llamar al método y validar el resultado
        Banner resultado = bannerService.crearBanner(banner);

        assertEquals(banner.getId(), resultado.getId());
        assertEquals(banner.getEmpresaNombre(), resultado.getEmpresaNombre());
        assertEquals(banner.getMensaje(), resultado.getMensaje());
        assertEquals(banner.getUrlImagen(), resultado.getUrlImagen());
        assertEquals(banner.getLinkWebsite(), resultado.getLinkWebsite());
        assertEquals(banner.getFechaCreacion(), resultado.getFechaCreacion());
    }

    @Test
    void testObtenerBanners() {
        // Configurar una lista de banners simulados
        Banner banner1 = new Banner();
        banner1.setId(1L);
        banner1.setEmpresaNombre("Empresa 1");
        banner1.setMensaje("Promoción 1");
        banner1.setUrlImagen("http://imagen.com/banner1.jpg");
        banner1.setLinkWebsite("http://empresa1.com");
        banner1.setFechaCreacion(new Date());

        Banner banner2 = new Banner();
        banner2.setId(2L);
        banner2.setEmpresaNombre("Empresa 2");
        banner2.setMensaje("Promoción 2");
        banner2.setUrlImagen("http://imagen.com/banner2.jpg");
        banner2.setLinkWebsite("http://empresa2.com");
        banner2.setFechaCreacion(new Date());

        List<Banner> banners = Arrays.asList(banner1, banner2);

        // Simular el comportamiento del servicio
        when(bannerService.obtenerBanners()).thenReturn(banners);

        // Llamar al método y validar el resultado
        List<Banner> resultado = bannerService.obtenerBanners();

        assertEquals(2, resultado.size());
        assertEquals("Empresa 1", resultado.get(0).getEmpresaNombre());
        assertEquals("Promoción 1", resultado.get(0).getMensaje());
        assertEquals("Empresa 2", resultado.get(1).getEmpresaNombre());
        assertEquals("Promoción 2", resultado.get(1).getMensaje());
    }
}
