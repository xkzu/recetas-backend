package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Usuario;
import cl.duoc.app.recetas_backend.request.UsuarioReq;
import cl.duoc.app.recetas_backend.response.LoginResponse;
import cl.duoc.app.recetas_backend.service.UsuarioService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        UsuarioReq userReq = new UsuarioReq("testUser", "password123");
        Usuario usuario = new Usuario(1L, "testUser", "Nombre Completo", "email@test.com", "encryptedPassword", true);

        when(usuarioService.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "encryptedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("testUser")).thenReturn("jwt-token");

        ResponseEntity<LoginResponse> response = usuarioController.login(userReq);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().getToken());
        verify(usuarioService).findByNombreUsuario("testUser");
        verify(passwordEncoder).matches("password123", "encryptedPassword");
        verify(jwtUtil).generateToken("testUser");
    }

    @Test
    void testLogin_Failure_WrongPassword() {
        UsuarioReq userReq = new UsuarioReq("testUser", "wrongPassword");
        Usuario usuario = new Usuario(1L, "testUser", "Nombre Completo", "email@test.com", "encryptedPassword", true);

        when(usuarioService.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrongPassword", "encryptedPassword")).thenReturn(false);

        ResponseEntity<LoginResponse> response = usuarioController.login(userReq);

        assertEquals(401, response.getStatusCodeValue());
        verify(usuarioService).findByNombreUsuario("testUser");
        verify(passwordEncoder).matches("wrongPassword", "encryptedPassword");
        verifyNoInteractions(jwtUtil);
    }

    @Test
    void testLogin_Failure_UserNotFound() {
        UsuarioReq userReq = new UsuarioReq("testUser", "password123");

        when(usuarioService.findByNombreUsuario("testUser")).thenReturn(Optional.empty());

        ResponseEntity<LoginResponse> response = usuarioController.login(userReq);

        assertEquals(401, response.getStatusCodeValue());
        verify(usuarioService).findByNombreUsuario("testUser");
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtUtil);
    }

    @Test
    void testRegister_Success() {
        Usuario usuario = new Usuario(null, "testUser", "Nombre Completo", "email@test.com", "password123", true);
        Usuario savedUsuario = new Usuario(1L, "testUser", "Nombre Completo", "email@test.com", "encryptedPassword", true);

        when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");
        when(usuarioService.register(usuario)).thenReturn(savedUsuario);

        ResponseEntity<Usuario> response = usuarioController.register(usuario);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(savedUsuario, response.getBody());
        verify(passwordEncoder).encode("password123");
        verify(usuarioService).register(usuario);
    }

    @Test
    void testRegister_Failure_BadRequest() {
        Usuario usuario = new Usuario(null, "", "Nombre Completo", "email@test.com", "password123", true);

        ResponseEntity<Usuario> response = usuarioController.register(usuario);

        assertEquals(400, response.getStatusCodeValue());
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(usuarioService);
    }

    @Test
    void testRegister_InternalServerError() {
        Usuario usuario = new Usuario(null, "testUser", "Nombre Completo", "email@test.com", "password123", true);

        when(passwordEncoder.encode("password123")).thenThrow(new RuntimeException("Error interno"));

        ResponseEntity<Usuario> response = usuarioController.register(usuario);

        assertEquals(500, response.getStatusCodeValue());
        verify(passwordEncoder).encode("password123");
        verifyNoInteractions(usuarioService);
    }

    @Test
    void testLogin_Success_WithEncodedPassword() {
        UsuarioReq userReq = new UsuarioReq("testUser", "password123");
        Usuario usuario = new Usuario(1L, "testUser", "Nombre Completo", "email@test.com", "encryptedPassword", true);

        when(usuarioService.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "encryptedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("testUser")).thenReturn("jwt-token");

        ResponseEntity<LoginResponse> response = usuarioController.login(userReq);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().getToken());
        verify(usuarioService).findByNombreUsuario("testUser");
        verify(passwordEncoder).matches("password123", "encryptedPassword");
        verify(jwtUtil).generateToken("testUser");
    }

    @Test
    void testLogin_Success_WithPlainPassword() {
        UsuarioReq userReq = new UsuarioReq("testUser", "password123");
        Usuario usuario = new Usuario(1L, "testUser", "Nombre Completo", "email@test.com", "password123", true);

        when(usuarioService.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "password123")).thenReturn(false); // Simula que no se usa encoding
        when(jwtUtil.generateToken("testUser")).thenReturn("jwt-token");

        ResponseEntity<LoginResponse> response = usuarioController.login(userReq);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("jwt-token", response.getBody().getToken());
        verify(usuarioService).findByNombreUsuario("testUser");
        verify(passwordEncoder).matches("password123", "password123");
        verify(jwtUtil).generateToken("testUser");
    }

    @Test
    void testLogin_Failure_InvalidPassword() {
        // Arrange
        UsuarioReq userReq = new UsuarioReq("testUser", "wrongPassword");
        Usuario usuario = new Usuario(1L, "testUser", "Nombre Completo", "email@test.com", "encryptedPassword", true);

        when(usuarioService.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrongPassword", "encryptedPassword")).thenReturn(false); // Simula que la contraseña no coincide

        // Act
        ResponseEntity<LoginResponse> response = usuarioController.login(userReq);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        verify(usuarioService).findByNombreUsuario("testUser");
        verify(passwordEncoder).matches("wrongPassword", "encryptedPassword");
        verifyNoInteractions(jwtUtil);
    }

}
