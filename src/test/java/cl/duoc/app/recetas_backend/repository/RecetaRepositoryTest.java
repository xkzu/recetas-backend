package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RecetaRepositoryTest {

    @Mock
    private RecetaRepository recetaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByNombre() {
        Receta receta = new Receta();
        receta.setNombre("Paella");

        when(recetaRepository.findByNombre("Paella")).thenReturn(Optional.of(receta));

        Optional<Receta> result = recetaRepository.findByNombre("Paella");

        assertEquals("Paella", result.get().getNombre());
    }

    @Test
    void testFindByPaisOrigen() {
        Receta receta1 = new Receta();
        receta1.setPaisOrigen("España");
        Receta receta2 = new Receta();
        receta2.setPaisOrigen("España");

        when(recetaRepository.findByPaisOrigen("España")).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaRepository.findByPaisOrigen("España");

        assertEquals(2, result.size());
    }

    @Test
    void testFindAllByOrderByFechaCreacionDesc() {
        Receta receta1 = new Receta();
        Receta receta2 = new Receta();

        Pageable pageable = PageRequest.of(0, 2);

        when(recetaRepository.findAllByOrderByFechaCreacionDesc(pageable)).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaRepository.findAllByOrderByFechaCreacionDesc(pageable);

        assertEquals(2, result.size());
    }

    @Test
    void testFindRecientes() {
        // Configurar recetas simuladas
        Receta receta1 = new Receta();
        receta1.setNombre("Receta 1");
        Receta receta2 = new Receta();
        receta2.setNombre("Receta 2");

        // Simular el comportamiento del método findAllByOrderByFechaCreacionDesc
        when(recetaRepository.findAllByOrderByFechaCreacionDesc(PageRequest.of(0, 2)))
                .thenReturn(Arrays.asList(receta1, receta2));

        // Configurar el comportamiento del método findRecientes llamando al método predeterminado
        when(recetaRepository.findRecientes(2)).thenCallRealMethod();

        // Llamar al método findRecientes
        List<Receta> result = recetaRepository.findRecientes(2);

        // Verificar que el resultado tiene el tamaño esperado y los elementos correctos
        assertEquals(2, result.size());
        assertEquals("Receta 1", result.get(0).getNombre());
        assertEquals("Receta 2", result.get(1).getNombre());
    }



    @Test
    void testFindAllByOrderByPopularidadDesc() {
        Receta receta1 = new Receta();
        Receta receta2 = new Receta();

        Pageable pageable = PageRequest.of(0, 2);

        when(recetaRepository.findAllByOrderByPopularidadDesc(pageable)).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaRepository.findAllByOrderByPopularidadDesc(pageable);

        assertEquals(2, result.size());
    }

    @Test
    void testBuscarRecetas() {
        Receta receta = new Receta();
        receta.setNombre("Paella");
        receta.setTipoCocina("Española");
        receta.setIngredientes("Arroz, Mariscos");
        receta.setPaisOrigen("España");
        receta.setDificultad("Media");

        // Simulación exacta con parámetros específicos
        when(recetaRepository.buscarRecetas("Paella", null, null, null, null))
                .thenReturn(List.of(receta));

        List<Receta> result = recetaRepository.buscarRecetas("Paella", null, null, null, null);

        assertEquals(1, result.size());
        assertEquals("Paella", result.get(0).getNombre());
    }

}
