package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Receta;
import cl.duoc.app.recetas_backend.service.RecetaService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecetaControllerTest {

    @Mock
    private RecetaService recetaService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private RecetaController recetaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRecientes_InternalServerError() {
        when(recetaService.obtenerRecetasMasRecientes(5)).thenThrow(new RuntimeException("Error interno"));

        ResponseEntity<List<Receta>> response = recetaController.recientes(5);

        assertEquals(500, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(recetaService).obtenerRecetasMasRecientes(5);
    }


    @Test
    void testRecientes() {
        List<Receta> recetas = Arrays.asList(new Receta(), new Receta());
        when(recetaService.obtenerRecetasMasRecientes(5)).thenReturn(recetas);

        ResponseEntity<List<Receta>> response = recetaController.recientes(5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(recetas, response.getBody());
        verify(recetaService).obtenerRecetasMasRecientes(5);
    }

    @Test
    void testGetAllRecetas() {
        List<Receta> recetas = Arrays.asList(new Receta(), new Receta());
        when(recetaService.findAll()).thenReturn(recetas);

        ResponseEntity<List<Receta>> response = recetaController.getAllRecetas();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(recetas, response.getBody());
        verify(recetaService).findAll();
    }

    @Test
    void testPopulares() {
        List<Receta> recetas = Arrays.asList(new Receta(), new Receta());
        when(recetaService.obtenerRecetasMasPopulares(5)).thenReturn(recetas);

        ResponseEntity<List<Receta>> response = recetaController.populares(5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(recetas, response.getBody());
        verify(recetaService).obtenerRecetasMasPopulares(5);
    }

    @Test
    void testBuscarRecetas() {
        List<Receta> recetas = Arrays.asList(new Receta(), new Receta());
        when(recetaService.buscarRecetas("Pizza", null, null, null, null)).thenReturn(recetas);

        ResponseEntity<List<Receta>> response = recetaController.buscarRecetas("Pizza", "any", "any", "any", "any");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(recetas, response.getBody());
        verify(recetaService).buscarRecetas("Pizza", null, null, null, null);
    }

    @Test
    void testGetRecetaDetalle_Found() {
        Receta receta = new Receta();
        when(recetaService.findById(1L)).thenReturn(Optional.of(receta));

        ResponseEntity<Receta> response = recetaController.getRecetaDetalle(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(receta, response.getBody());
        verify(recetaService).findById(1L);
    }

    @Test
    void testGetRecetaDetalle_NotFound() {
        when(recetaService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Receta> response = recetaController.getRecetaDetalle(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(recetaService).findById(1L);
    }

    @Test
    void testRegistrarReceta_Success() {
        Receta receta = new Receta();
        receta.setNombre("Receta 1");
        receta.setDificultad("Fácil");
        receta.setIngredientes("Ingredientes");
        receta.setPaisOrigen("Chile");
        receta.setFotografiaUrl("http://image.jpg");
        receta.setTipoCocina("Italiana");
        receta.setInstruccionesPreparacion("Instrucciones");

        String token = "Bearer validToken";

        when(Util.validateToken(token, jwtUtil)).thenReturn(true);
        when(recetaService.register(receta)).thenReturn(receta);

        ResponseEntity<Receta> response = recetaController.registrarReceta(receta, token);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(receta, response.getBody());
        verify(recetaService).register(receta);
    }

    @Test
    void testRegistrarReceta_Unauthorized() {
        Receta receta = new Receta();
        String token = "Bearer invalidToken";

        when(Util.validateToken(token, jwtUtil)).thenReturn(false);

        ResponseEntity<Receta> response = recetaController.registrarReceta(receta, token);

        assertEquals(401, response.getStatusCodeValue());
        verifyNoInteractions(recetaService);
    }

    @Test
    void testRegistrarReceta_BadRequest() {
        Receta receta = new Receta(); // Receta con campos vacíos
        String token = "Bearer validToken";

        when(Util.validateToken(token, jwtUtil)).thenReturn(true);

        ResponseEntity<Receta> response = recetaController.registrarReceta(receta, token);

        assertEquals(400, response.getStatusCodeValue());
        verifyNoInteractions(recetaService);
    }

    @Test
    void testRegistrarReceta_InternalServerError() {
        Receta receta = new Receta();
        receta.setNombre("Receta 1");
        receta.setDificultad("Fácil");
        receta.setIngredientes("Ingredientes");
        receta.setPaisOrigen("Chile");
        receta.setFotografiaUrl("http://image.jpg");
        receta.setTipoCocina("Italiana");
        receta.setInstruccionesPreparacion("Instrucciones");

        String token = "Bearer validToken";

        when(Util.validateToken(token, jwtUtil)).thenReturn(true);
        when(recetaService.register(receta)).thenThrow(new RuntimeException("Error interno"));

        ResponseEntity<Receta> response = recetaController.registrarReceta(receta, token);

        assertEquals(500, response.getStatusCodeValue());
        verify(recetaService).register(receta);
    }
}
