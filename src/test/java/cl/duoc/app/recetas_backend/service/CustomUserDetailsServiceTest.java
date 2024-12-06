package cl.duoc.app.recetas_backend.service;

import cl.duoc.app.recetas_backend.model.Usuario;
import cl.duoc.app.recetas_backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        // Configurar un usuario simulado
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("testUser");
        usuario.setContrasena("testPassword");

        // Simular el comportamiento del repositorio
        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));

        // Llamar al método y validar el resultado
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
        verify(usuarioRepository, times(1)).findByNombreUsuario("testUser");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Simular que el usuario no existe en el repositorio
        when(usuarioRepository.findByNombreUsuario("unknownUser")).thenReturn(Optional.empty());

        // Llamar al método y esperar una excepción
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("unknownUser"));

        // Verificar que se haya llamado al método del repositorio
        verify(usuarioRepository, times(1)).findByNombreUsuario("unknownUser");
    }
}
