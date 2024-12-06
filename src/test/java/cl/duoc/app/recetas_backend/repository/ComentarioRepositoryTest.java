package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Comentario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComentarioRepositoryTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdRecetaAndVisible() {
        // Configurar datos de prueba
        Comentario comentario1 = new Comentario(1L, 1L, 1L, "Comentario 1", LocalDateTime.now(), true);
        Comentario comentario2 = new Comentario(2L, 1L, 1L, "Comentario 2", LocalDateTime.now(), true);

        // Mockear comportamiento
        when(comentarioRepository.findByIdRecetaAndVisible(1L, true))
                .thenReturn(Arrays.asList(comentario1, comentario2));

        // Llamar al método
        List<Comentario> comentarios = comentarioRepository.findByIdRecetaAndVisible(1L, true);

        // Validar resultados
        assertEquals(2, comentarios.size());
        assertTrue(comentarios.stream().allMatch(Comentario::isVisible));
        assertEquals("Comentario 1", comentarios.get(0).getContenido());
    }

    @Test
    void testActualizarVisibilidad() {
        // Configurar datos de prueba
        Comentario comentario = new Comentario(1L, 1L, 1L, "Comentario inicial", LocalDateTime.now(), true);

        // Mockear comportamiento
        doNothing().when(comentarioRepository).actualizarVisibilidad(1L, false);
        when(comentarioRepository.findById(1L)).thenReturn(Optional.of(comentario));

        // Llamar al método simulado
        comentarioRepository.actualizarVisibilidad(1L, false);

        // Validar que el método fue llamado correctamente
        verify(comentarioRepository, times(1)).actualizarVisibilidad(1L, false);
    }
}
