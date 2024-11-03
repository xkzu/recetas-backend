package cl.duoc.app.recetas_backend.controller;

import cl.duoc.app.recetas_backend.model.Receta;
import cl.duoc.app.recetas_backend.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    @Autowired
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
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

}
