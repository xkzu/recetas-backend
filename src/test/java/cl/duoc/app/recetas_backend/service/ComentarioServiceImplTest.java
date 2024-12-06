package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Comentario;
import cl.duoc.app.recetas_backend.repository.ComentarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComentarioServiceImplTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioServiceImpl comentarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Configurar un comentario simulado
        Comentario comentario = new Comentario();
        comentario.setId(1L);
        comentario.setContenido("Comentario de prueba");

        // Simular el comportamiento del repositorio
        when(comentarioRepository.save(comentario)).thenReturn(comentario);

        // Llamar al método y validar el resultado
        Comentario resultado = comentarioService.save(comentario);

        assertEquals(comentario.getId(), resultado.getId());
        assertEquals(comentario.getContenido(), resultado.getContenido());

        // Verificar que se haya llamado al método `save`
        verify(comentarioRepository, times(1)).save(comentario);
    }

    @Test
    void testGetAll() {
        // Configurar comentarios simulados
        Comentario comentario1 = new Comentario();
        comentario1.setId(1L);
        comentario1.setContenido("Comentario 1");

        Comentario comentario2 = new Comentario();
        comentario2.setId(2L);
        comentario2.setContenido("Comentario 2");

        List<Comentario> comentarios = Arrays.asList(comentario1, comentario2);

        // Simular el comportamiento del repositorio
        when(comentarioRepository.findAll()).thenReturn(comentarios);

        // Llamar al método y validar el resultado
        List<Comentario> resultado = comentarioService.getAll();

        assertEquals(2, resultado.size());
        assertEquals("Comentario 1", resultado.get(0).getContenido());
        assertEquals("Comentario 2", resultado.get(1).getContenido());

        // Verificar que se haya llamado al método `findAll`
        verify(comentarioRepository, times(1)).findAll();
    }

    @Test
    void testGetAllByIdReceta() {
        // Configurar comentarios simulados
        Comentario comentario1 = new Comentario();
        comentario1.setId(1L);
        comentario1.setContenido("Comentario visible");
        comentario1.setVisible(true);

        List<Comentario> comentarios = List.of(comentario1);

        // Simular el comportamiento del repositorio
        when(comentarioRepository.findByIdRecetaAndVisible(1L, true)).thenReturn(comentarios);

        // Llamar al método y validar el resultado
        List<Comentario> resultado = comentarioService.getAllByIdReceta(1L, true);

        assertEquals(1, resultado.size());
        assertEquals("Comentario visible", resultado.get(0).getContenido());

        // Verificar que se haya llamado al método `findByIdRecetaAndVisible`
        verify(comentarioRepository, times(1)).findByIdRecetaAndVisible(1L, true);
    }

    @Test
    void testAprobarComentario() {
        // Configurar un comentario simulado
        Long comentarioId = 1L;
        boolean visible = true;

        // No se espera retorno, solo verificar que el método sea llamado
        doNothing().when(comentarioRepository).actualizarVisibilidad(comentarioId, visible);

        // Llamar al método
        comentarioService.aprobarComentario(comentarioId, visible);

        // Verificar que se haya llamado al método `actualizarVisibilidad`
        verify(comentarioRepository, times(1)).actualizarVisibilidad(comentarioId, visible);
    }
}
