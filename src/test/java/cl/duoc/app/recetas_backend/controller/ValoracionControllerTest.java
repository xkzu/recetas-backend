package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Valoracion;
import cl.duoc.app.recetas_backend.service.ValoracionService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ValoracionControllerTest {

    @Mock
    private ValoracionService valoracionService;

    @Mock
    private JwtUtil jwtUtil;

    private ValoracionController valoracionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        valoracionController = new ValoracionController(valoracionService, jwtUtil);
    }

    @Test
    void testAddValoracion_Success() {
        try (MockedStatic<Util> mockedStatic = mockStatic(Util.class)) {
            // Arrange
            Valoracion valoracion = new Valoracion(1L, 1L, 1L, 5);
            String token = "Bearer validToken";

            mockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(valoracionService.save(valoracion)).thenReturn(valoracion);

            // Act
            ResponseEntity<Valoracion> response = valoracionController.addValoracion(valoracion, token);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(valoracion, response.getBody());
            verify(valoracionService, times(1)).save(valoracion);
        }
    }

    @Test
    void testAddValoracion_Unauthorized() {
        try (MockedStatic<Util> mockedStatic = mockStatic(Util.class)) {
            // Arrange
            Valoracion valoracion = new Valoracion(1L, 1L, 1L, 5);
            String token = "Bearer invalidToken";

            mockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(false);

            // Act
            ResponseEntity<Valoracion> response = valoracionController.addValoracion(valoracion, token);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            verify(valoracionService, never()).save(valoracion);
        }
    }

    @Test
    void testAddValoracion_InternalServerError() {
        try (MockedStatic<Util> mockedStatic = mockStatic(Util.class)) {
            // Arrange
            Valoracion valoracion = new Valoracion(1L, 1L, 1L, 5);
            String token = "Bearer validToken";

            mockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(valoracionService.save(valoracion)).thenThrow(new RuntimeException("Unexpected error"));

            // Act
            ResponseEntity<Valoracion> response = valoracionController.addValoracion(valoracion, token);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertEquals(valoracion, response.getBody());
            verify(valoracionService, times(1)).save(valoracion);
        }
    }

    @Test
    void testFindAll_Success() {
        try (MockedStatic<Util> mockedStatic = mockStatic(Util.class)) {
            // Arrange
            Long id = 1L;
            String token = "Bearer validToken";
            List<Valoracion> valoraciones = new ArrayList<>();
            valoraciones.add(new Valoracion(1L, id, 1L, 5));
            valoraciones.add(new Valoracion(2L, id, 2L, 4));

            mockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(valoracionService.findAll(id)).thenReturn(valoraciones);

            // Act
            ResponseEntity<List<Valoracion>> response = valoracionController.findAll(id, token);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(valoraciones, response.getBody());
            verify(valoracionService, times(1)).findAll(id);
        }
    }

    @Test
    void testFindAll_Unauthorized() {
        try (MockedStatic<Util> mockedStatic = mockStatic(Util.class)) {
            // Arrange
            Long id = 1L;
            String token = "Bearer invalidToken";

            mockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(false);

            // Act
            ResponseEntity<List<Valoracion>> response = valoracionController.findAll(id, token);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            verify(valoracionService, never()).findAll(id);
        }
    }

    @Test
    void testFindAll_InternalServerError() {
        try (MockedStatic<Util> mockedStatic = mockStatic(Util.class)) {
            // Arrange
            Long id = 1L;
            String token = "Bearer validToken";

            mockedStatic.when(() -> Util.validateToken(token, jwtUtil)).thenReturn(true);
            when(valoracionService.findAll(id)).thenThrow(new RuntimeException("Unexpected error"));

            // Act
            ResponseEntity<List<Valoracion>> response = valoracionController.findAll(id, token);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
            assertEquals(null, response.getBody());
            verify(valoracionService, times(1)).findAll(id);
        }
    }
}
