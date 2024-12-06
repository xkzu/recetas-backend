package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Receta;
import cl.duoc.app.recetas_backend.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecetaServiceImplTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaServiceImpl recetaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Configurar recetas simuladas
        Receta receta1 = new Receta();
        Receta receta2 = new Receta();

        List<Receta> recetas = Arrays.asList(receta1, receta2);

        // Simular comportamiento del repositorio
        when(recetaRepository.findAll()).thenReturn(recetas);

        // Llamar al método y validar el resultado
        List<Receta> resultado = recetaService.findAll();

        assertEquals(2, resultado.size());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Configurar una receta simulada
        Receta receta = new Receta();
        receta.setId(1L);

        // Simular comportamiento del repositorio
        when(recetaRepository.findById(1L)).thenReturn(Optional.of(receta));

        // Llamar al método y validar el resultado
        Optional<Receta> resultado = recetaService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(recetaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByName() {
        // Configurar una receta simulada
        Receta receta = new Receta();
        receta.setNombre("Paella");

        // Simular comportamiento del repositorio
        when(recetaRepository.findByNombre("Paella")).thenReturn(Optional.of(receta));

        // Llamar al método y validar el resultado
        Optional<Receta> resultado = recetaService.findByName("Paella");

        assertTrue(resultado.isPresent());
        assertEquals("Paella", resultado.get().getNombre());
        verify(recetaRepository, times(1)).findByNombre("Paella");
    }

    @Test
    void testFindByCountry() {
        // Configurar recetas simuladas
        Receta receta1 = new Receta();
        receta1.setPaisOrigen("España");
        Receta receta2 = new Receta();
        receta2.setPaisOrigen("España");

        List<Receta> recetas = Arrays.asList(receta1, receta2);

        // Simular comportamiento del repositorio
        when(recetaRepository.findByPaisOrigen("España")).thenReturn(recetas);

        // Llamar al método y validar el resultado
        List<Receta> resultado = recetaService.findByCountry("España");

        assertEquals(2, resultado.size());
        verify(recetaRepository, times(1)).findByPaisOrigen("España");
    }

    @Test
    void testObtenerRecetasMasRecientes() {
        // Configurar recetas simuladas
        Receta receta1 = new Receta();
        Receta receta2 = new Receta();

        List<Receta> recetas = Arrays.asList(receta1, receta2);

        // Simular comportamiento del repositorio
        when(recetaRepository.findRecientes(2)).thenReturn(recetas);

        // Llamar al método y validar el resultado
        List<Receta> resultado = recetaService.obtenerRecetasMasRecientes(2);

        assertEquals(2, resultado.size());
        verify(recetaRepository, times(1)).findRecientes(2);
    }

    @Test
    void testObtenerRecetasMasPopulares() {
        // Configurar recetas simuladas
        Receta receta1 = new Receta();
        Receta receta2 = new Receta();

        List<Receta> recetas = Arrays.asList(receta1, receta2);

        // Simular comportamiento del repositorio
        when(recetaRepository.findAllByOrderByPopularidadDesc(PageRequest.of(0, 2))).thenReturn(recetas);

        // Llamar al método y validar el resultado
        List<Receta> resultado = recetaService.obtenerRecetasMasPopulares(2);

        assertEquals(2, resultado.size());
        verify(recetaRepository, times(1)).findAllByOrderByPopularidadDesc(PageRequest.of(0, 2));
    }

    @Test
    void testBuscarRecetas() {
        // Configurar una receta simulada
        Receta receta = new Receta();
        receta.setNombre("Paella");

        List<Receta> recetas = List.of(receta);

        // Simular comportamiento del repositorio
        when(recetaRepository.buscarRecetas("Paella", null, null, null, null)).thenReturn(recetas);

        // Llamar al método y validar el resultado
        List<Receta> resultado = recetaService.buscarRecetas("Paella", null, null, null, null);

        assertEquals(1, resultado.size());
        assertEquals("Paella", resultado.get(0).getNombre());
        verify(recetaRepository, times(1)).buscarRecetas("Paella", null, null, null, null);
    }

    @Test
    void testRegister() {
        // Configurar una receta simulada
        Receta receta = new Receta();
        receta.setNombre("Paella");

        // Simular comportamiento del repositorio
        when(recetaRepository.save(receta)).thenReturn(receta);

        // Llamar al método y validar el resultado
        Receta resultado = recetaService.register(receta);

        assertEquals("Paella", resultado.getNombre());
        verify(recetaRepository, times(1)).save(receta);
    }
}
