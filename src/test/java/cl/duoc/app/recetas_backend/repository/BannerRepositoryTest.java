package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Banner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BannerRepositoryTest {

    @Mock
    private BannerRepository bannerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBanner() {
        // Crear un Banner de prueba
        Banner banner = new Banner();
        banner.setEmpresaNombre("Empresa X");
        banner.setMensaje("Mensaje de prueba");
        banner.setUrlImagen("http://imagen.com/banner.jpg");
        banner.setLinkWebsite("http://empresa.com");

        // Mockear el comportamiento del repositorio
        Banner savedBanner = new Banner();
        savedBanner.setId(1L);
        savedBanner.setEmpresaNombre("Empresa X");
        savedBanner.setMensaje("Mensaje de prueba");
        savedBanner.setUrlImagen("http://imagen.com/banner.jpg");
        savedBanner.setLinkWebsite("http://empresa.com");

        when(bannerRepository.save(banner)).thenReturn(savedBanner);

        // Llamar al método
        Banner result = bannerRepository.save(banner);

        // Verificar que el resultado es el esperado
        assertNotNull(result.getId());
        assertEquals("Empresa X", result.getEmpresaNombre());
        verify(bannerRepository, times(1)).save(banner);
    }

    @Test
    void testFindById() {
        // Crear un Banner de prueba
        Banner banner = new Banner();
        banner.setId(1L);
        banner.setEmpresaNombre("Empresa Y");
        banner.setMensaje("Otro mensaje");
        banner.setUrlImagen("http://imagen.com/otro.jpg");
        banner.setLinkWebsite("http://empresaY.com");

        // Mockear el comportamiento del repositorio
        when(bannerRepository.findById(1L)).thenReturn(Optional.of(banner));

        // Llamar al método
        Optional<Banner> retrievedBanner = bannerRepository.findById(1L);

        // Verificar que se recuperó correctamente
        assertTrue(retrievedBanner.isPresent());
        assertEquals("Empresa Y", retrievedBanner.get().getEmpresaNombre());
        verify(bannerRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteBanner() {
        // Crear un Banner de prueba
        Banner banner = new Banner();
        banner.setId(1L);
        banner.setEmpresaNombre("Empresa Z");
        banner.setMensaje("Mensaje para eliminar");
        banner.setUrlImagen("http://imagen.com/eliminar.jpg");
        banner.setLinkWebsite("http://empresaZ.com");

        // Mockear el comportamiento del repositorio
        doNothing().when(bannerRepository).delete(banner);

        // Llamar al método
        bannerRepository.delete(banner);

        // Verificar que el método fue llamado
        verify(bannerRepository, times(1)).delete(banner);
    }
}
