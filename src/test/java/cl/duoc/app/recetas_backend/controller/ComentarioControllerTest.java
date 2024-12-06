package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Comentario;
import cl.duoc.app.recetas_backend.service.ComentarioService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ComentarioControllerTest {

    @Mock
    private ComentarioService comentarioService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private ComentarioController comentarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllComentarios_ValidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";
            Long id = 1L;
            List<Comentario> comentarios = List.of(new Comentario(), new Comentario());

            // Configuración del mock para que el token sea válido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(comentarioService.getAllByIdReceta(id, true)).thenReturn(comentarios);

            // Act
            ResponseEntity<List<Comentario>> response = comentarioController.getAll(id, token);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(comentarios, response.getBody());
            verify(comentarioService, times(1)).getAllByIdReceta(id, true);
        }
    }

    @Test
    void testGetAllComentarios_InvalidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "invalid_token";
            Long id = 1L;

            // Configuración del mock para que el token sea inválido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(false);

            // Act
            ResponseEntity<List<Comentario>> response = comentarioController.getAll(id, token);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            verify(comentarioService, never()).getAllByIdReceta(id, true);
        }
    }

    @Test
    void testGetAllComentarios_Exception() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";
            Long id = 1L;

            // Configuración del mock para que el token sea válido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            // Simula una excepción al obtener los comentarios
            when(comentarioService.getAllByIdReceta(id, true)).thenThrow(new RuntimeException("Error al obtener comentarios"));

            // Act
            ResponseEntity<List<Comentario>> response = comentarioController.getAll(id, token);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertNull(response.getBody());
            verify(comentarioService, times(1)).getAllByIdReceta(id, true);
        }
    }


    @Test
    void testAddComentario_ValidToken() {
        // Mock del método estático de Util
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";
            Comentario comentario = new Comentario();
            Comentario savedComentario = new Comentario();

            // Configuración del mock para validar el token
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(comentarioService.save(comentario)).thenReturn(savedComentario);

            // Act
            ResponseEntity<Comentario> response = comentarioController.add(comentario, token);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(savedComentario, response.getBody());
            verify(comentarioService, times(1)).save(comentario);
        }
    }

    @Test
    void testAddComentario_InvalidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "invalid_token";
            Comentario comentario = new Comentario();

            // Configuración del mock para que el token sea inválido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(false);

            // Act
            ResponseEntity<Comentario> response = comentarioController.add(comentario, token);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            verify(comentarioService, never()).save(comentario);
        }
    }

    @Test
    void testAddComentario_Exception() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";
            Comentario comentario = new Comentario();

            // Configuración del mock para que el token sea válido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            // Simula una excepción al guardar el comentario
            when(comentarioService.save(comentario)).thenThrow(new RuntimeException("Error al guardar"));

            // Act
            ResponseEntity<Comentario> response = comentarioController.add(comentario, token);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertEquals(comentario, response.getBody());
            verify(comentarioService, times(1)).save(comentario);
        }
    }

    @Test
    void testGetAllAdmin_ValidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";
            List<Comentario> comentarios = List.of(new Comentario(), new Comentario());

            // Configuración del mock para que el token sea válido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(comentarioService.getAll()).thenReturn(comentarios);

            // Act
            ResponseEntity<List<Comentario>> response = comentarioController.getAllAdmin(token);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(comentarios, response.getBody());
            verify(comentarioService, times(1)).getAll();
        }
    }

    @Test
    void testGetAllAdmin_InvalidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "invalid_token";

            // Configuración del mock para que el token sea inválido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(false);

            // Act
            ResponseEntity<List<Comentario>> response = comentarioController.getAllAdmin(token);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            verify(comentarioService, never()).getAll();
        }
    }

    @Test
    void testGetAllAdmin_Exception() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";

            // Configuración del mock para que el token sea válido
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            // Simula una excepción al obtener los comentarios
            when(comentarioService.getAll()).thenThrow(new RuntimeException("Error al obtener comentarios"));

            // Act
            ResponseEntity<List<Comentario>> response = comentarioController.getAllAdmin(token);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertNull(response.getBody());
            verify(comentarioService, times(1)).getAll();
        }
    }

    @Test
    void testAprobarComentario_ValidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String token = "valid_token";
            Long id = 1L;

            // Simula que el token válido devuelve true
            utilMockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);

            // Act
            ResponseEntity<Comentario> response = comentarioController.aprobar(id, token);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode(), "Un token válido debería retornar 200 OK.");
            verify(comentarioService, times(1)).aprobarComentario(id, true);
        }
    }

    @Test
    void testAprobarComentario_InvalidToken() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String invalidToken = "invalid_token";
            Long comentarioId = 1L;

            // Simula que el token inválido devuelve false (token inválido según la nueva lógica)
            utilMockedStatic.when(() -> Util.validateToken(invalidToken, jwtUtil)).thenReturn(false);

            // Act
            ResponseEntity<Comentario> response = comentarioController.aprobar(comentarioId, invalidToken);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode(), "El token inválido debería retornar 401 UNAUTHORIZED.");
            verify(comentarioService, never()).aprobarComentario(anyLong(), anyBoolean());
        }
    }

    @Test
    void testAprobarComentario_Exception() {
        try (MockedStatic<Util> utilMockedStatic = mockStatic(Util.class)) {
            // Arrange
            String validToken = "valid_token";
            Long comentarioId = 1L;

            // Simular que el token es válido
            utilMockedStatic.when(() -> Util.validateToken(validToken, jwtUtil)).thenReturn(true);

            // Simular que el servicio lanza una excepción inesperada
            doThrow(new RuntimeException("Error interno del servidor")).when(comentarioService).aprobarComentario(comentarioId, true);

            // Act
            ResponseEntity<Comentario> response = comentarioController.aprobar(comentarioId, validToken);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Debería retornar 500 INTERNAL_SERVER_ERROR cuando ocurre una excepción.");
            verify(comentarioService, times(1)).aprobarComentario(comentarioId, true);
        }
    }



}
