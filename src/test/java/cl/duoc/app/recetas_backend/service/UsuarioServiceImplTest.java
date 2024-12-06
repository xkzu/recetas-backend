package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Usuario;
import cl.duoc.app.recetas_backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Configurar un usuario simulado
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreUsuario("testUser");
        usuario.setContrasena("testPassword");

        // Simular comportamiento del repositorio
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Llamar al método y validar el resultado
        Usuario resultado = usuarioService.register(usuario);

        assertEquals(1L, resultado.getId());
        assertEquals("testUser", resultado.getNombreUsuario());
        assertEquals("testPassword", resultado.getContrasena());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testFindByNombreUsuario() {
        // Configurar un usuario simulado
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreUsuario("testUser");

        // Simular comportamiento del repositorio
        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));

        // Llamar al método y validar el resultado
        Optional<Usuario> resultado = usuarioService.findByNombreUsuario("testUser");

        assertTrue(resultado.isPresent());
        assertEquals("testUser", resultado.get().getNombreUsuario());
        verify(usuarioRepository, times(1)).findByNombreUsuario("testUser");
    }

    @Test
    void testFindByNombreUsuarioNotFound() {
        // Simular que no se encuentra el usuario
        when(usuarioRepository.findByNombreUsuario("unknownUser")).thenReturn(Optional.empty());

        // Llamar al método y validar el resultado
        Optional<Usuario> resultado = usuarioService.findByNombreUsuario("unknownUser");

        assertFalse(resultado.isPresent());
        verify(usuarioRepository, times(1)).findByNombreUsuario("unknownUser");
    }
}
