package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Banner;
import cl.duoc.app.recetas_backend.service.BannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BannerControllerTest {

    @Mock
    private BannerService bannerService;

    @InjectMocks
    private BannerController bannerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearBanner() {
        // Arrange
        Banner banner = new Banner();
        banner.setEmpresaNombre("Empresa Test");
        banner.setMensaje("Mensaje Test");
        Banner savedBanner = new Banner();
        savedBanner.setId(1L);
        savedBanner.setEmpresaNombre("Empresa Test");
        savedBanner.setMensaje("Mensaje Test");

        when(bannerService.crearBanner(banner)).thenReturn(savedBanner);

        // Act
        ResponseEntity<Banner> response = bannerController.crearBanner(banner);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(savedBanner, response.getBody());
        verify(bannerService, times(1)).crearBanner(banner);
    }

    @Test
    void testObtenerBanners() {
        // Arrange
        Banner banner1 = new Banner();
        banner1.setId(1L);
        banner1.setEmpresaNombre("Empresa Test 1");
        banner1.setMensaje("Mensaje Test 1");

        Banner banner2 = new Banner();
        banner2.setId(2L);
        banner2.setEmpresaNombre("Empresa Test 2");
        banner2.setMensaje("Mensaje Test 2");

        List<Banner> banners = Arrays.asList(banner1, banner2);

        when(bannerService.obtenerBanners()).thenReturn(banners);

        // Act
        ResponseEntity<List<Banner>> response = bannerController.obtenerBanners();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(banners, response.getBody());
        verify(bannerService, times(1)).obtenerBanners();
    }
}
