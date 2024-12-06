package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Valoracion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ValoracionRepositoryTest {

    @Mock
    private ValoracionRepository valoracionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdReceta() {
        // Datos de prueba
        Long idReceta = 1L;
        Valoracion valoracion1 = new Valoracion();
        valoracion1.setId(1L);
        valoracion1.setIdReceta(idReceta);
        valoracion1.setPuntaje(5);

        Valoracion valoracion2 = new Valoracion();
        valoracion2.setId(2L);
        valoracion2.setIdReceta(idReceta);
        valoracion2.setPuntaje(4);

        List<Valoracion> valoraciones = Arrays.asList(valoracion1, valoracion2);

        // Simulación del repositorio
        when(valoracionRepository.findByIdReceta(idReceta)).thenReturn(valoraciones);

        // Ejecución
        List<Valoracion> result = valoracionRepository.findByIdReceta(idReceta);

        // Validaciones
        assertEquals(2, result.size());
        assertEquals(idReceta, result.get(0).getIdReceta());
        assertEquals(idReceta, result.get(1).getIdReceta());
        assertEquals(5, result.get(0).getPuntaje());
        assertEquals(4, result.get(1).getPuntaje());
    }

    @Test
    void testFindByIdRecetaEmptyResult() {
        // Datos de prueba
        Long idReceta = 2L;

        // Simulación del repositorio
        when(valoracionRepository.findByIdReceta(idReceta)).thenReturn(List.of());

        // Ejecución
        List<Valoracion> result = valoracionRepository.findByIdReceta(idReceta);

        // Validaciones
        assertTrue(result.isEmpty());
    }
}
