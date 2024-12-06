package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Valoracion;
import cl.duoc.app.recetas_backend.repository.ValoracionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValoracionServiceImplTest {

    @Mock
    private ValoracionRepository valoracionRepository;

    @InjectMocks
    private ValoracionServiceImpl valoracionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Configurar una valoración simulada
        Valoracion valoracion = new Valoracion();
        valoracion.setId(1L);
        valoracion.setIdReceta(100L);
        valoracion.setIdUsuario(10L);
        valoracion.setPuntaje(5);

        // Simular el comportamiento del repositorio
        when(valoracionRepository.save(valoracion)).thenReturn(valoracion);

        // Llamar al método y validar el resultado
        Valoracion resultado = valoracionService.save(valoracion);

        assertEquals(1L, resultado.getId());
        assertEquals(100L, resultado.getIdReceta());
        assertEquals(10L, resultado.getIdUsuario());
        assertEquals(5, resultado.getPuntaje());
        verify(valoracionRepository, times(1)).save(valoracion);
    }

    @Test
    void testFindAll() {
        // Configurar valoraciones simuladas
        Valoracion valoracion1 = new Valoracion();
        valoracion1.setId(1L);
        valoracion1.setIdReceta(100L);
        valoracion1.setIdUsuario(10L);
        valoracion1.setPuntaje(4);

        Valoracion valoracion2 = new Valoracion();
        valoracion2.setId(2L);
        valoracion2.setIdReceta(100L);
        valoracion2.setIdUsuario(11L);
        valoracion2.setPuntaje(5);

        List<Valoracion> valoraciones = Arrays.asList(valoracion1, valoracion2);

        // Simular el comportamiento del repositorio
        when(valoracionRepository.findByIdReceta(100L)).thenReturn(valoraciones);

        // Llamar al método y validar el resultado
        List<Valoracion> resultado = valoracionService.findAll(100L);

        assertEquals(2, resultado.size());
        assertEquals(4, resultado.get(0).getPuntaje());
        assertEquals(5, resultado.get(1).getPuntaje());
        verify(valoracionRepository, times(1)).findByIdReceta(100L);
    }
}
