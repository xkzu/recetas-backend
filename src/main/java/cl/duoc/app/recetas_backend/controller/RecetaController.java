package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Receta;
import cl.duoc.app.recetas_backend.service.RecetaService;
import cl.duoc.app.recetas_backend.util.JwtUtil;
import cl.duoc.app.recetas_backend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    private final JwtUtil jwtUtil;

    @Autowired
    public RecetaController(RecetaService recetaService, JwtUtil jwtUtil) {
        this.recetaService = recetaService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/recientes/{limite}")
    public ResponseEntity<List<Receta>> recientes(@PathVariable int limite) {
        try {
            return ResponseEntity.ok(recetaService.obtenerRecetasMasRecientes(limite));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Receta>> getAllRecetas() {
        return ResponseEntity.ok(recetaService.findAll());
    }

    @GetMapping("/populares/{limite}")
    public ResponseEntity<List<Receta>> populares(@PathVariable int limite) {
        return ResponseEntity.ok(recetaService.obtenerRecetasMasPopulares(limite));
    }

    @GetMapping("/buscar/{nombre}/{tipoCocina}/{ingredientes}/{paisOrigen}/{dificultad}")
    public ResponseEntity<List<Receta>> buscarRecetas(
            @PathVariable String nombre,
            @PathVariable String tipoCocina,
            @PathVariable String ingredientes,
            @PathVariable String paisOrigen,
            @PathVariable String dificultad) {

        List<Receta> recetas = recetaService.buscarRecetas(
                "any".equalsIgnoreCase(nombre) ? null : nombre,
                "any".equalsIgnoreCase(tipoCocina) ? null : tipoCocina,
                "any".equalsIgnoreCase(ingredientes) ? null : ingredientes,
                "any".equalsIgnoreCase(paisOrigen) ? null : paisOrigen,
                "any".equalsIgnoreCase(dificultad) ? null : dificultad);

        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Receta> getRecetaDetalle(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.findById(id);
        if (receta.isPresent()) {
            return ResponseEntity.ok(receta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Receta> registrarReceta(@RequestBody Receta receta, @RequestHeader("Authorization") String token) {
        try {
            if (Util.validateToken(token, jwtUtil))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if (Util.isEmptyOrNull(receta.getDificultad())
                    || Util.isEmptyOrNull(receta.getNombre())
                    || Util.isEmptyOrNull(receta.getIngredientes())
                    || Util.isEmptyOrNull(receta.getPaisOrigen())
                    || Util.isEmptyOrNull(receta.getFotografiaUrl())
                    || Util.isEmptyOrNull(receta.getTipoCocina())
                    || Util.isEmptyOrNull(receta.getInstruccionesPreparacion())) {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok(recetaService.register(receta));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
