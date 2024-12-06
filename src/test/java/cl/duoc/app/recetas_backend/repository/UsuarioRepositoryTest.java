package cl.duoc.app.recetas_backend.repository;

import cl.duoc.app.recetas_backend.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByNombreUsuario() {
        // Datos de prueba
        String nombreUsuario = "usuarioTest";
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena("1234");

        // Simulación del repositorio
        when(usuarioRepository.findByNombreUsuario(nombreUsuario)).thenReturn(Optional.of(usuario));

        // Ejecución
        Optional<Usuario> result = usuarioRepository.findByNombreUsuario(nombreUsuario);

        // Validaciones
        assertTrue(result.isPresent());
        assertEquals(nombreUsuario, result.get().getNombreUsuario());
    }

    @Test
    void testLogin() {
        // Datos de prueba
        String nombreUsuario = "usuarioTest";
        String contrasena = "1234";
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasena(contrasena);

        // Simulación del repositorio
        when(usuarioRepository.login(nombreUsuario, contrasena)).thenReturn(Optional.of(usuario));

        // Ejecución
        Optional<Usuario> result = usuarioRepository.login(nombreUsuario, contrasena);

        // Validaciones
        assertTrue(result.isPresent());
        assertEquals(nombreUsuario, result.get().getNombreUsuario());
        assertEquals(contrasena, result.get().getContrasena());
    }

    @Test
    void testLoginInvalidCredentials() {
        // Datos de prueba
        String nombreUsuario = "usuarioTest";
        String contrasena = "incorrecta";

        // Simulación del repositorio
        when(usuarioRepository.login(nombreUsuario, contrasena)).thenReturn(Optional.empty());

        // Ejecución
        Optional<Usuario> result = usuarioRepository.login(nombreUsuario, contrasena);

        // Validaciones
        assertTrue(result.isEmpty());
    }
}
